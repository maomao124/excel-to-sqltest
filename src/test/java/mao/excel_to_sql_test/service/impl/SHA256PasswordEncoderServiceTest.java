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

@ContextConfiguration(classes = {SHA256PasswordEncoderService.class})
@ExtendWith(SpringExtension.class)
class SHA256PasswordEncoderServiceTest
{
    @Autowired
    private SHA256PasswordEncoderService sHA256PasswordEncoderService;

    /**
     * Method under test: {@link SHA256PasswordEncoderService#encoder(String)}
     */
    @Test
    void testEncoder()
    {
        assertEquals("e4ad93ca07acb8d908a3aa41e920ea4f4ef4f26e7f86cf8291c5db289780a5ae",
                this.sHA256PasswordEncoderService.encoder("iloveyou"));
        assertNull(this.sHA256PasswordEncoderService.encoder(""));
    }

    /**
     * Method under test: {@link SHA256PasswordEncoderService#verification(String, String)}
     */
    @Test
    void testVerification()
    {
        assertFalse(this.sHA256PasswordEncoderService.verification("iloveyou", "secret"));
        assertTrue(this.sHA256PasswordEncoderService.verification("iloveyou",
                "e4ad93ca07acb8d908a3aa41e920ea4f4ef4f26e7f86cf8291c5db289780a5ae"));
    }

    /**
     * Method under test: {@link SHA256PasswordEncoderService#verification(String, String)}
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
        //       at mao.excel_to_sql_test.service.impl.SHA256PasswordEncoderService.verification(SHA256PasswordEncoderService.java:42)
        //   In order to prevent verification(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   verification(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        this.sHA256PasswordEncoderService.verification("", "secret");
    }

    /**
     * Method under test: default or parameterless constructor of {@link SHA256PasswordEncoderService}
     */
    @Test
    void testConstructor()
    {
        assertEquals("SHA256", (new SHA256PasswordEncoderService()).getAlgorithmName());
    }
}

