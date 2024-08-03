package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.service.DevNumCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(类名): DevNumCreateServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2024/8/3
 * Time(创建时间)： 16:14
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class DevNumCreateServiceImpl implements DevNumCreateService
{
    private static final Logger log = LoggerFactory.getLogger(DevNumCreateServiceImpl.class);

    @Override
    public String create(String prefix)
    {
        Random random = new Random();
        StringBuilder licensePlate = new StringBuilder();

        licensePlate.append((char) (random.nextInt(26) + 'A'));
        licensePlate.append((char) (random.nextInt(26) + 'A'));

        for (int i = 0; i < 5; i++)
        {
            licensePlate.append(random.nextInt(10));
        }
        return prefix + licensePlate;
    }
}
