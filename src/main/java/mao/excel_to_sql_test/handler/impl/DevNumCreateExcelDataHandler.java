package mao.excel_to_sql_test.handler.impl;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.handler.ExcelDataHandler;
import mao.excel_to_sql_test.service.DevNumCreateService;
import mao.excel_to_sql_test.utils.id.SnowflakeIdGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler.impl
 * Class(类名): DevNumCreateExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2024/8/3
 * Time(创建时间)： 16:34
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Component
public class DevNumCreateExcelDataHandler implements ExcelDataHandler
{
    private static final Logger log = LoggerFactory.getLogger(DevNumCreateExcelDataHandler.class);

    @Autowired
    private DevNumCreateService devNumCreateService;

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.devNumCreateExcelDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.devNumCreateExcelDataHandler.order:60}")
    private int order;

    /**
     * 车牌号前缀
     */
    @Value("${ets.handler.excelDataHandler.devNumCreateExcelDataHandler.prefix:京A}")
    private String prefix;

    /**
     * 生成的列名称，默认为车牌号
     */
    @Value("${ets.handler.excelDataHandler.devNumCreateExcelDataHandler.filedName:车牌号}")
    private String filedName;

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
        return "车牌号创建处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        List<Map<String, String>> content = excelData.getContent();
        List<String> titles = excelData.getTitles();
        for (Map<String, String> rowMap : content)
        {
            String code = devNumCreateService.create(prefix);
            log.debug("车牌号：" + code);
            rowMap.put(filedName, code);
        }
        //设置表头
        titles.add(filedName);
    }
}
