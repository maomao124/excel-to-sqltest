package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.service.FileService;
import mao.excel_to_sql_test.service.SqlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(测试类名): FileServiceImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/12
 * Time(创建时间)： 10:59
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class FileServiceImplTest

{
    @Autowired
    private FileService fileService;

    @Autowired
    private SqlService sqlService;

    @Test
    void write() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("update base_put set image12='${备注}' where put_id='${编码}';");
        fileService.write(sqlList);
    }

    /**
     * 查询表
     *
     * @throws IOException IOException
     */
    @Test
    void write2() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("select * from base_village where village_name='${小区/村名称}';");
        fileService.write(sqlList);

    }

    /**
     * in查询
     *
     * @throws IOException IOException
     */
    @Test
    void write3() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("'${小区/村名称}',");
        fileService.write(sqlList);
    }

    /**
     * 更改村名称
     *
     * @throws IOException IOException
     */
    @Test
    void write4() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("update base_village set village_name='${change_village_name}' where village_code='${village_code}' and village_name='${village_name}';");
        fileService.write(sqlList);
    }

    /**
     * 更改村名称回滚
     *
     * @throws IOException IOException
     */
    @Test
    void write5() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("update base_village set village_name='${village_name}' where village_code='${village_code}';");
        fileService.write(sqlList);
    }

    /**
     * 更改投放点
     *
     * @throws IOException IOException
     */
    @Test
    void write6() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("update base_put set put_name='${调整后投放点名称}', village_name='${调整后小区/村名称}' where put_code='${投放点编号}' and put_name='${投放点名称}';");
        fileService.write(sqlList);
    }


    /**
     * 更改投放点回滚
     *
     * @throws IOException IOException
     */
    @Test
    void write7() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("update base_put set put_name='${投放点名称}',village_name='${小区/村名称}' where put_code='${投放点编号}';");
        fileService.write(sqlList);
    }

    /**
     * 验证的in内容
     *
     * @throws IOException
     */
    @Test
    void write8() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("'${投放点编号}',");
        fileService.write(sqlList);
    }

    @Test
    void write9() throws Exception
    {
        List<String> sqlList = sqlService.excelToSql("insert into gameLog values(${FPS},${Time},${FrameTime},${CPU Power [W]},${GPU Power [W]});");
        fileService.write(sqlList);
    }

}
