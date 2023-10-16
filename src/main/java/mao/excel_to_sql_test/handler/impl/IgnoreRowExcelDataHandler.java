package mao.excel_to_sql_test.handler.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.handler.ExcelDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): IgnoreRowExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/7
 * Time(创建时间)： 9:20
 * Version(版本): 1.0
 * Description(描述)： 忽略行excel数据处理器
 */

@Component
public class IgnoreRowExcelDataHandler implements ExcelDataHandler
{

    private static final Logger log = LoggerFactory.getLogger(DistinctExcelDataHandler.class);

    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.ignoreRowExcelDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.ignoreRowExcelDataHandler.order:0}")
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
        return "忽略行excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        List<Map<String, String>> content = excelData.getContent();
        try
        {
            List<Map<String, String>> contentNew = new ArrayList<>();
            for (int i = 0; i < content.size(); i++)
            {
                Map<String, String> rowMap = content.get(i);
                if (isIgnore(rowMap))
                {
                    //忽略
                    log.info("忽略第" + (i + 1) + "行数据：" + rowMap);
                }
                else
                {
                    //log.debug("第" + (i + 1) + "行数据：" + rowMap);
                    contentNew.add(rowMap);
                }
            }
            excelData.setContent(contentNew);
        }
        catch (Exception e)
        {
            log.error("执行忽略行excel数据处理器时出现问题：", e);
        }
    }


    /**
     * 是否忽略当前行
     *
     * @param rowMap 行数据
     * @return boolean 要忽略为是
     */
    private boolean isIgnore(Map<String, String> rowMap)
    {
        Map<String, List<String>> filter = baseConfigurationProperties.getFilter();
        AtomicBoolean isIgnore = new AtomicBoolean(false);
        rowMap.forEach((title, value) ->
        {
            //判断该列的表头是否在过滤列表里
            if (filter.get(title) == null)
            {
                //不在过滤列表，直接下一个
                return;
            }
            //表头在过滤列表里
            List<String> list = filter.get(title);
            for (String s : list)
            {
                if (s.equals(value))
                {
                    isIgnore.set(true);
                    return;
                }
            }
        });
        return isIgnore.get();
    }
}
