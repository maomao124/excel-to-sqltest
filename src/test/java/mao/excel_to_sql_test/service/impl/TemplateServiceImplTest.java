package mao.excel_to_sql_test.service.impl;

import freemarker.template.TemplateException;
import mao.excel_to_sql_test.service.TemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.service.impl
 * Class(测试类名): TemplateServiceImplTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/9/13
 * Time(创建时间)： 9:04
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

@SpringBootTest
class TemplateServiceImplTest
{

    @Autowired
    private TemplateService templateService;

    @Test
    void writeTemplateLog() throws IOException
    {
        templateService.writeTemplateLog("insert into tableName values(${姓名},${性别},${年龄})");
    }

    @Test
    void buildDefaultTemplate()
    {
        List<String> list = new ArrayList<>();
        list.add("id");
        list.add("name");
        list.add("sex");
        list.add("age");
        System.out.println(templateService.buildDefaultTemplate(list, "student"));
    }

    @Test
    void parseTemplate() throws Exception
    {
        Map<String, String> map = new HashMap<>();
        map.put("tableName", "stu");
        map.put("名字", "张三");
        String s = templateService.parseTemplate("insert into ${tableName} values('${名字}');", map);
        System.out.println(s);
    }

    @Test
    void parseTemplate2() throws Exception
    {
        Map<String, String> map = new HashMap<>();
        map.put("tableName", "stu");
        map.put("名字", "张三");
        String s = templateService.parseTemplate("insert into ${'tb_'+tableName} values('${名字}');", map);
        System.out.println(s);
    }

    @Test
    void parseTemplate3() throws Exception
    {
        Map<String, String> map = new HashMap<>();
        map.put("tableName", "stu");
        map.put("名字", "张三");
        String s = templateService.parseTemplate("insert into ${'tb_'+tableName} ${1+1} values('${名字}');", map);
        System.out.println(s);
    }
}
