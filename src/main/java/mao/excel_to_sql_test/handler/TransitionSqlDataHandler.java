package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.entity.ExcelData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): TransitionSqlDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/14
 * Time(创建时间)： 19:59
 * Version(版本): 1.0
 * Description(描述)： 事务sql数据处理器
 */

@Component
public class TransitionSqlDataHandler implements SqlDataHandler
{

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.sqlDataHandler.transitionSqlDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.sqlDataHandler.transitionSqlDataHandler.order:10}")
    private int order;

    @Override
    public boolean enabled()
    {
        return enable;
    }

    @Override
    public int getOrder()
    {
        return order;
    }

    @Override
    public String getName()
    {
        return "事务sql数据处理器";
    }

    @Override
    public void handler(ExcelData excelData, String template, List<String> sqlList)
    {
        sqlList.add(0,"START TRANSACTION;\n");
        sqlList.add("\nCOMMIT;");
    }
}
