package mao.excel_to_sql_test.service;

import java.util.List;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service
 * Interface(接口名): FileService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/12
 * Time(创建时间)： 10:51
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface FileService
{
    /**
     * 写入到文件
     *
     * @param sqlList sql列表
     */
    void write(List<String> sqlList);
}
