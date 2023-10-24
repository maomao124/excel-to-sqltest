package mao.excel_to_sql_test.handler.impl;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.entity.Geo;
import mao.excel_to_sql_test.handler.ExcelDataHandler;
import mao.excel_to_sql_test.service.AddressToGeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): AddressToGeoExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/7
 * Time(创建时间)： 11:33
 * Version(版本): 1.0
 * Description(描述)： 详细地址转经纬度excel数据处理器
 */

@Component
public class AddressToGeoExcelDataHandler implements ExcelDataHandler
{

    private static final Logger log = LoggerFactory.getLogger(DistinctExcelDataHandler.class);


    @Autowired
    private AddressToGeoService addressToGeoService;

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.order:20}")
    private int order;

    /**
     * 详细地址在表头里的名称，默认为address
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.filedName:address}")
    private String filedName;

    /**
     * 并发的时间间隔，单位是毫秒，百度地图限制了并发，如果参数填200，就是每秒处理5个，填50是20个
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.concurrencyInterval:200}")
    private int concurrencyInterval;

    /**
     * 并发重试间隔，默认为3秒
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.concurrencyRetryInterval:3000}")
    private int concurrencyRetryInterval;

    /**
     * 如果发生错误，是否需要添加表头信息，当只运行了一部分后报错时，原来的一部分数据也会保存
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.ifErrorAddTitle:false}")
    private boolean ifErrorAddTitle;

    /**
     * 超时时间
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.timeout:60000}")
    private int timeout;

    /**
     * 读取超时时间
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.readTimeout:30000}")
    private int readTimeout;


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
        return "详细地址转经纬度excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        if (!excelData.getTitles().contains(filedName))
        {
            //没有详细地址字段
            log.info("没有详细地址字段\"" + filedName + "\"，跳过执行");
            return;
        }
        List<String> titles = excelData.getTitles();
        List<Map<String, String>> content = excelData.getContent();
        if (titles.contains("longitude") || titles.contains("latitude"))
        {
            log.warn("字段冲突! 已存在字段longitude或者latitude，跳过执行");
            return;
        }
        try
        {
            log.info("开始向百度地图服务发起请求");
            int index = 0;
            for (Map<String, String> rowMap : content)
            {
                String address = rowMap.get(filedName);
                index++;
                if (address == null || address.equals(""))
                {
                    log.info("跳过" + index);
                    continue;
                }
                //转换
                Geo geo = addressToGeoService.addressToGeo(address);
                log.info("已完成：" + index + "/" + content.size() + " ，结果：" + geo);
                rowMap.put("longitude", geo.getLongitude() != null ? geo.getLongitude().toString() : null);
                rowMap.put("latitude", geo.getLongitude() != null ? geo.getLatitude().toString() : null);
                rowMap.put("precise", geo.getLongitude() != null ? geo.getPrecise().toString() : null);
                rowMap.put("confidence", geo.getLongitude() != null ? geo.getConfidence().toString() : null);
                rowMap.put("comprehension", geo.getLongitude() != null ? geo.getComprehension().toString() : null);
                rowMap.put("level", geo.getLevel());
                if (concurrencyInterval > 0)
                {
                    //限制并发
                    Thread.sleep(concurrencyInterval);
                }
            }
            log.info("请求完成");
            //设置表头
            titles.add("longitude");
            titles.add("latitude");
            titles.add("precise");
            titles.add("confidence");
            titles.add("comprehension");
            titles.add("level");
        }
        catch (Exception e)
        {
            if (ifErrorAddTitle)
            {
                //设置表头
                titles.add("longitude");
                titles.add("latitude");
                titles.add("precise");
                titles.add("confidence");
                titles.add("comprehension");
                titles.add("level");
            }
            log.error("执行详细地址转经纬度excel数据处理器时发生错误：", e);
        }
    }
}
