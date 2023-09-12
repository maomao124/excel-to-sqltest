package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.service.ExcelService;
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
        int firstRow = baseConfigurationProperties.getExcel().getStartRow();
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
                        value = String.valueOf(row.getCell(i1).getNumericCellValue());
                    }
                    catch (Exception ex)
                    {
                        try
                        {
                            value = String.valueOf(row.getCell(i1).getDateCellValue());
                        }
                        catch (Exception exx)
                        {
                            value = String.valueOf(row.getCell(i1).getBooleanCellValue());
                        }
                    }
                }
                rowMap.put(iterator.next(), value);
            }
            log.debug("第" + (i + 1) + "行数据：" + rowMap);
            content.add(rowMap);
        }

        return new ExcelData().setTitles(titles).setContent(content);
    }
}
