package mao.excel_to_sql_test.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(测试类名): PhoneCreateServiceImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2024/8/3
 * Time(创建时间)： 16:29
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class PhoneCreateServiceImplTest
{
    @Autowired
    private PhoneCreateServiceImpl phoneCreateService;

    @Test
    void create()
    {
        System.out.println(phoneCreateService.create());
    }

    @Test
    void create2()
    {
        for (int i = 0; i < 1000; i++)
        {
            System.out.println(phoneCreateService.create());
        }
    }

    @Test
    void create3()
    {
        for (int i = 0; i < 100000; i++)
        {
            System.out.println(phoneCreateService.create());
        }
    }
}
