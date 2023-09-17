package mao.excel_to_sql_test.service.impl;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import mao.excel_to_sql_test.service.TemplateEngineService;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(类名): FreeMarkerTemplateEngineServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/17
 * Time(创建时间)： 11:32
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class FreeMarkerTemplateEngineServiceImpl implements TemplateEngineService
{

    private final Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

    @Override
    public String parse(String template, Map<String, String> map) throws Exception
    {
        /*//加载模板
        stringTemplateLoader.putTemplate("template", template);
        configuration.setTemplateLoader(stringTemplateLoader);
        Template configurationTemplate = configuration.getTemplate("template");
        StringWriter writer = new StringWriter();
        //处理模板
        configurationTemplate.process(map, writer);*/
        StringWriter writer = new StringWriter();
        Template configurationTemplate = new Template("template", template, configuration);
        configurationTemplate.process(map, writer);
        return writer.toString();
    }
}
