package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.entity.ExcelData;

import java.util.List;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Interface(接口名): SqlDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/6
 * Time(创建时间)： 13:43
 * Version(版本): 1.0
 * Description(描述)： sql数据处理器
 */

public interface SqlDataHandler
{
    /**
     * 是否启用此处理器
     *
     * @return boolean 启用为true，否则为false
     */
    boolean enabled();

    /**
     * 得到处理器的执行顺序，数字越小，优先级越高
     *
     * @return int 处理器的执行顺序
     */
    int getOrder();

    /**
     * 得到sql数据处理器的名称
     *
     * @return {@link String} sql数据处理器名称
     */
    String getName();

    /**
     * 处理数据
     *
     * @param excelData excel数据，在这里修改几乎无意义，供查询使用
     * @param sqlList   sql列表
     */
    void handler(ExcelData excelData, List<String> sqlList);
}
