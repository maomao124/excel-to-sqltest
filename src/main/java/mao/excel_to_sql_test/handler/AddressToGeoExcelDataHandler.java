package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
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
    private BaseConfigurationProperties baseConfigurationProperties;

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


    }
}
