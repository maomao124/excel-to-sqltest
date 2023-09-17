package mao.excel_to_sql_test.service;

import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service
 * Interface(接口名): TemplateEngineService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/17
 * Time(创建时间)： 11:30
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface TemplateEngineService
{
    /**
     * 解析模板
     *
     * @param template 要解析的模板
     * @param map      参数
     * @return {@link String}
     */
    String parse(String template, Map<String, String> map) throws Exception;
}
