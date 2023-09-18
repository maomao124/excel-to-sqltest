package mao.excel_to_sql_test.dao.Impl;

import mao.excel_to_sql_test.dao.SqlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.dao.Impl
 * Class(类名): BaseJdbcSqlDaoImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/18
 * Time(创建时间)： 14:01
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Repository
public class BaseJdbcSqlDaoImpl implements SqlDao
{
    private static final Logger log = LoggerFactory.getLogger(BaseJdbcSqlDaoImpl.class);

    @Autowired(required = false)
    private DataSource dataSource;

    @Override
    public boolean save(List<String> sqlList) throws Exception
    {
        log.info("开始执行sql");
        //todo
        return true;
    }
}
