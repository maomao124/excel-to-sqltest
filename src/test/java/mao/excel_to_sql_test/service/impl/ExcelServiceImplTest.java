package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.service.ExcelService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(测试类名): ExcelServiceImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/11
 * Time(创建时间)： 9:48
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class ExcelServiceImplTest
{
    @Autowired
    private ExcelService excelService;

    private static final Logger log = LoggerFactory.getLogger(ExcelServiceImplTest.class);

    @Test
    void loadExcel() throws IOException
    {
        excelService.loadExcel();
    }

    @Test
    void test()
    {
        log.debug("开始");
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        Properties properties = new Properties();
        properties.setProperty("tableName", "test");
        properties.setProperty("名字", "张三");
        String result = helper.replacePlaceholders("insert into ${tableName} values(${名字});", properties);
        log.debug(result);
    }

    @Test
    void saveExcel() throws IOException
    {
        ExcelData excelData = excelService.loadExcel();
        excelService.saveExcel(excelData);
    }
}
