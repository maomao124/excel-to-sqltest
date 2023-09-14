package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.service.ExcelService;
import mao.excel_to_sql_test.service.SqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl.impl
 * Class(类名): SqlServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/11
 * Time(创建时间)： 10:18
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class SqlServiceImpl implements SqlService
{
    private static final Logger log = LoggerFactory.getLogger(SqlServiceImpl.class);

    @Autowired
    private ExcelService excelService;

    @Override
    public List<String> excelToSql(String template, ExcelData excelData) throws IOException
    {
        List<String> sqlList = new ArrayList<>();
        log.debug("模板：" + template);
        List<String> titles = excelData.getTitles();
        List<Map<String, String>> content = excelData.getContent();
        content.forEach(rowMap ->
        {
            PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
            Properties properties = new Properties();
            rowMap.forEach(properties::setProperty);
            String result = helper.replacePlaceholders(template, properties);
            sqlList.add(result);
            log.debug("记录：" + result);
        });
        return sqlList;
    }

    @Override
    public List<String> excelToSql(String template) throws IOException
    {
        ExcelData excelData = excelService.loadExcel();
        return excelToSql(template, excelData);
    }
}
