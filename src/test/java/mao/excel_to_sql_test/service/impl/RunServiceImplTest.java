package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.service.RunService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(测试类名): RunServiceImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/14
 * Time(创建时间)： 10:05
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class RunServiceImplTest
{
    @Autowired
    private RunService runService;

    @Test
    void run() throws Exception
    {
        runService.run("update base_put set put_name='${调整后投放点名称}', village_name='${调整后小区/村名称}' where put_code='${投放点编号}' and put_name='${投放点名称}';");
    }

    /**
     * 测试并发问题
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    void run2() throws IOException, InterruptedException
    {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 32; i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    for (int i = 0; i < 100; i++)
                    {
                        try
                        {
                            runService.run("insert into gameLog values(${FPS},${Time},${FrameTime},${CPU Power [W]},${GPU Power [W]});");
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
    }

    @Test
    void run3() throws Exception
    {
        runService.run("insert into gameLog values(${FPS},${Time},${FrameTime},${(_index?number*2+100000)?c});");
    }

    @Test
    void run4() throws Exception
    {
        runService.run("insert into gameLog values(${FPS},${(_index?number*2+100000)?c},${.vars['CPU Temp']});");
    }

    @Test
    void run5() throws Exception
    {
        runService.run("insert into gameLog values(${FPS},${Time?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')},${FrameTime},${(_index?number*2+100000)?c});");
    }

    @Test
    void run6() throws Exception
    {
        runService.run("insert into student values(${年龄},<#if 性别=='男'>0</#if><#if 性别=='女'>1</#if>)");
    }
}
