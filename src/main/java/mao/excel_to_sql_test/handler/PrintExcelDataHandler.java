package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.entity.ExcelData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): PrintExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/6
 * Time(创建时间)： 14:14
 * Version(版本): 1.0
 * Description(描述)： 数据打印excel数据处理器
 */

@Component
public class PrintExcelDataHandler implements ExcelDataHandler
{

    private static final Logger log = LoggerFactory.getLogger(PrintExcelDataHandler.class);

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.printExcelDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.printExcelDataHandler.order:9998}")
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
        return "数据打印excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> titles = excelData.getTitles();
        List<Map<String, String>> content = excelData.getContent();
        stringBuilder.append("\n\n-----------------------------------------------------------------------\n|  ");
        for (String title : titles)
        {
            stringBuilder.append(title).append("\t");
        }
        stringBuilder.append("\n-----------------------------------------------------------------------\n|  ");
        for (Map<String, String> rowMap : content)
        {
            for (String title : titles)
            {
                stringBuilder.append(rowMap.get(title)).append("\t");
            }
            stringBuilder.append("\n|  ");
        }
        stringBuilder.append("-----------------------------------------------------------------------\n");
        log.info(stringBuilder.toString());
    }
}
