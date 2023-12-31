package mao.excel_to_sql_test.handler;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Interface(接口名): DataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/13
 * Time(创建时间)： 16:09
 * Version(版本): 1.0
 * Description(描述)： 数据处理器顶层接口
 */

public interface DataHandler
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
     * 得到数据处理器的名称
     *
     * @return {@link String} 数据处理器名称
     */
    String getName();
}
