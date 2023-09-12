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
    void write() throws IOException
    {
        List<String> sqlList = sqlService.excelToSql("update base_put set image12='${备注}' where put_id='${编码}';");
        fileService.write(sqlList);
    }
}
