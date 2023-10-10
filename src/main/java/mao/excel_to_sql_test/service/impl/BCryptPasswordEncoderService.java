package mao.excel_to_sql_test.service.impl;


import mao.excel_to_sql_test.service.PasswordEncoderService;
import mao.excel_to_sql_test.utils.password.BCryptPasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Project name(项目名称)：authority
 * Package(包名): mao.auth_server.service.auth.impl
 * Class(类名): BCryptPasswordEncoderService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/11/10
 * Time(创建时间)： 20:50
 * Version(版本): 1.0
 * Description(描述)： 密码加密和验证服务
 */

@Service
public class BCryptPasswordEncoderService implements PasswordEncoderService
{
    private static final Logger log = LoggerFactory.getLogger(BCryptPasswordEncoderService.class);

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptPasswordEncoderService()
    {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String getAlgorithmName()
    {
        return "BCrypt";
    }

    @Override
    public String encoder(String rawPassword)
    {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    @Override
    public boolean verification(String rawPassword, String encodePassword)
    {
        return bCryptPasswordEncoder.matches(rawPassword, encodePassword);
    }
}
