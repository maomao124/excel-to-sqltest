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


    @Test
    void run7() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange` (`auditing`, `year`, `times`, `type`, `dept_name`, `user_name`, `arrange_date`, `code`, `ptype`, `fnc_type`, `adept_id`, `dept_id`, `memo`, `user_id`, `add_userid`, `add_date`, `modify_userid`, `modify_date`, `tenant_id`, `arrange_type_id`, `car_arrange_id`) VALUES ('0', '${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}', 0, '1', '永宁项目', NULL, NULL, NULL, '0', 'ysc', NULL, 'N000017-100100100001', NULL, NULL, NULL, NULL, NULL, NULL, 'N000017', NULL, 'jx0${(_index?number+348278714144624062142)?c}');");
    }

    @Test
    void run8() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange_line` (`collector_user_id`, `collector_user_name`, `line_name`, `collector_car_name`, `line_phone`, `add_userid`, `add_date`, `modify_userid`, `modify_date`, `tenant_id`, `collector_car_id`, `car_arrange_id`, `collection_line_id`, `car_arrange_line_id`, `daily_count`, `api_map_id`, `high_speed`, `down_date`, `up_date`, `memo`, `line_time`) VALUES (NULL, '${司机}', NULL, '${车牌号}', '${联系电话?number?c}', NULL, '2023-09-19 00:00:00', NULL, NULL, 'N000017', 'gzepi22091607', (select car_arrange_id FROM car_arrange WHERE year='${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}' AND dept_name='永宁项目'), NULL, 'lzcd00${(_index?number+205)?c}', 0, NULL, NULL, NULL, NULL, '永宁项目', '${收运时间段}');");
    }

    @Test
    void run9() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange` (`auditing`, `year`, `times`, `type`, `dept_name`, `user_name`, `arrange_date`, `code`, `ptype`, `fnc_type`, `adept_id`, `dept_id`, `memo`, `user_id`, `add_userid`, `add_date`, `modify_userid`, `modify_date`, `tenant_id`, `arrange_type_id`, `car_arrange_id`) " +
                "VALUES ('0', '${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}', 0, '1', '永宁项目', NULL, NULL, NULL, '0', 'ysc', NULL, 'N000017-100100100001', NULL, NULL, NULL, NULL, NULL, NULL, 'N000017', NULL, 'jx0${(_index?number+348278714144624062159)?c}');");
    }

    @Test
    void run10() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange_line` (`collector_user_id`, `collector_user_name`, `line_name`, `collector_car_name`, `line_phone`, `add_userid`, `add_date`, `modify_userid`, `modify_date`, `tenant_id`, `collector_car_id`, `car_arrange_id`, `collection_line_id`," +
                " `car_arrange_line_id`, `daily_count`, `api_map_id`, `high_speed`, `down_date`, `up_date`, " +
                "`memo`, `line_time`)" +
                " VALUES (NULL, '${司机}', NULL, '${车牌号}', " +
                "'${联系电话?number?c}', NULL, '2023-09-19 00:00:00', NULL, NULL, " +
                "'N000017', 'gzepi22091607', " +
                "(select car_arrange_id FROM car_arrange WHERE year='${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}' AND dept_name='白云车队')," +
                " NULL, 'bycd00${(_index?number+205)?c}', 0, NULL, NULL, NULL, NULL, '白云车队·', '${收运时间段}');");
    }

    @Test
    void run11() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange_line` (`collector_user_id`, `collector_user_name`, `line_name`, `collector_car_name`, `line_phone`, `add_userid`, `add_date`, `modify_userid`, `modify_date`, `tenant_id`, `collector_car_id`, `car_arrange_id`, `collection_line_id`, `car_arrange_line_id`, `daily_count`, `api_map_id`, `high_speed`, `down_date`, `up_date`, `memo`, `line_time`) VALUES (NULL, '${司机}', NULL, '${车牌号}', '${联系电话?number?c}', NULL, '2023-09-19 00:00:00', NULL, NULL, 'N000017', (SELECT dosscard_id FROM car_dosscard WHERE dev_num='${车牌号}' AND dept_name='永宁项目'), (select car_arrange_id FROM car_arrange WHERE year='${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}' AND dept_name='永宁项目'), NULL, 'lzcd00${(_index?number+205)?c}', 0, NULL, NULL, NULL, NULL, '永宁项目', '${收运时间段}');");
    }

    /**
     * 插入用户表
     *
     * @throws Exception
     */
    @Test
    void run12() throws Exception
    {
        runService.run("insert into sys_user (user_id,user_name,id_code,add_date,tenant_id,dept_id,dept_name) values('${(_index?number+2207040178)?c}','${司机}','${身份证号码}','2023-09-21 00:00:00','N000017','N000017-100100090020','雷州项目');");
    }

    /**
     * 查询id语句的in内容
     *
     * @throws Exception
     */
    @Test
    void run13() throws Exception
    {
        runService.run("" +
                "'${身份证号码}'," +
                ""); }

    /**
     * 测试去重和排序
     * @throws Exception
     */
    @Test
    void run14() throws Exception
    {
        runService.run("" +
                "'${FPS}'," +
                ""); }

}
