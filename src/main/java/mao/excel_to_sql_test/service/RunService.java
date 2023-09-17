package mao.excel_to_sql_test.service;

import java.io.IOException;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service
 * Interface(接口名): RunService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/13
 * Time(创建时间)： 14:26
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface RunService
{
    /**
     * 输出配置信息到info级别日志
     */
    void showConfig();

    /**
     * 运行入口
     */
    void run();

    /**
     * 运行入口
     *
     * @param template 模板
     */
    void run(String template) throws Exception;

}
