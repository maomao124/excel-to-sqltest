package mao.excel_to_sql_test.handler;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.service.PasswordEncoderService;
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
 * Class(类名): PasswordEncoderExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/9
 * Time(创建时间)： 15:55
 * Version(版本): 1.0
 * Description(描述)： 密码加密excel数据处理器
 */

@Service
public class PasswordEncoderExcelDataHandler implements ExcelDataHandler
{
    private static final Logger log = LoggerFactory.getLogger(PasswordEncoderExcelDataHandler.class);

    /**
     * 密码加密服务列表
     */
    @Autowired
    List<PasswordEncoderService> passwordEncoderServiceList;

    /**
     * 是否启用此handler，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.passwordEncoderExcelDataHandler.enable:true}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.passwordEncoderExcelDataHandler.order:31}")
    private int order;

    /**
     * 密码字段在表头里的名称，默认为password
     */
    @Value("${ets.handler.excelDataHandler.passwordEncoderExcelDataHandler.filedName:password}")
    private String filedName;

    /**
     * 密码加密算法，默认为BCrypt，目前支持BCrypt、MD5、SHA1、SHA256算法，区分大小写
     */
    @Value("${ets.handler.excelDataHandler.passwordEncoderExcelDataHandler.algorithm:BCrypt}")
    private String algorithm;


    /**
     * 是否加密密码字段，如果为true，则生成所有加密算法的加密字符串，如果为false，则生成algorithm配置对应的加密字符串
     */
    @Value("${ets.handler.excelDataHandler.passwordEncoderExcelDataHandler.isAllTypeEncoder:false}")
    private boolean isAllTypeEncoder;

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
        return "密码加密excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        if (!excelData.getTitles().contains(filedName))
        {
            //没有详细地址字段
            log.info("没有密码字段\"" + filedName + "\"，跳过执行");
            return;
        }
        List<String> titles = excelData.getTitles();
        List<Map<String, String>> content = excelData.getContent();

        if (isAllTypeEncoder)
        {
            //生成全部加密算法的加密字符串
            for (PasswordEncoderService passwordEncoderService : passwordEncoderServiceList)
            {
                String algorithmName = passwordEncoderService.getAlgorithmName();
                String encoderFiledName = filedName + "_" + algorithmName;
                if (titles.contains(encoderFiledName))
                {
                    log.warn("字段冲突! 已存在字段\"" + encoderFiledName + "\"，跳过执行");
                    return;
                }
            }
            for (PasswordEncoderService passwordEncoderService : passwordEncoderServiceList)
            {
                String algorithmName = passwordEncoderService.getAlgorithmName();
                String encoderFiledName = filedName + "_" + algorithmName;
                //开始转换
                int index = 0;
                for (Map<String, String> rowMap : content)
                {
                    String rowPassword = rowMap.get(filedName);
                    index++;
                    if (rowPassword == null || rowPassword.equals(""))
                    {
                        continue;
                    }
                    String encoderPassword = passwordEncoderService.encoder(rowPassword);
                    log.debug(index + "/" + content.size() + " 生成的加密字符串：" + encoderPassword);
                    rowMap.put(encoderFiledName, encoderPassword);
                }
                //设置表头
                titles.add(encoderFiledName);
                log.info(encoderFiledName + "转换完成");
            }
        }
        else
        {
            boolean isHavePasswordEncoderService = false;
            PasswordEncoderService passwordEncoderService = null;
            for (PasswordEncoderService passwordEncoderService1 : passwordEncoderServiceList)
            {
                if (passwordEncoderService1.getAlgorithmName().equals(algorithm))
                {
                    log.info("使用" + algorithm + "算法进行密码加密");
                    isHavePasswordEncoderService = true;
                    passwordEncoderService = passwordEncoderService1;
                    break;
                }
            }
            if (!isHavePasswordEncoderService)
            {
                log.info("暂时不支持加密算法\"" + algorithm + "\"，跳过执行");
                return;
            }
            //生成对应的算法的加密字符串
            if (titles.contains(filedName + "_" + algorithm))
            {
                log.warn("字段冲突! 已存在字段\"" + filedName + "_" + algorithm + "\"，跳过执行");
                return;
            }
            //开始转换
            int index = 0;
            for (Map<String, String> rowMap : content)
            {
                String rowPassword = rowMap.get(filedName);
                index++;
                if (rowPassword == null || rowPassword.equals(""))
                {
                    continue;
                }
                String encoderPassword = passwordEncoderService.encoder(rowPassword);
                log.debug(index + "/" + content.size() + " 生成的加密字符串：" + encoderPassword);
                rowMap.put(filedName + "_" + algorithm, encoderPassword);
            }
            //设置表头
            titles.add(filedName + "_" + algorithm);
        }
    }
}
