package mao.excel_to_sql_test.service.impl;

import mao.excel_to_sql_test.service.PasswordEncoderService;
import mao.excel_to_sql_test.utils.password.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Project name(项目名称)：authority
 * Package(包名): mao.auth_server.service.auth.impl
 * Class(类名): SHA1PasswordEncoderService
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/11/10
 * Time(创建时间)： 22:54
 * Version(版本): 1.0
 * Description(描述)： 无
 */

@Service
public class SHA1PasswordEncoderService implements PasswordEncoderService
{
    private static final Logger log = LoggerFactory.getLogger(SHA1PasswordEncoderService.class);

    @Override
    public String getAlgorithmName()
    {
        return "SHA1";
    }

    @Override
    public String encoder(String rawPassword)
    {
        return SHA1.getSHA1(rawPassword);
    }

    @Override
    public boolean verification(String rawPassword, String encodePassword)
    {
        return SHA1.getSHA1(rawPassword).equals(encodePassword);
    }
}
