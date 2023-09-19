package mao.excel_to_sql_test.dao.Impl;

import mao.excel_to_sql_test.dao.SqlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Connection connection = null;
        try
        {
            log.debug("获取数据库连接");
            connection = dataSource.getConnection();
            log.debug("开启事务");
            connection.setAutoCommit(false);
            for (String sql : sqlList)
            {
                save(sql, connection);
            }
            log.debug("执行成功，提交事务");
            connection.commit();
        }
        catch (Exception e)
        {
            log.error("", e);
            log.debug("产生错误，回滚事务");
            if (connection != null)
            {
                connection.rollback();
            }
        }
        finally
        {
            if (connection != null)
            {
                log.debug("关闭连接");
                connection.close();
            }
        }
        return true;
    }

    /**
     * 执行sql
     *
     * @param sql        sql语句
     * @param connection 连接
     * @return int 影响行数
     * @throws Exception 异常
     */
    private int save(String sql, Connection connection) throws Exception
    {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int update = preparedStatement.executeUpdate();
        if (update != 1)
        {
            log.warn("sql语句'" + sql + "'执行成功，但是影响行数为" + update);
        }
        else
        {
            log.debug("sql语句'" + sql + "'执行成功");
        }
        return update;
    }
}
