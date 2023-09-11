package mao.excel_to_sql_test.service;

import java.io.IOException;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service
 * Interface(接口名): SqlService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/11
 * Time(创建时间)： 10:16
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface SqlService
{

    /**
     * 将excel的数据变成sql insert语句
     *
     * @param template 模板数据
     */
    void excelToSql(String template) throws IOException;
}
