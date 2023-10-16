package mao.excel_to_sql_test.service.impl;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {BCryptPasswordEncoderService.class})
@ExtendWith(SpringExtension.class)
class BCryptPasswordEncoderServiceTest
{
    @Autowired
    private BCryptPasswordEncoderService bCryptPasswordEncoderService;

    /**
     * Method under test: default or parameterless constructor of {@link BCryptPasswordEncoderService}
     */
    @Test
    void testConstructor()
    {
        assertEquals("BCrypt", (new BCryptPasswordEncoderService()).getAlgorithmName());
    }

    /**
     * Method under test: {@link BCryptPasswordEncoderService#encoder(String)}
     */
    @Test
    void testEncoder()
    {
        System.out.println("------");
        System.out.println(this.bCryptPasswordEncoderService.encoder("iloveyou"));
        System.out.println("------");
    }

    /**
     * Method under test: {@link BCryptPasswordEncoderService#verification(String, String)}
     */
    @Test
    void testVerification()
    {
        assertFalse(this.bCryptPasswordEncoderService.verification("iloveyou", "secret"));
        assertFalse(this.bCryptPasswordEncoderService.verification("iloveyou", ""));
    }

    /**
     * Method under test: {@link BCryptPasswordEncoderService#verification(String, String)}
     */
    @Test
    void testVerification2()
    {
        assertTrue(this.bCryptPasswordEncoderService.verification("iloveyou",
                "$2a$10$WsYYIBpyMJMr1NODobmgJ.F.ByaiFokS9Id5g/CeSLyLaw9nZ8Rqq"));
    }
}

