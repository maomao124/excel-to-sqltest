package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Consumer;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(类名): FileServiceImpl
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/12
 * Time(创建时间)： 10:51
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class FileServiceImpl implements FileService
{
    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private BaseConfigurationProperties baseConfigurationProperties;

    @Override
    public void write(List<String> sqlList)
    {
        String path = baseConfigurationProperties.getOutputPath();
        log.debug("文件位置：" + path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter))
        {
            for (String sql : sqlList)
            {
                bufferedWriter.write(sql);
                if (baseConfigurationProperties.getWrapNum() >= 0)
                {
                    for (int i = 0; i < baseConfigurationProperties.getWrapNum(); i++)
                    {
                        bufferedWriter.write("\n");
                    }
                }
                else
                {
                    bufferedWriter.write("\n\n");
                }
                log.debug("写入到文件：" + sql);
            }
            bufferedWriter.flush();
            log.info("写入完成，一共" + sqlList.size() + "条数据");
        }
        catch (Exception e)
        {
            log.error("写入到文件时发生错误：", e);
        }
    }
}
