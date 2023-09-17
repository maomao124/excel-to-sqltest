package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.service.SqlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl.impl
 * Class(测试类名): SqlServiceImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/11
 * Time(创建时间)： 11:26
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class SqlServiceImplTest
{
    @Autowired
    private SqlService sqlService;

    @Test
    void excelToSql() throws Exception
    {
        sqlService.excelToSql("insert into tableName values(${姓名},${性别},${年龄})");
    }
    @Test
    void excelToSql2() throws Exception
    {
        sqlService.excelToSql("insert into gameLog values(${FPS},${Time},${FrameTime},${CPU Power [W]},${GPU Power [W]});");
    }
}
