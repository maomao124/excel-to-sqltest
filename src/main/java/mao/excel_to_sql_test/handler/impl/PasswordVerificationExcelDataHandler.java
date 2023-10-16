package mao.excel_to_sql_test.handler.impl;

import mao.excel_to_sql_test.entity.ExcelData;
import mao.excel_to_sql_test.handler.ExcelDataHandler;
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
 * Class(类名): PasswordVerificationExcelDataHandler
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/10/9
 * Time(创建时间)： 17:18
 * Version(版本): 1.0
 * Description(描述)： 密码校验excel数据处理器
 */

@Service
public class PasswordVerificationExcelDataHandler implements ExcelDataHandler
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
    @Value("${ets.handler.excelDataHandler.passwordVerificationExcelDataHandler.enable:false}")
    private boolean enable;

    /**
     * 此handler的优先级，从配置文件里读取
     */
    @Value("${ets.handler.excelDataHandler.passwordVerificationExcelDataHandler.order:32}")
    private int order;

    /**
     * 原始密码字段在表头里的名称，默认为password
     */
    @Value("${ets.handler.excelDataHandler.passwordVerificationExcelDataHandler.passwordFiledName:password}")
    private String passwordFiledName;

    /**
     * 加密后密码字段在表头里的名称，默认为password_BCrypt
     */
    @Value("${ets.handler.excelDataHandler.passwordVerificationExcelDataHandler.encoderPasswordFiledName:password_BCrypt}")
    private String encoderPasswordFiledName;

    /**
     * 校验输出结果的字段名称，默认为passwordVerificationResult
     */
    @Value("${ets.handler.excelDataHandler.passwordVerificationExcelDataHandler.verificationResultFiledName:passwordVerificationResult}")
    private String verificationResultFiledName;

    /**
     * 要验证的密码加密算法，默认为BCrypt，目前支持BCrypt、MD5、SHA1、SHA256算法，区分大小写
     */
    @Value("${ets.handler.excelDataHandler.passwordVerificationExcelDataHandler.algorithm:BCrypt}")
    private String algorithm;


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
        return "密码校验excel数据处理器";
    }

    @Override
    public void handler(ExcelData excelData)
    {
        List<String> titles = excelData.getTitles();
        List<Map<String, String>> content = excelData.getContent();
        boolean isHavePasswordEncoderService = false;
        PasswordEncoderService passwordEncoderService = null;
        for (PasswordEncoderService passwordEncoderService1 : passwordEncoderServiceList)
        {
            if (passwordEncoderService1.getAlgorithmName().equals(algorithm))
            {
                log.info("使用" + algorithm + "算法进行密码验证");
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
        //判断是否存在字段
        if (titles.contains(verificationResultFiledName))
        {
            log.warn("字段冲突! 已存在字段\"" + verificationResultFiledName + "\"，跳过执行");
            return;
        }
        //开始校验
        for (Map<String, String> map : content)
        {
            int index = 0;
            index++;
            String passwordFiledValue = map.get(passwordFiledName);
            String encoderPasswordValue = map.get(encoderPasswordFiledName);
            if (passwordFiledValue == null || passwordFiledValue.equals(""))
            {
                continue;
            }
            if (encoderPasswordValue == null || encoderPasswordValue.equals(""))
            {
                continue;
            }
            boolean verification = passwordEncoderService.verification(passwordFiledValue, encoderPasswordValue);
            log.debug(index + "/" + content.size() + " 验证结果：" + verification);
            map.put(verificationResultFiledName, String.valueOf(verification));
        }
        //设置表头
        titles.add(verificationResultFiledName);
    }
}
