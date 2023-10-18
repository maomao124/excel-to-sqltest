package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.config.BaseConfigurationProperties;
import mao.excel_to_sql_test.dao.SqlDao;
import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.handler.ExcelDataHandler;
import mao.excel_to_sql_test.handler.SqlDataHandler;
import mao.excel_to_sql_test.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
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
                "10.程序通过配置属性类读取配置，配置文件示例如下，可以通过修改application.yml来修改配置，也可以通过参数传递来让配置临时生效\n" +
                "11.如果需要对数据进行加工处理，可以实现ExcelDataHandler或者SqlDataHandler接口来处理excel或者sql数据，可以参考AddressToGeoExcelDataHandler\n" +
                "\n\n" +
                "ets:\n" +
                "  inputPath: './in6.xlsx'    # excel文件的路径\n" +
                "  outputPath: './out.sql'    # sql文件的输出路径\n" +
                "  appendTime: false          # 输出文件的文件名的后面是否追加时间信息\n" +
                "  appendTimeFormat: 'YYYYMMddHHmmss'   # 输出文件的文件名的后面追加时间信息的格式\n" +
                "  isWriteToMysql: false      # 是否直接执行输出的sql文件\n" +
                "  wrapNum: 1                 # sql文件两条sql之间换行的数量，也就是\\n的个数\n" +
                "  apiKey: 'hNrSgpC76vhq05xxxxxxxxxxxxxxx'   # 百度地图ak，用于通过详细地址生成经纬度信息\n" +
                "  #distinct: ''               # 要去重的字段\n" +
                "  #orderBy: 'FPS,desc'        # 要排序的列，列为表头名称，升序为asc，降序为desc，按姓名降序：'姓名,desc'，按成绩升序：'成绩,asc'\n" +
                "  excel:\n" +
                "    sheetAt: 0               # 读取sheet的索引，从0开始，第一个sheet就是0，第二个sheet就是1\n" +
                "    startRow: 0              # 开始行，选择要从第几行开始读，从0开始，开始行必须为表头，从第4行开始就是3\n" +
                "    endRow: -1               # 结束行，值为-1默认读到最后一行\n" +
                "    startCell: 0             # 开始列，默认从第一列开始读\n" +
                "    endCell: -1              # 结束列，值为-1默认读到最后一列\n" +
                "  filter: # 过滤器，过滤掉满足以下条件的行，比如过滤掉‘所属班级’这一列中值为100001的行、过滤‘编码’字段值为‘Noinfo’、‘NotOnly’或者‘N000004-446-1151’的行\n" +
                "    \"[编码]\": [ 'Noinfo','NotOnly','N000004-446-1151' ]\n" +
                "    \"[所属班级]\": [ '100001' ]\n" +
                "    \"[联系电话]\": [ '' ]\n" +
                "  handler:                   # 处理器\n" +
                "    excelDataHandler:        # excel数据处理器\n" +
                "      ignoreRowExcelDataHandler:  # 忽略行excel数据处理器，filter为此处理器的配置项\n" +
                "        enable: true              # 是否启用此处理器\n" +
                "        order: 0                  # 执行的优先级，数字越低，优先级越高，越先执行\n" +
                "      distinctExcelDataHandler:   # 字段去重excel数据处理器，distinct为此处理器的配置项\n" +
                "        enable: true\n" +
                "        order: 1\n" +
                "      orderByExcelDataHandler:    # 字段排序excel数据处理器，orderBy为此处理器的配置项\n" +
                "        enable: true\n" +
                "        order: 2\n" +
                "      snowflakeIdExcelDataHandler: # 雪花算法ID生成excel数据处理器，filedName和machineCode为此处理器的配置项\n" +
                "        enable: true\n" +
                "        order: 8\n" +
                "        filedName: '_sid'       # 生成的雪花算法ID列的列名称，默认为_sid\n" +
                "        machineCode: 1          # 雪花算法的机器码，取值为0-31\n" +
                "      addressToGeoExcelDataHandler: # 详细地址转经纬度excel数据处理器，filedName为此处理器的配置项\n" +
                "        enable: true\n" +
                "        order: 20\n" +
                "        filedName: 'address'       # 详细地址字段名称\n" +
                "      passwordEncoderExcelDataHandler: # 密码加密excel数据处理器\n" +
                "        enable: false\n" +
                "        order: 31\n" +
                "        filedName: 'password'       # 密码字段在表头里的名称，默认为password\n" +
                "        algorithm: 'BCrypt'         # 密码加密算法，默认为BCrypt，目前支持BCrypt、MD5、SHA1、SHA256算法，区分大小写\n" +
                "        isAllTypeEncoder: true   # 是否加密密码字段，如果为true，则生成所有加密算法的加密字符串，如果为false，则生成algorithm配置对应的加密字符串\n" +
                "      passwordVerificationExcelDataHandler: # 密码校验excel数据处理器\n" +
                "        enable: false\n" +
                "        order: 32\n" +
                "        passwordFiledName: 'password'       # 密码字段在表头里的名称，默认为password\n" +
                "        encoderPasswordFiledName: 'password_BCrypt'  # 加密后密码字段在表头里的名称，默认为password_BCrypt\n" +
                "        verificationResultFiledName: 'passwordVerificationResult'    # 校验输出结果的字段名称，默认为passwordVerificationResult\n" +
                "        algorithm: 'BCrypt'         # 密码加密算法，默认为BCrypt，目前支持BCrypt、MD5、SHA1、SHA256算法，区分大小写\n" +
                "      printExcelDataHandler:      # 数据打印excel数据处理器\n" +
                "        enable: true\n" +
                "        order: 9998\n" +
                "      saveExcelDataHandler:       # 保存excel数据处理器\n" +
                "        enable: true\n" +
                "        order: 9999\n" +
                "    sqlDataHandler:           # sql数据处理器\n" +
                "      transitionSqlDataHandler: # 事务sql数据处理器\n" +
                "        enable: true\n" +
                "        order: 10\n" +
                "      printSqlDataHandler:     # 数据打印sql数据处理器\n" +
                "        enable: true\n" +
                "        order: 9998" +
                "\n\n\n");
    }

    @Override
    public void run() throws Exception
    {
        showTip();
        showConfig();
        ExcelData excelData = getExcelData();
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
        templateService.writeTemplateLog(template);
        if (baseConfigurationProperties.isWriteToMysql())
        {
            log.info("将sql语句写入到数据库");
            sqlDao.save(sqlList);
        }
        else
        {
            log.info("跳过执行sql步骤");
        }
        log.info("任务完成");
    }


    @Override
    public void run(String template) throws Exception
    {
        showConfig();
        ExcelData excelData = getExcelData();
        List<String> sqlList = sqlService.excelToSql(template, excelData);
        fileService.write(sqlList);
        templateService.writeTemplateLog(template);
        if (baseConfigurationProperties.isWriteToMysql())
        {
            log.info("将sql语句写入到数据库");
            sqlDao.save(sqlList);
        }
        else
        {
            log.info("跳过执行sql步骤");
        }
        log.info("任务完成");
    }

    private ExcelData getExcelData() throws IOException
    {
        return excelService.loadExcel();
    }
}
