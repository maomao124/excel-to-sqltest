package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.entity.ExcelData;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Interface(接口名): ExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/6
 * Time(创建时间)： 13:22
 * Version(版本): 1.0
 * Description(描述)： excel数据处理器接口
 */

public interface ExcelDataHandler
{
    /**
     * 得到处理器的执行顺序，数字越小，优先级越高
     *
     * @return int 处理器的执行顺序
     */
    int getOrder();

    /**
     * 得到excel数据处理器的名称
     *
     * @return {@link String} excel数据处理器名称
     */
    String getName();

    /**
     * 处理数据
     *
     * @param excelData excel数据
     */
    void handler(ExcelData excelData);
}
