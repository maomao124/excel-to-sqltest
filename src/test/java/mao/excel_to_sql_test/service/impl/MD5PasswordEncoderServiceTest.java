package mao.excel_to_sql_test.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MD5PasswordEncoderService.class})
@ExtendWith(SpringExtension.class)
class MD5PasswordEncoderServiceTest
{
    @Autowired
    private MD5PasswordEncoderService mD5PasswordEncoderService;

    /**
     * Method under test: {@link MD5PasswordEncoderService#encoder(String)}
     */
    @Test
    void testEncoder()
    {
        assertEquals("0e9c20d9b237aecc65de77a491061be5",
                this.mD5PasswordEncoderService.encoder("27c7cf400229103e00c6d8830029e29b"));
        assertEquals("685b0c9f9bbb44711664478b1da888c2",
                this.mD5PasswordEncoderService.encoder("mao.excel_to_sql_test.service.impl.MD5PasswordEncoderService"));
        assertEquals("128be35ca6bd84af0474c270b3297880",
                this.mD5PasswordEncoderService.encoder("27c7cf400229103e00c6d8830029e29b27c7cf400229103e00c6d8830029e29b"));
        assertEquals("2c299a19b8a8d6e3fe615ec07effc467",
                this.mD5PasswordEncoderService
                        .encoder("mao.excel_to_sql_test.service.impl.MD5PasswordEncoderServicemao.excel_to_sql_test.service.impl"
                                + ".MD5PasswordEncoderService"));
    }

    /**
     * Method under test: {@link MD5PasswordEncoderService#verification(String, String)}
     */
    @Test
    void testVerification()
    {
        assertFalse(this.mD5PasswordEncoderService.verification("27c7cf400229103e00c6d8830029e29b",
                "27c7cf400229103e00c6d8830029e29b"));
        assertFalse(this.mD5PasswordEncoderService.verification(
                "mao.excel_to_sql_test.service.impl.MD5PasswordEncoderService", "27c7cf400229103e00c6d8830029e29b"));
        assertTrue(this.mD5PasswordEncoderService.verification("27c7cf400229103e00c6d8830029e29b",
                "0e9c20d9b237aecc65de77a491061be5"));
    }

    /**
     * Method under test: default or parameterless constructor of {@link MD5PasswordEncoderService}
     */
    @Test
    void testConstructor()
    {
        assertEquals("MD5", (new MD5PasswordEncoderService()).getAlgorithmName());
    }
}

