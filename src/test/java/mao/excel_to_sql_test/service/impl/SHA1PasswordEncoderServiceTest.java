package mao.excel_to_sql_test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SHA1PasswordEncoderService.class})
@ExtendWith(SpringExtension.class)
class SHA1PasswordEncoderServiceTest
{
    @Autowired
    private SHA1PasswordEncoderService sHA1PasswordEncoderService;

    /**
     * Method under test: {@link SHA1PasswordEncoderService#encoder(String)}
     */
    @Test
    void testEncoder()
    {
        assertEquals("ee8d8728f435fd550f83852aabab5234ce1da528", this.sHA1PasswordEncoderService.encoder("iloveyou"));
        assertNull(this.sHA1PasswordEncoderService.encoder(""));
    }

    /**
     * Method under test: {@link SHA1PasswordEncoderService#verification(String, String)}
     */
    @Test
    void testVerification()
    {
        assertFalse(this.sHA1PasswordEncoderService.verification("iloveyou", "secret"));
        assertTrue(this.sHA1PasswordEncoderService.verification("iloveyou", "ee8d8728f435fd550f83852aabab5234ce1da528"));
    }

    /**
     * Method under test: {@link SHA1PasswordEncoderService#verification(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testVerification2()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "Object.equals(Object)" because "obj" is null
        //       at mao.excel_to_sql_test.service.impl.SHA1PasswordEncoderService.verification(SHA1PasswordEncoderService.java:42)
        //   In order to prevent verification(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   verification(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        this.sHA1PasswordEncoderService.verification("", "secret");
    }

    /**
     * Method under test: default or parameterless constructor of {@link SHA1PasswordEncoderService}
     */
    @Test
    void testConstructor()
    {
        assertEquals("SHA1", (new SHA1PasswordEncoderService()).getAlgorithmName());
    }
}

