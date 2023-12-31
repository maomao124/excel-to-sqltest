package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.handler.ExcelDataHandler;
import mao.excel_to_sql_test.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private List<ExcelDataHandler> excelDataHandlerList;


    @Override
    public ExcelData loadExcel() throws IOException
    {
        log.debug("文件:" + baseConfigurationProperties.getInputPath());
        int firstRow = baseConfigurationProperties.getExcel().getStartRow();
        Workbook workbook = null;
        if (baseConfigurationProperties.getInputPath().endsWith(".xls"))
        {
            //加载低版本工作簿
            log.debug("加载低版本excel");
            workbook = new HSSFWorkbook(new FileInputStream(baseConfigurationProperties.getInputPath()));
        }
        else
        {
            //加载工作簿
            log.debug("加载高版本excel");
            workbook = new XSSFWorkbook(new FileInputStream(baseConfigurationProperties.getInputPath()));
        }
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
            log.debug("第" + (i + 1) + "行数据：" + rowMap);
            content.add(rowMap);
        }
        return executeExcelDataHandler(new ExcelData().setTitles(titles).setContent(content));
    }

    @Override
    public boolean saveExcel(ExcelData excelData) throws IOException
    {
        String path = baseConfigurationProperties.getOutputPath();

        boolean appendTime = baseConfigurationProperties.isAppendTime();
        String appendTimeFormat = baseConfigurationProperties.getAppendTimeFormat();
        int index = path.lastIndexOf(".");
        String startSubstring = path.substring(0, index);
        if (appendTime)
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(appendTimeFormat);
            String format = simpleDateFormat.format(new Date());
            path = startSubstring + "-" + format + ".xlsx";
        }
        else
        {
            path = startSubstring + ".xlsx";
        }
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //构建表头
        Row row = sheet.createRow(0);
        List<String> titles = excelData.getTitles();
        for (int i = 0; i < titles.size(); i++)
        {
            row.createCell(i).setCellValue(titles.get(i));
        }
        //内容
        List<Map<String, String>> content = excelData.getContent();
        for (int i = 0; i < content.size(); i++)
        {
            row = sheet.createRow(i + 1);
            Map<String, String> rowMap = content.get(i);
            for (int i1 = 0; i1 < titles.size(); i1++)
            {
                row.createCell(i1).setCellValue(rowMap.get(titles.get(i1)));
            }
        }
        workbook.write(new FileOutputStream(path));
        workbook.close();
        log.debug("保存的文件位于：" + path);
        return true;
    }


    /**
     * 执行excel数据处理器
     *
     * @param excelData ExcelData
     * @return {@link ExcelData} ExcelData
     * @throws IOException 异常
     */
    private ExcelData executeExcelDataHandler(ExcelData excelData) throws IOException
    {
        if (excelDataHandlerList != null && excelDataHandlerList.size() > 0)
        {
            excelDataHandlerList.sort(Comparator.comparingInt(ExcelDataHandler::getOrder));
            //相当于：
            /*excelDataHandlerList.sort(new Comparator<ExcelDataHandler>()
            {
                @Override
                public int compare(ExcelDataHandler o1, ExcelDataHandler o2)
                {
                    return o1.getOrder() - o2.getOrder();
                }
            });*/
            StringBuilder str = new StringBuilder("excel数据处理器执行顺序：");
            for (ExcelDataHandler excelDataHandler : excelDataHandlerList)
            {
                if (!excelDataHandler.enabled())
                {
                    continue;
                }
                str.append("-->").append(excelDataHandler.getName());
            }
            log.info(str.toString());
            for (ExcelDataHandler excelDataHandler : excelDataHandlerList)
            {
                if (!excelDataHandler.enabled())
                {
                    log.info("excel数据处理器\"" + excelDataHandler.getName() + "\"已关闭");
                    continue;
                }
                log.info("开始进入\"" + excelDataHandler.getName() + "\"");
                excelDataHandler.handler(excelData);
                log.info("退出\"" + excelDataHandler.getName() + "\"");
            }
        }
        return excelData;
    }
}
