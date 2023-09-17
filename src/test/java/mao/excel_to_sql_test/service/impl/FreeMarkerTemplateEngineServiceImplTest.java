package mao.excel_to_sql_test.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(测试类名): FreeMarkerTemplateEngineServiceImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/17
 * Time(创建时间)： 11:45
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class FreeMarkerTemplateEngineServiceImplTest
{
    @Autowired
    private FreeMarkerTemplateEngineServiceImpl freeMarkerTemplateEngineService;

    @Test
    void parse() throws Exception
    {
        System.out.println(freeMarkerTemplateEngineService.parse("${1+1}", new HashMap<>()));
    }

    @Test
    void parse2() throws Exception
    {
        System.out.println(freeMarkerTemplateEngineService.parse("<#if !stus??>\n" +
                "    变量 stus 为null\n" +
                "</#if>" , new HashMap<>()));
    }

    @Test
    void parse3() throws Exception
    {
        System.out.println(freeMarkerTemplateEngineService.parse("${'300.001'?number+1}" , new HashMap<>()));
    }
}
