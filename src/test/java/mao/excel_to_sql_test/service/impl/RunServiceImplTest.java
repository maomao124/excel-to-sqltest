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
        runService.run("update base_put set put_name='${调整后投放点名称}', " +
                "village_name='${调整后小区/村名称}' where put_code='${投放点编号}' and put_name='${投放点名称}';");
    }

    /**
     * 测试并发问题
     *
     * @throws IOException          异常
     * @throws InterruptedException 异常
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
                            runService.run("insert into gameLog values(${FPS},${Time}," +
                                    "${FrameTime},${CPU Power [W]},${GPU Power [W]});");
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
        runService.run("insert into gameLog values(${FPS}," +
                "${Time?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}," +
                "${FrameTime},${(_index?number*2+100000)?c});");
    }

    @Test
    void run6() throws Exception
    {
        runService.run("insert into student values(${年龄}," +
                "<#if 性别=='男'>0</#if><#if 性别=='女'>1</#if>)");
    }


    @Test
    void run7() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange` " +
                "(`auditing`, `year`, `times`, `type`, `dept_name`, `user_name`, " +
                "`arrange_date`, `code`, `ptype`, `fnc_type`, `adept_id`, `dept_id`," +
                " `memo`, `user_id`, `add_userid`, `add_date`, `modify_userid`," +
                " `modify_date`, `tenant_id`, `arrange_type_id`, `car_arrange_id`)" +
                " VALUES ('0', '${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}'," +
                " 0, '1', '永宁项目', NULL, NULL, NULL, '0', 'ysc', NULL," +
                " 'N000017-100100100001', NULL, NULL, NULL, NULL, NULL, NULL," +
                " 'N000017', NULL, 'jx0${(_index?number+348278714144624062142)?c}');");
    }

    @Test
    void run8() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange_line` " +
                "(`collector_user_id`, `collector_user_name`, `line_name`," +
                " `collector_car_name`, `line_phone`, `add_userid`, `add_date`, " +
                "`modify_userid`, `modify_date`, `tenant_id`, `collector_car_id`," +
                " `car_arrange_id`, `collection_line_id`, `car_arrange_line_id`," +
                " `daily_count`, `api_map_id`, `high_speed`, `down_date`, " +
                "`up_date`, `memo`, `line_time`) VALUES (NULL, '${司机}', NULL, " +
                "'${车牌号}', '${联系电话?number?c}', NULL, '2023-09-19 00:00:00'," +
                " NULL, NULL, 'N000017', 'gzepi22091607'," +
                " (select car_arrange_id FROM car_arrange WHERE " +
                "year='${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}' " +
                "AND dept_name='永宁项目'), NULL, 'lzcd00${(_index?number+205)?c}', " +
                "0, NULL, NULL, NULL, NULL, '永宁项目', '${收运时间段}');");
    }

    @Test
    void run9() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange` (`auditing`, `year`, `times`, " +
                "`type`, `dept_name`, `user_name`, `arrange_date`, `code`, `ptype`, `fnc_type`," +
                " `adept_id`, `dept_id`, `memo`, `user_id`, `add_userid`, `add_date`," +
                " `modify_userid`, `modify_date`, `tenant_id`, `arrange_type_id`, `car_arrange_id`) " +
                "VALUES ('0', '${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}', " +
                "0, '1', '永宁项目', NULL, NULL, NULL, '0', 'ysc', NULL, 'N000017-100100100001', " +
                "NULL, NULL, NULL, NULL, NULL, NULL, 'N000017', NULL, " +
                "'jx0${(_index?number+348278714144624062159)?c}');");
    }

    @Test
    void run10() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange_line` (`collector_user_id`, " +
                "`collector_user_name`, `line_name`, `collector_car_name`, `line_phone`, " +
                "`add_userid`, `add_date`, `modify_userid`, `modify_date`, `tenant_id`, " +
                "`collector_car_id`, `car_arrange_id`, `collection_line_id`," +
                " `car_arrange_line_id`, `daily_count`, `api_map_id`, `high_speed`, `down_date`, `up_date`, " +
                "`memo`, `line_time`)" +
                " VALUES (NULL, '${司机}', NULL, '${车牌号}', " +
                "'${联系电话?number?c}', NULL, '2023-09-19 00:00:00', NULL, NULL, " +
                "'N000017', 'gzepi22091607', " +
                "(select car_arrange_id FROM car_arrange WHERE" +
                " year='${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}' " +
                "AND dept_name='白云车队')," +
                " NULL, 'bycd00${(_index?number+205)?c}', 0, NULL, NULL, NULL, NULL," +
                " '白云车队·', '${收运时间段}');");
    }

    @Test
    void run11() throws Exception
    {
        runService.run("INSERT INTO `th_db`.`car_arrange_line` " +
                "(`collector_user_id`, `collector_user_name`, `line_name`, " +
                "`collector_car_name`, `line_phone`, `add_userid`, " +
                "`add_date`, `modify_userid`, `modify_date`, `tenant_id`, " +
                "`collector_car_id`, `car_arrange_id`, `collection_line_id`, " +
                "`car_arrange_line_id`, `daily_count`, `api_map_id`, " +
                "`high_speed`, `down_date`, `up_date`, `memo`, `line_time`)" +
                " VALUES (NULL, '${司机}', NULL, '${车牌号}', '${联系电话?number?c}'," +
                " NULL, '2023-09-19 00:00:00', NULL, NULL, 'N000017', " +
                "(SELECT dosscard_id FROM car_dosscard WHERE dev_num='${车牌号}'" +
                " AND dept_name='永宁项目'), (select car_arrange_id FROM car_arrange " +
                "WHERE year='${日期?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}' " +
                "AND dept_name='永宁项目'), NULL, 'lzcd00${(_index?number+205)?c}'," +
                " 0, NULL, NULL, NULL, NULL, '永宁项目', '${收运时间段}');");
    }

    /**
     * 插入用户表
     *
     * @throws Exception 异常
     */
    @Test
    void run12() throws Exception
    {
        runService.run("insert into sys_user (user_id,user_name,id_code," +
                "add_date,tenant_id,dept_id,dept_name)" +
                " values('${(_index?number+2207040178)?c}','${司机}','${身份证号码}'," +
                "'2023-09-21 00:00:00','N000017','N000017-100100090020','雷州项目');");
    }

    /**
     * 查询id语句的in内容
     *
     * @throws Exception 异常
     */
    @Test
    void run13() throws Exception
    {
        runService.run("" +
                "'${身份证号码}'," +
                "");
    }

    /**
     * 测试去重和排序
     *
     * @throws Exception 异常
     */
    @Test
    void run14() throws Exception
    {
        runService.run("" +
                "'${FPS}'," +
                "");
    }

    /**
     * 导入雷州一体化部门人员档案信息
     *
     * @throws Exception 异常
     */
    @Test
    void run15() throws Exception
    {
        runService.run("" +
                "insert into sys_user " +
                "(user_id,user_code,tenant_id,add_date,phone_code," +
                "user_name,id_code,sex,nation,politics,linkman_phone," +
                "address,dept_name,dept_id,pory_type,entry_date,contract_limit,driver_code," +
                "native_place,household_registration,now_address,date_this_unit," +
                "subject_name,contract_start_time,contract_end_time,sign_contract_number," +
                "auditing) values('${(_index?number+202210120346)?c}','${联系电话?number?c}'," +
                "'N000017',NOW(),'${联系电话?number?c}','${姓名}','${身份证号码}','" +
                "<#if 性别=='男'>1</#if><#if 性别=='女'>0</#if>','${民族}'," +
                "'<#if 政治面貌=='群众'>3</#if><#if 政治面貌=='共青团员'>2</#if><#if 政治面貌=='中共党员'>" +
                "1</#if><#if 政治面貌=='无党派人士'>4</#if><#if 政治面貌=='民主党派'>5</#if>'," +
                "'${联系电话?number?c}','${户籍地址}','雷州项目'," +
                "(select dept_id from sys_dept where dept_name='雷州项目')," +
                "'${岗位类型}','${入职日期?number?number_to_datetime?string('yyyy/MM/dd')}'," +
                "'${合同期限?number?number_to_datetime?string('yyyy/MM/dd')}','${驾驶证件号}'," +
                "'${籍贯}','<#if 户籍=='本地城镇'>1</#if><#if 户籍=='本地农村'>2</#if><#if 户籍=='外地城镇'>3</#if>" +
                "<#if 户籍=='外地农村'>4</#if>','${现住地址}'," +
                "'${到本单位日期?number?number_to_datetime?string('yyyy/MM/dd')}'," +
                "'${合同主体}','${合同开始日期?number?number_to_datetime?string('yyyy/MM/dd')}'," +
                "'${合同结束日期?number?number_to_datetime?string('yyyy/MM/dd')}'," +
                "'${第几次签合同?number}','1');" +
                "");
    }

    /**
     * 查询雷州一体化部门人员档案信息的in语句内容
     * select * from sys_user where user_id in()
     *
     * @throws Exception 异常
     */
    @Test
    void run16() throws Exception
    {
        runService.run("" +
                "'${(_index?number+202210120346)?c}'," +
                "");
    }


    /**
     * 雷州一体化部门人员档案信息添加移动手机号字段
     *
     * @throws Exception 异常
     */
    @Test
    void run17() throws Exception
    {
        runService.run("" +
                "update sys_user set mob_code='${联系电话?number?c}' where user_id='${(_index?number+202210120346)?c}';" +
                "");
    }

    /**
     * 雷州一体化部门人员档案信息添加duty字段
     *
     * @throws Exception 异常
     */
    @Test
    void run18() throws Exception
    {
        runService.run("" +
                "update sys_user set duty='${岗位类型}' where user_id='${(_index?number+202210120346)?c}';" +
                "");
    }

    /**
     * 雷州一体化部门人员档案信息更新岗位类型
     *
     * @throws Exception 异常
     */
    @Test
    void run19() throws Exception
    {
        runService.run("" +
                "update sys_user set birth_date=CONCAT(SUBSTR(id_code,7,4)," +
                "'-',SUBSTR(id_code,11,2),'-',SUBSTR(id_code,13,2))," +
                "user_type='<#if 岗位类型=='前端保洁工'>sanitation</#if>" +
                "<#if 岗位类型=='驾驶员'>sj</#if><#if 岗位类型=='司乘'>sc</#if>" +
                "<#if 岗位类型=='汽车维修工'>check</#if><#if 岗位类型=='勤杂人员'>qinza</#if>" +
                "<#if 岗位类型=='管理人员'>gl</#if><#if 岗位类型=='其他'>other</#if>" +
                "<#if 岗位类型=='压缩站操作工'>zzz</#if>'" +
                " where user_id='${(_index?number+202210120346)?c}';" +
                "");

    }


    /**
     * 南沙车队收运线路
     *
     * @throws Exception 异常
     */
    @Test
    void run20() throws Exception
    {
        runService.run("" +
                "insert into collection_line (auditing,line_code,line_name,add_date,tenant_id," +
                "collection_line_id,dept_name,dept_id,line_time,line_phone,collector_user_name," +
                "collector_car_name,work_type) values ('1','${线路编号}','${线路名称}',NOW()," +
                "'jxstar','jxstar-${(_index?number+100001)?c}','${管理单位}'," +
                "(select dept_id from sys_dept where dept_name='${管理单位}'),'${收运时间段}'," +
                "'${联系电话}','${司机}','${车牌号}','ysc');" +
                "");

    }


    /**
     * 用户表更新社区名称-投放点
     *
     * @throws Exception 异常
     */
    @Test
    void run21() throws Exception
    {
        runService.run("" +
                "update sys_user set community_name=(select community_name from base_community where community_id='${community_id}')" +
                " where user_id='${user_id}';" +
                "");

    }

    /**
     * 用户表更新小区名称-投放点
     *
     * @throws Exception 异常
     */
    @Test
    void run22() throws Exception
    {
        runService.run("" +
                "update sys_user set village_name=(select village_name from base_village where village_id='${village_id}')" +
                " where user_id='${user_id}';" +
                "");

    }

    /**
     * 用户表更投放点名称-投放点
     *
     * @throws Exception 异常
     */
    @Test
    void run23() throws Exception
    {
        runService.run("" +
                "update sys_user set put_name=(select put_name from base_put where put_id='${put_id}')" +
                " where user_id='${user_id}';" +
                "");

    }


    /**
     * 用户表更名称-投放点
     *
     * @throws Exception 异常
     */
    @Test
    void run24() throws Exception
    {
        runService.run("" +
                "update sys_user set user_name=(CONCAT(" +
                "(SELECT village_name FROM base_village WHERE village_id='${village_id}')," +
                "'-',(SELECT put_name FROM base_put WHERE put_id='${put_id}')))" +
                " where user_id='${user_id}';" +
                "");

    }

    /**
     * 用户表更新社区名称-社区
     *
     * @throws Exception 异常
     */
    @Test
    void run26() throws Exception
    {
        runService.run("" +
                "update sys_user set put_name=NULL , village_name=NULL" +
                ", user_name=(select community_name from base_community where community_id='${community_id}')" +
                ", community_name=(select community_name from base_community where community_id='${community_id}')" +
                " where user_id='${user_id}';" +
                "");

    }

    /**
     * 用户表更新小区名称-小区-空社区
     *
     * @throws Exception 异常
     */
    @Test
    void run27() throws Exception
    {
        runService.run("" +
                "update sys_user set put_name=NULL" +
                ", user_name=(select village_name from base_village where village_id='${village_id}')" +
                ", village_name=(select village_name from base_village where village_id='${village_id}')" +
                " where user_id='${user_id}';" +
                "");

    }

    /**
     * 用户表更新社区名称和id-小区-空社区
     *
     * @throws Exception 异常
     */
    @Test
    void run28() throws Exception
    {
        runService.run("" +
                "update sys_user" +
                " set community_id=(select community_id from base_village where village_id='${village_id}')" +
                ", community_name=(select community_name from base_village where village_id='${village_id}')" +
                " where user_id='${user_id}';" +
                "");

    }

    /**
     * 用户表更新小区名称-小区
     *
     * @throws Exception 异常
     */
    @Test
    void run29() throws Exception
    {
        runService.run("" +
                "update sys_user set put_name=NULL" +
                ", user_name=(select village_name from base_village where village_id='${village_id}')" +
                ", village_name=(select village_name from base_village where village_id='${village_id}')" +
                " where user_id='${user_id}';" +
                "");

    }


    /**
     * 用户表更新社区名称-小区-空社区
     *
     * @throws Exception 异常
     */
    @Test
    void run30() throws Exception
    {
        runService.run("" +
                "update sys_user" +
                " set " +
                "community_name=(select community_name from base_village where village_id='${village_id}')" +
                " where user_id='${user_id}';" +
                "");

    }

    /**
     * 导入合同内容
     *
     * @throws Exception 异常
     */
    @Test
    void run31() throws Exception
    {
        runService.run("" +
                "insert into annual_contract_son (contract_son_id,contract_id," +
                        "cartract_number,son_name,efficient,price,contract_unit,add_date,add_userid,tenant_id) " +
                        "values('${_sid}','N000017-644-102','${序号}','${名称}','${规格}',${价格},'${单位}'," +
                "now(),'N000017-418-1','N000017');"+
                "");

    }

    /**
     * 导入合同内容
     *
     * @throws Exception 异常
     */
    @Test
    void run32() throws Exception
    {
        runService.run("" +
                "insert into annual_contract_son (contract_son_id,contract_id," +
                "cartract_number,son_name,efficient,price,contract_unit,add_date,add_userid,tenant_id) " +
                "values('${_sid}','N000017-569-1','${序号}','${名称}','${规格}',${价格},'${单位}'," +
                "now(),'N000017-418-1','N000017');"+
                "");

    }

    /**
     * 查询用户-in内容
     *
     * @throws Exception 异常
     */
    @Test
    void run33() throws Exception
    {
        runService.run("" +
                "'${user_name}'," +
                "");

    }

    /**
     * 根据详细地址生成经纬度
     *
     * @throws Exception 异常
     */
    @Test
    void run34() throws Exception
    {
        runService.run("" +
                "update base_box set point='${longitude},${latitude}'" +
                " where box_no='${box_no}' and dept_id='${dept_id}';" +
                "");

    }

    /**
     * 生成部门
     *
     * @throws Exception 异常
     */
    @Test
    void run35() throws Exception
    {
        runService.run("" +
                "insert into sys_dept (auditing,dept_id,dept_code,dept_name,dept_level," +
                "is_novalid,add_userid,add_date,tenant_id) " +
                "values('0','${dept_id?number?c}'," +
                "'${dept_code?number?c}','企业${dept_name?number?c}0000-${dept_name?number?c}9999'," +
                "'3','0','${add_userid}',now(),'jxstar');" +
                "");

    }
}
