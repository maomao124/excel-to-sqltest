package mao.excel_to_sql_test.service;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service
 * Interface(接口名): DevNumCreateService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2024/8/3
 * Time(创建时间)： 16:11
 * Version(版本): 1.0
 * Description(描述)： 车牌号随机创建
 */

public interface DevNumCreateService
{
    /**
     * 创建车牌
     *
     * @param prefix 前缀，京A，粤A，粤C等
     * @return {@link String }
     */
    String create(String prefix);

}
