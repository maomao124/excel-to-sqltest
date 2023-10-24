package mao.excel_to_sql_test.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.Geo;
import mao.excel_to_sql_test.service.AddressToGeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Project name(项目名称)：addressToGeo
 * Package(包名): mao.address_to_geo.service.impl
 * Class(类名): AddressToGeoServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/5
 * Time(创建时间)： 11:13
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class AddressToGeoServiceImpl implements AddressToGeoService
{

    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;

    /**
     * 并发重试间隔，默认为3秒
     */
    @Value("${ets.handler.excelDataHandler.addressToGeoExcelDataHandler.concurrencyRetryInterval:3000}")
    private int concurrencyRetryInterval;

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

    private static final Logger log = LoggerFactory.getLogger(AddressToGeoServiceImpl.class);

    @Override
    public Geo addressToGeo(String address)
    {
        String apiKey = baseConfigurationProperties.getApiKey();
        String url = "https://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=" + apiKey;
        String body = HttpRequest.get(url).timeout(timeout).setReadTimeout(readTimeout).execute().body();
        log.debug("响应体：" + body);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String message = jsonObject.getStr("message");
        if (message != null)
        {
            //当前并发量已经超过约定并发配额，限制访问
            if (message.equals("当前并发量已经超过约定并发配额，限制访问"))
            {
                log.info("已被限制并发，" + concurrencyRetryInterval + "毫秒后重试");
                try
                {
                    Thread.sleep(concurrencyRetryInterval);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                //递归
                return addressToGeo(address);
            }
            else
            {
                throw new RuntimeException(message);
            }
        }
        String msg = jsonObject.getStr("msg");
        if (msg != null && msg.equals("Internal Service Error:无相关结果"))
        {
            log.warn("地址 \"" + address + "\" 无结果");
            return new Geo().setAddress(address).setTime(LocalDateTime.now());
        }
        JSONObject result = jsonObject.getJSONObject("result");
        Double precise = result.getDouble("precise");
        Double confidence = result.getDouble("confidence");
        Double comprehension = result.getDouble("comprehension");
        String level = result.getStr("level");
        JSONObject location = result.getJSONObject("location");
        //经度
        Double lng = location.getDouble("lng");
        //纬度
        Double lat = location.getDouble("lat");
        return new Geo().setAddress(address).setTime(LocalDateTime.now())
                .setLatitude(lat)
                .setLongitude(lng)
                .setPrecise(precise)
                .setConfidence(confidence)
                .setComprehension(comprehension)
                .setLevel(level);
    }
}
