package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): OrderByExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/6
 * Time(创建时间)： 13:26
 * Version(版本): 1.0
 * Description(描述)： 字段排序处理器
 */

@Component
public class OrderByExcelDataHandler implements ExcelDataHandler
{

    private static final Logger log = LoggerFactory.getLogger(OrderByExcelDataHandler.class);

    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;

    @Override
    public int getOrder()
    {
        return 2;
    }

    @Override
    public String getName()
    {
        return "字段排序excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        fieldSort(excelData.getTitles(), excelData.getContent());
    }


    /**
     * 排序
     *
     * @param titles  表头
     * @param content 内容
     */
    private void fieldSort(List<String> titles, List<Map<String, String>> content)
    {
        if (baseConfigurationProperties.getOrderBy() != null)
        {
            String orderBy = baseConfigurationProperties.getOrderBy();
            //是否包含英文逗号
            if (orderBy.contains(","))
            {
                String[] split = orderBy.split(",");
                if (split.length == 2)
                {
                    String field = split[0];
                    String order = split[1].toLowerCase(Locale.ROOT);
                    if ((!order.equals("asc")) && (!order.equals("desc")))
                    {
                        log.warn("排序失败！ 逗号后面只能写asc或者desc");
                    }
                    else
                    {
                        //判断字段是否存在
                        if (titles.contains(field))
                        {
                            //排序
                            content.sort(new Comparator<Map<String, String>>()
                            {
                                @Override
                                public int compare(Map<String, String> o1, Map<String, String> o2)
                                {
                                    String fValue1 = o1.get(field);
                                    String fValue2 = o2.get(field);
                                    int result = fValue1.compareTo(fValue2);
                                    if (order.equals("desc"))
                                    {
                                        result = -result;
                                    }
                                    return result;
                                }
                            });
                        }
                        else
                        {
                            log.warn("排序字段不存在!");
                        }
                    }
                }
            }
            else
            {
                log.warn("排序失败！ 不包含逗号");
            }
        }
    }
}
