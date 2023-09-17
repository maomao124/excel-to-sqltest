package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.service.TemplateEngineService;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Map;
import java.util.Properties;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(类名): SpringBaseTemplateEngineServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/17
 * Time(创建时间)： 12:28
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class SpringBaseTemplateEngineServiceImpl implements TemplateEngineService
{

    @Override
    public String parse(String template, Map<String, String> map) throws Exception
    {
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        Properties properties = new Properties();
        map.forEach(properties::setProperty);
        return helper.replacePlaceholders(template, properties);
    }
}
