package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(类名): TemplateServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/12
 * Time(创建时间)： 20:12
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class TemplateServiceImpl implements TemplateService
{
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;

    @Override
    public void writeTemplateLog(String template) throws IOException
    {
        String log = null;
        String format = simpleDateFormat.format(new Date());
        String inputPath = baseConfigurationProperties.getInputPath();
        log = format + "\t\t" + inputPath + "\t\t" + template;
        String path = baseConfigurationProperties.getTemplateLogPath();
        logger.debug("日志输出文件位置：" + path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path, true);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter))
        {
            bufferedWriter.write(log);
            bufferedWriter.write("\n");
            bufferedWriter.flush();
            logger.debug("输出日志：'" + log + "'");
        }
        catch (Exception e)
        {
            logger.error("写入到文件时发生错误：", e);
        }
    }
}
