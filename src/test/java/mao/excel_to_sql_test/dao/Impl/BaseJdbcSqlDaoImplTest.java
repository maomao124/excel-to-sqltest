package mao.excel_to_sql_test.dao.Impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.dao.Impl
 * Class(测试类名): BaseJdbcSqlDaoImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/19
 * Time(创建时间)： 9:13
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class BaseJdbcSqlDaoImplTest
{

    @Autowired
    private BaseJdbcSqlDaoImpl baseJdbcSqlDao;

    @Test
    void save() throws Exception
    {
        baseJdbcSqlDao.save(new ArrayList<>());
    }
}
