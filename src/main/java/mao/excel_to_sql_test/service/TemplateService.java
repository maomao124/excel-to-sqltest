package mao.excel_to_sql_test.service;

import freemarker.template.TemplateException;
import mao.excel_to_sql_test.entity.ExcelData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service
 * Interface(接口名): TemplateService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/12
 * Time(创建时间)： 20:12
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface TemplateService
{
    /**
     * 写入模板日志
     *
     * @param template 模板
     */
    void writeTemplateLog(String template) throws IOException;

    /**
     * 根据excel字段构建默认模板
     *
     * @param excelData excel数据
     * @param tableName 数据库表名称
     * @return {@link String}
     */
    String buildDefaultTemplate(ExcelData excelData, String tableName);

    /**
     * 根据excel字段构建默认模板
     *
     * @param titles    excel标题
     * @param tableName 数据库表名称
     * @return {@link String}
     */
    String buildDefaultTemplate(List<String> titles, String tableName);

    /**
     * 解析模板
     *
     * @param template 要解析的模板
     * @param map      参数
     * @return {@link String}
     */
    String parseTemplate(String template, Map<String, String> map) throws Exception;
}
