package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.service.PhoneCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(类名): PhoneCreateServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2024/8/3
 * Time(创建时间)： 16:22
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class PhoneCreateServiceImpl implements PhoneCreateService
{
    private static final Logger log = LoggerFactory.getLogger(PhoneCreateServiceImpl.class);

    private static final List<String> prefixList = new ArrayList<>();

    static
    {
        prefixList.add("153");
        prefixList.add("181");
        prefixList.add("133");
        prefixList.add("199");
        prefixList.add("177");
        prefixList.add("150");
        prefixList.add("183");
    }

    @Override
    public String create()
    {
        StringBuilder stringBuilder = new StringBuilder(11);
        stringBuilder.append(prefixList.get(getIntRandom(0, prefixList.size() - 1)));
        for (int i = 0; i < 8; i++)
        {
            stringBuilder.append(getIntRandom(0, 9));
        }
        return stringBuilder.toString();
    }


    /**
     * 得到int随机
     *
     * @param min 分钟
     * @param max 最大值
     * @return int
     */
    public static int getIntRandom(int min, int max)
    {
        if (min > max)
        {
            min = max;
        }
        return min + (int) (Math.random() * (max - min + 1));
    }

}
