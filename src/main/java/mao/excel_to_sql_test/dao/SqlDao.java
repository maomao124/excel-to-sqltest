package mao.excel_to_sql_test.dao;

import java.util.List;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.dao
 * Interface(接口名): SqlDao
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/18
 * Time(创建时间)： 13:59
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface SqlDao
{
    /**
     * 执行sql，保存到数据库
     *
     * @param sqlList sql语句列表
     * @return boolean
     * @throws Exception 异常
     */
    boolean save(List<String> sqlList) throws Exception;
}
