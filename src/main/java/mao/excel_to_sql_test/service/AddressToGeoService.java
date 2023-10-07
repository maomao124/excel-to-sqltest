package mao.excel_to_sql_test.service;


import mao.excel_to_sql_test.entity.Geo;

/**
 * Project name(项目名称)：addressToGeo
 * Package(包名): mao.address_to_geo.service
 * Interface(接口名): AddressToGeoService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/5
 * Time(创建时间)： 11:07
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public interface AddressToGeoService
{
    /**
     * 详细位置转经纬度
     *
     * @param address 详细地址
     * @return {@link Geo} 经纬度信息
     */
    Geo addressToGeo(String address);
}
