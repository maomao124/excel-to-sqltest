package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.utils.id.SnowflakeIdGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Project name(项目名称)：excel-to-sqltest
 * Package(包名): mao.excel_to_sql_test.handler
 * Class(类名): SnowflakeIdExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/12
 * Time(创建时间)： 9:49
 * Version(版本): 1.0
 * Description(描述)： 雪花算法ID生成excel数据处理器
 */

@Service
public class SnowflakeIdExcelDataHandler implements ExcelDataHandler
{

    private static final Logger log = LoggerFactory.getLogger(PasswordEncoderExcelDataHandler.class);


    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.snowflakeIdExcelDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.snowflakeIdExcelDataHandler.order:8}")
    private int order;

    /**
     * 生成的雪花算法ID列的列名称，默认为_sid
     */
    @Value("${ets.handler.excelDataHandler.snowflakeIdExcelDataHandler.filedName:_sid}")
    private String filedName;

    /**
     * 雪花算法的机器码，取值为0-31
     */
    @Value("${ets.handler.excelDataHandler.snowflakeIdExcelDataHandler.machineCode:1}")
    private long machineCode;

    @Override
    public boolean enabled()
    {
        return enable;
    }

    @Override
    public int getOrder()
    {
        return order;
    }

    @Override
    public String getName()
    {
        return "雪花算法ID生成excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        SnowflakeIdGenerate snowflakeIdGenerate = new SnowflakeIdGenerate(machineCode);
        log.debug("机器码：" + machineCode);
        List<Map<String, String>> content = excelData.getContent();
        List<String> titles = excelData.getTitles();
        for (Map<String, String> rowMap : content)
        {
            rowMap.put(filedName, snowflakeIdGenerate.generate().toString());
        }
        //设置表头
        titles.add(0, filedName);
    }
}
