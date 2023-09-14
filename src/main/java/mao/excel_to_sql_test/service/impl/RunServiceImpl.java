package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(类名): RunServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/13
 * Time(创建时间)： 17:02
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class RunServiceImpl implements RunService
{
    private static final Logger log = LoggerFactory.getLogger(RunServiceImpl.class);

    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private SqlService sqlService;

    @Autowired
    private FileService fileService;

    @Autowired
    private TemplateService templateService;

    @Override
    public void showConfig()
    {
        log.info("excel文件位置：" + baseConfigurationProperties.getInputPath());
        log.info("sql文件输出位置：" + baseConfigurationProperties.getOutputPath());
        log.info("模板日志输出位置：" + baseConfigurationProperties.getTemplateLogPath());
        log.info("SQL之间换行数量：" + baseConfigurationProperties.getWrapNum());
        log.info("是否写入到数据库：" + baseConfigurationProperties.isWriteToMysql());
        log.info("Excel配置：" + baseConfigurationProperties.getExcel());
        log.info("过滤器配置：" + baseConfigurationProperties.getFilter());
    }

    @Override
    public void run()
    {

    }

    @Override
    public void run(String template) throws IOException
    {
        showConfig();
        ExcelData excelData = excelService.loadExcel();
        List<String> sqlList = sqlService.excelToSql(template, excelData);
        fileService.write(sqlList);
        templateService.writeTemplateLog(template);
    }
}
