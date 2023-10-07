package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): PrintSqlDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/7
 * Time(创建时间)： 14:17
 * Version(版本): 1.0
 * Description(描述)： 数据打印sql数据处理器
 */

@Component
public class PrintSqlDataHandler implements SqlDataHandler
{
    private static final Logger log = LoggerFactory.getLogger(OrderByExcelDataHandler.class);

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.sqlDataHandler.printSqlDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.sqlDataHandler.printSqlDataHandler.order:9998}")
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
        return "数据打印sql数据处理器";
    }

    @Override
    public void handler(ExcelData excelData, String template, List<String> sqlList)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n|-------|---------------------------------------------\n");
        stringBuilder.append("| 序号\t | sql \n");
        stringBuilder.append("|-------|---------------------------------------------\n");
        for (int i = 0; i < sqlList.size(); i++)
        {
            stringBuilder.append("| ").append((i + 1)).append(" | ")
                    .append(sqlList.get(i))
                    .append("\n");
        }
        stringBuilder.append("|-------|---------------------------------------------\n");
        log.info(stringBuilder.toString());
    }
}
