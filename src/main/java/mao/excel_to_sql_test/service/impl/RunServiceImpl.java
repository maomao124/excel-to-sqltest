package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.dao.SqlDao;
import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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

    @Autowired
    private SqlDao sqlDao;

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
    public void showTip()
    {
        log.info("\n\n\n本程序用于将Excel文件里的数据转换成sql脚本，并选择执行!\n" +
                "通过自定义模板来生成想要的sql语句\n" +
                "1. 模板使用freemacker模板引擎实现，变量使用'${表头列名称}'格式来替换\n" +
                "2. freemacker支持加减乘除运算、条件语句、类型转换等，使用方法自行百度\n" +
                "3. 如果表头包含空格，写法'${.vars['CPU Temp']'\n" +
                "4. 数值类型会有逗号，可以使用类型转换，示例：'${(_index?number*2+100000)?c}'\n" +
                "5. 程序包含内置参数'_index'，可以直接使用，从0开始\n" +
                "6. excel的日期类型统一转换成时间戳，想要格式化的时间，可以写成'${Time?number?number_to_datetime?string('yyyy-MM-dd HH:mm:ss')}'\n" +
                "7. 对于excel中性别字段为男或女，但是数据库为0或1的，可以使用if else解决，示例：'insert into student values(${年龄},<#if 性别=='男'>0</#if><#if 性别=='女'>1</#if>)'\n" +
                "8. 数据库配置位于 druid.properties 文件里\n" +
                "9. 如果直接想要使用默认的模板，可以直接按回车即可，使用默认模板时，excel里的表头列名称要满足数据库规范\n" +
                "10.程序通过配置属性类读取配置，配置文件示例如下，可以通过修改application.yml来修改配置，也可以通过参数传递来让配置临时生效\n\n" +
                "ets:\n" +
                "  inputPath: './in.xlsx'    # excel文件的路径\n" +
                "  outputPath: './out.sql'    # sql文件的输出路径\n" +
                "  isWriteToMysql: false      # 是否直接执行输出的sql文件\n" +
                "  wrapNum: 1                 # sql文件两条sql之间换行的数量，也就是\\n的个数\n" +
                "  excel:\n" +
                "    sheetAt: 0               # 读取sheet的索引，从0开始，第一个sheet就是0，第二个sheet就是1\n" +
                "    startRow: 0              # 开始行，选择要从第几行开始读，从0开始，开始行必须为表头，从第4行开始就是3\n" +
                "    endRow: -1               # 结束行，值为-1默认读到最后一行\n" +
                "    startCell: 0             # 开始列，默认从第一列开始读\n" +
                "    endCell: -1              # 结束列，值为-1默认读到最后一列\n" +
                "  filter:                    # 过滤器，过滤掉满足以下条件的行，比如过滤掉‘所属班级’这一列中值为100001的行、过滤‘编码’字段值为‘Noinfo’、‘NotOnly’或者‘N000004-446-1151’的行\n" +
                "    \"[编码]\": ['Noinfo','NotOnly','N000004-446-1151']\n" +
                "    \"[所属班级]\": ['100001']" +
                "\n\n\n");
    }

    @Override
    public void run() throws Exception
    {
        showTip();
        showConfig();
        ExcelData excelData = excelService.loadExcel();
        if (excelData.getContent().size() > 50 && log.isDebugEnabled())
        {
            showTip();
        }
        String defaultTemplate = templateService.buildDefaultTemplate(excelData, "表名");
        log.info("默认模板示例：" + defaultTemplate);
        Scanner input = new Scanner(System.in);
        System.out.print("请输入模板：");
        String template = input.nextLine();
        if (template == null || template.equals(""))
        {
            System.out.print("请输入表名称：");
            String tableName = input.next();
            log.info("表名称：" + tableName);
            log.info("构建模板");
            template = templateService.buildDefaultTemplate(excelData, tableName);
        }
        log.info("模板：" + template);
        log.info("开始生成");
        List<String> sqlList = sqlService.excelToSql(template, excelData);
        log.info("生成完成");
        log.info("开始写入到sql文件");
        fileService.write(sqlList);
        log.info("数据全部写入完成");
        if (baseConfigurationProperties.isWriteToMysql())
        {
            log.info("将sql语句写入到数据库");
            sqlDao.save(sqlList);
        }
        log.info("任务完成");
    }

    @Override
    public void run(String template) throws Exception
    {
        showConfig();
        ExcelData excelData = excelService.loadExcel();
        List<String> sqlList = sqlService.excelToSql(template, excelData);
        fileService.write(sqlList);
        templateService.writeTemplateLog(template);
    }
}
