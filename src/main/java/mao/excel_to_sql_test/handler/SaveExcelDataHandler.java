package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): SaveExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/6
 * Time(创建时间)： 14:16
 * Version(版本): 1.0
 * Description(描述)： 保存excel数据处理器
 */

@Component
public class SaveExcelDataHandler implements ExcelDataHandler
{
    private static final Logger log = LoggerFactory.getLogger(SaveExcelDataHandler.class);

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.saveExcelDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.saveExcelDataHandler.order:9999}")
    private int order;

    @Lazy
    @Autowired
    private ExcelService excelService;

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
        return "保存excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        try
        {
            excelService.saveExcel(excelData);
        }
        catch (IOException e)
        {
            log.error("保存excel数据时出现问题：", e);
        }
    }
}
