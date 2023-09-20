package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(类名): ExcelServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/10
 * Time(创建时间)： 16:52
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class ExcelServiceImpl implements ExcelService
{
    /**
     * 日志对象
     */
    private static final Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;


    @Override
    public ExcelData loadExcel() throws IOException
    {
        log.debug("文件:" + baseConfigurationProperties.getInputPath());
        int firstRow = baseConfigurationProperties.getExcel().getStartRow();
        if (baseConfigurationProperties.getInputPath().endsWith(".xls"))
        {
            //加载低版本工作簿
            log.debug("加载低版本excel");
            Workbook workbook = new HSSFWorkbook(new FileInputStream(baseConfigurationProperties.getInputPath()));
        }
        else
        {
            //加载工作簿
            log.debug("加载高版本excel");
            Workbook workbook = new XSSFWorkbook(new FileInputStream(baseConfigurationProperties.getInputPath()));
        }
        //加载工作簿
        Workbook workbook = new XSSFWorkbook(new FileInputStream(baseConfigurationProperties.getInputPath()));
        //读取工作表
        Sheet sheet = workbook.getSheetAt(baseConfigurationProperties.getExcel().getSheetAt());
        //得到最后一行
        int lastRowNum = sheet.getLastRowNum();
        if (baseConfigurationProperties.getExcel().getEndRow() > 0)
        {
            lastRowNum = baseConfigurationProperties.getExcel().getEndRow();
        }
        //得到开始行
        Row row = sheet.getRow(firstRow);
        //列开始位置和列结束位置
        short firstCellNum = baseConfigurationProperties.getExcel().getStartCell() > 0 ?
                (short) baseConfigurationProperties.getExcel().getStartCell() : row.getFirstCellNum();
        short lastCellNum = baseConfigurationProperties.getExcel().getEndCell() > 0 ?
                (short) baseConfigurationProperties.getExcel().getEndCell() : row.getLastCellNum();
        log.debug("开始列：" + (firstCellNum + 1));
        log.debug("结束列：" + (lastCellNum + 1));
        //填充表头
        List<String> titles = new ArrayList<>();
        for (short i = firstCellNum; i < lastCellNum; i++)
        {
            String title = row.getCell(i).getStringCellValue();
            titles.add(title);
        }
        log.debug("表头：" + titles);

        //加载表内容
        List<Map<String, String>> content = new ArrayList<>(lastRowNum + 1);
        for (int i = firstRow + 1; i < lastRowNum + 1; i++)
        {
            Map<String, String> rowMap = new HashMap<>();
            row = sheet.getRow(i);
            Iterator<String> iterator = titles.iterator();
            for (short i1 = firstCellNum; i1 < lastCellNum; i1++)
            {
                String value = null;
                try
                {
                    value = row.getCell(i1).getStringCellValue();
                }
                catch (Exception e)
                {
                    try
                    {
                        if (DateUtil.isCellDateFormatted(row.getCell(i1)))
                        {
                            Date date = row.getCell(i1).getDateCellValue();
                            value = String.valueOf(date.getTime());
                        }
                        else
                        {
                            value = String.valueOf(row.getCell(i1).getNumericCellValue());
                        }
                    }
                    catch (Exception ex)
                    {

                        try
                        {
                            value = String.valueOf(row.getCell(i1).getBooleanCellValue());
                        }
                        catch (Exception exxx)
                        {
                            log.warn("导入(" + i + "," + i1 + ")位置的数据时发生问题 :" + exxx.getMessage());
                            value = "";
                        }
                    }
                }
                rowMap.put(iterator.next(), value);
            }
            if (isIgnore(rowMap))
            {
                //忽略
                log.info("忽略第" + (i + 1) + "行数据：" + rowMap);
            }
            else
            {
                log.debug("第" + (i + 1) + "行数据：" + rowMap);
                content.add(rowMap);
            }
        }

        //去重
        content = distinct(titles, content);
        //排序
        if (baseConfigurationProperties.getOrderBy() != null)
        {
            String orderBy = baseConfigurationProperties.getOrderBy();
            //是否包含英文逗号
            if (orderBy.contains(","))
            {
                String[] split = orderBy.split(",");
                //todo
            }
        }

        return new ExcelData().setTitles(titles).setContent(content);
    }

    /**
     * 字段去重
     *
     * @param titles  表头
     * @param content 内容
     * @return {@link List}<{@link Map}<{@link String}, {@link String}>>
     */
    private List<Map<String, String>> distinct(List<String> titles, List<Map<String, String>> content)
    {
        if (baseConfigurationProperties.getDistinct() != null)
        {
            String field = baseConfigurationProperties.getDistinct();
            Map<String, Map<String, String>> map = new HashMap<>();
            //判断是否在表头里存在
            if (titles.contains(field))
            {
                //存在
                for (Map<String, String> stringStringMap : content)
                {
                    map.put(stringStringMap.get(field), stringStringMap);
                }
                List<Map<String, String>> list = new ArrayList<>();
                map.forEach(new BiConsumer<String, Map<String, String>>()
                {
                    @Override
                    public void accept(String s, Map<String, String> map)
                    {
                        list.add(map);
                    }
                });
                content = list;
            }
        }
        return content;
    }

    /**
     * 是否忽略当前行
     *
     * @param rowMap 行数据
     * @return boolean 要忽略为是
     */
    private boolean isIgnore(Map<String, String> rowMap)
    {
        Map<String, List<String>> filter = baseConfigurationProperties.getFilter();
        AtomicBoolean isIgnore = new AtomicBoolean(false);
        rowMap.forEach((title, value) ->
        {
            //判断该列的表头是否在过滤列表里
            if (filter.get(title) == null)
            {
                //不在过滤列表，直接下一个
                return;
            }
            //表头在过滤列表里
            List<String> list = filter.get(title);
            for (String s : list)
            {
                if (s.equals(value))
                {
                    isIgnore.set(true);
                    return;
                }
            }
        });
        return isIgnore.get();
    }
}
