package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): DistinctExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/6
 * Time(创建时间)： 13:28
 * Version(版本): 1.0
 * Description(描述)： 字段去重excel数据处理器
 */

@Component
public class DistinctExcelDataHandler implements ExcelDataHandler
{

    private static final Logger log = LoggerFactory.getLogger(DistinctExcelDataHandler.class);

    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.distinctExcelDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.distinctExcelDataHandler.order:1}")
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
        return "字段去重excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        excelData.setContent(distinct(excelData.getTitles(), excelData.getContent()));
    }


    /**
     * 字段去重
     *
     * @param titles  表头
     * @param content 内容
     * @return {@link List}<{@link Map}<{@link String}, {@link String}>>
     */
    private List<Map<String, String>> distinct(List<String> titles, List<Map<String, String>> content)
    {
        if (baseConfigurationProperties.getDistinct() != null)
        {
            String field = baseConfigurationProperties.getDistinct();
            Map<String, Map<String, String>> map = new HashMap<>();
            //判断是否在表头里存在
            if (titles.contains(field))
            {
                //存在
                for (Map<String, String> stringStringMap : content)
                {
                    map.put(stringStringMap.get(field), stringStringMap);
                }
                List<Map<String, String>> list = new ArrayList<>();
                map.forEach(new BiConsumer<String, Map<String, String>>()
                {
                    @Override
                    public void accept(String s, Map<String, String> map)
                    {
                        list.add(map);
                    }
                });
                content = list;
            }
            else
            {
                log.warn("字段去重失败！字段" + field + "不存在");
            }
        }
        return content;
    }
}
