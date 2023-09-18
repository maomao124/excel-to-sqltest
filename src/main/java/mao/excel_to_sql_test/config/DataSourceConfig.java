package mao.excel_to_sql_test.config;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.config
 * Class(类名): DataSourceConfig
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/18
 * Time(创建时间)： 9:13
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Configuration
public class DataSourceConfig
{

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    @ConditionalOnProperty(value = "ets.isWriteToMysql", havingValue = "true", matchIfMissing = false)
    DataSource dataSource() throws Exception
    {
        log.debug("开始加载druid数据源");
        InputStream inputStream = DataSourceConfig.class.getClassLoader()
                .getResourceAsStream("druid.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return DruidDataSourceFactory.createDataSource(properties);
    }

    @PostConstruct
    public void init()
    {
        log.info("初始化 DataSourceConfig");
    }
}
