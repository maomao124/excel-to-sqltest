package mao.excel_to_sql_test.utils.password;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cn.hutool.core.text.StrBuilder;

import java.security.SecureRandom;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class BCryptPasswordEncoderTest
{
    /**
     * Method under test: {@link BCryptVersion#valueOf(String)}
     */
    @Test
    void testBCryptVersionValueOf()
    {
        assertEquals("$2a", BCryptPasswordEncoder.BCryptVersion.valueOf("$2A").getVersion());
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder()}
     */
    @Test
    void testConstructor()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder();
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(int)}
     */
    @Test
    void testConstructor2()
    {
        assertThrows(IllegalArgumentException.class, () -> new BCryptPasswordEncoder(1));
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(int)}
     */
    @Test
    void testConstructor3()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(-1);
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(int)}
     */
    @Test
    void testConstructor4()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(4);
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(int, SecureRandom)}
     */
    @Test
    void testConstructor5()
    {
        assertThrows(IllegalArgumentException.class, () -> new BCryptPasswordEncoder(1, new SecureRandom()));

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(int, SecureRandom)}
     */
    @Test
    void testConstructor6()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(-1, new SecureRandom());

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(int, SecureRandom)}
     */
    @Test
    void testConstructor7()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(4, new SecureRandom());

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion)}
     */
    @Test
    void testConstructor8()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A);
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion)}
     */
    @Test
    void testConstructor9()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y);
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion)}
     */
    @Test
    void testConstructor10()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B);
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int)}
     */
    @Test
    void testConstructor11()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 1));

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int)}
     */
    @Test
    void testConstructor12()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, -1);

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int)}
     */
    @Test
    void testConstructor13()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 4);

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int)}
     */
    @Test
    void testConstructor14()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, 1));

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int)}
     */
    @Test
    void testConstructor15()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, 1));

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int, SecureRandom)}
     */
    @Test
    void testConstructor16()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 1, new SecureRandom()));

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int, SecureRandom)}
     */
    @Test
    void testConstructor17()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, -1, new SecureRandom());

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int, SecureRandom)}
     */
    @Test
    void testConstructor18()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, 4, new SecureRandom());

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int, SecureRandom)}
     */
    @Test
    void testConstructor19()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, 1, new SecureRandom()));

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, int, SecureRandom)}
     */
    @Test
    void testConstructor20()
    {
        assertThrows(IllegalArgumentException.class,
                () -> new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, 1, new SecureRandom()));

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, SecureRandom)}
     */
    @Test
    void testConstructor21()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A, new SecureRandom());

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, SecureRandom)}
     */
    @Test
    void testConstructor22()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y, new SecureRandom());

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion, SecureRandom)}
     */
    @Test
    void testConstructor23()
    {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     BCryptPasswordEncoder.BCRYPT_PATTERN
        //     BCryptPasswordEncoder.logger
        //     BCryptPasswordEncoder.strength
        //     BCryptPasswordEncoder.version
        //     BCryptPasswordEncoder.random

        new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, new SecureRandom());

    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#encode(CharSequence)}
     */
    @Test
    void testEncode()
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by encode(CharSequence)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        bCryptPasswordEncoder.encode(StrBuilder.create());
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#encode(CharSequence)}
     */
    @Test
    void testEncode2()
    {
        assertThrows(IllegalArgumentException.class, () -> (new BCryptPasswordEncoder()).encode(null));
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#matches(CharSequence, String)}
     */
    @Test
    void testMatches()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertFalse(bCryptPasswordEncoder.matches(StrBuilder.create(), "secret"));
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#matches(CharSequence, String)}
     */
    @Test
    void testMatches2()
    {
        assertThrows(IllegalArgumentException.class, () -> (new BCryptPasswordEncoder()).matches(null, null));
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#matches(CharSequence, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testMatches3()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: Bad number of rounds
        //       at mao.excel_to_sql_test.utils.password.BCrypt.crypt_raw(BCrypt.java:560)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.hashpw(BCrypt.java:698)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.hashpwforcheck(BCrypt.java:607)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.checkpw(BCrypt.java:826)
        //       at mao.excel_to_sql_test.utils.password.BCryptPasswordEncoder.matches(BCryptPasswordEncoder.java:166)
        //   In order to prevent matches(CharSequence, String)
        //   from throwing IllegalArgumentException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   matches(CharSequence, String).
        //   See https://diff.blue/R013 to resolve this issue.

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        bCryptPasswordEncoder.matches(StrBuilder.create(), "$2a$99$UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#matches(CharSequence, String)}
     */
    @Test
    void testMatches4()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertFalse(bCryptPasswordEncoder.matches(StrBuilder.create(), null));
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#matches(CharSequence, String)}
     */
    @Test
    void testMatches5()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertFalse(bCryptPasswordEncoder.matches(StrBuilder.create(), ""));
    }

    /**
     * Method under test: {@link BCryptPasswordEncoder#upgradeEncoding(String)}
     */
    @Test
    void testUpgradeEncoding()
    {
        assertThrows(IllegalArgumentException.class, () -> (new BCryptPasswordEncoder()).upgradeEncoding("secret"));
        assertFalse((new BCryptPasswordEncoder()).upgradeEncoding(null));
        assertFalse(
                (new BCryptPasswordEncoder()).upgradeEncoding("$2a$99$UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"));
        assertFalse((new BCryptPasswordEncoder()).upgradeEncoding(""));
    }
}

