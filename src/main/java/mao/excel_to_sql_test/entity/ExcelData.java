package mao.excel_to_sql_test.entity;

import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.entity
 * Class(类名): ExcelData
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/10
 * Time(创建时间)： 16:48
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class ExcelData
{
    /**
     * 标题
     */
    private List<String> titles;

    /**
     * 内容
     */
    private List<Map<String, String>> content;

    public List<String> getTitles()
    {
        return titles;
    }

    public ExcelData setTitles(List<String> titles)
    {
        this.titles = titles;
        return this;
    }

    public List<Map<String, String>> getContent()
    {
        return content;
    }

    public ExcelData setContent(List<Map<String, String>> content)
    {
        this.content = content;
        return this;
    }
}
