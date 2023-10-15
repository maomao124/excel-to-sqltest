package mao.excel_to_sql_test.utils.password;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class BCryptTest
{
    /**
     * Method under test: {@link BCrypt#encode_base64(byte[], int, StringBuilder)}
     */
    @Test
    void testEncode_base64() throws UnsupportedEncodingException, IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by encode_base64(byte[], int, StringBuilder)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        byte[] d = "AAAAAAAA".getBytes("UTF-8");
        BCrypt.encode_base64(d, 3, new StringBuilder("foo"));
    }

    /**
     * Method under test: {@link BCrypt#encode_base64(byte[], int, StringBuilder)}
     */
    @Test
    void testEncode_base642() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class,
                () -> BCrypt.encode_base64(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 0, new StringBuilder("foo")));
    }

    /**
     * Method under test: {@link BCrypt#encode_base64(byte[], int, StringBuilder)}
     */
    @Test
    void testEncode_base643() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by encode_base64(byte[], int, StringBuilder)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        BCrypt.encode_base64(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 1, new StringBuilder("foo"));
    }

    /**
     * Method under test: {@link BCrypt#encode_base64(byte[], int, StringBuilder)}
     */
    @Test
    void testEncode_base644() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by encode_base64(byte[], int, StringBuilder)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        BCrypt.encode_base64(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 2, new StringBuilder("foo"));
    }

    /**
     * Method under test: {@link BCrypt#encode_base64(byte[], int, StringBuilder)}
     */
    @Test
    void testEncode_base645() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class,
                () -> BCrypt.encode_base64(new byte[]{'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A'}, 9, new StringBuilder("foo")));
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base64() throws IllegalArgumentException
    {
        byte[] actualDecode_base64Result = BCrypt.decode_base64("foo", 3);
        assertEquals(2, actualDecode_base64Result.length);
        assertEquals((byte) -122, actualDecode_base64Result[0]);
        assertEquals((byte) -86, actualDecode_base64Result[1]);
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base642() throws IllegalArgumentException
    {
        byte[] actualDecode_base64Result = BCrypt.decode_base64("foo", 1);
        assertEquals(1, actualDecode_base64Result.length);
        assertEquals((byte) -122, actualDecode_base64Result[0]);
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base643() throws IllegalArgumentException
    {
        assertEquals(0, BCrypt.decode_base64("$", 3).length);
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base644() throws IllegalArgumentException
    {
        byte[] actualDecode_base64Result = BCrypt.decode_base64("42", 3);
        assertEquals(1, actualDecode_base64Result.length);
        assertEquals((byte) -21, actualDecode_base64Result[0]);
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base645() throws IllegalArgumentException
    {
        assertEquals(0, BCrypt.decode_base64("$2", 3).length);
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base646() throws IllegalArgumentException
    {
        byte[] actualDecode_base64Result = BCrypt.decode_base64("int[]", 3);
        assertEquals(3, actualDecode_base64Result.length);
        assertEquals((byte) -110, actualDecode_base64Result[0]);
        assertEquals((byte) -101, actualDecode_base64Result[1]);
        assertEquals((byte) -1, actualDecode_base64Result[2]);
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base647() throws IllegalArgumentException
    {
        byte[] actualDecode_base64Result = BCrypt.decode_base64("foo", 2);
        assertEquals(2, actualDecode_base64Result.length);
        assertEquals((byte) -122, actualDecode_base64Result[0]);
        assertEquals((byte) -86, actualDecode_base64Result[1]);
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base648() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.decode_base64("foo", 0));
    }

    /**
     * Method under test: {@link BCrypt#decode_base64(String, int)}
     */
    @Test
    void testDecode_base649() throws IllegalArgumentException
    {
        byte[] actualDecode_base64Result = BCrypt.decode_base64("Invalid maxolen", 3);
        assertEquals(3, actualDecode_base64Result.length);
        assertEquals('*', actualDecode_base64Result[0]);
        assertEquals((byte) -100, actualDecode_base64Result[1]);
        assertEquals('\\', actualDecode_base64Result[2]);
    }

    /**
     * Method under test: {@link BCrypt#roundsForLogRounds(int)}
     */
    @Test
    void testRoundsForLogRounds()
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.roundsForLogRounds(1));
        assertEquals(16L, BCrypt.roundsForLogRounds(4));
        assertThrows(IllegalArgumentException.class, () -> BCrypt.roundsForLogRounds(36));
    }

    /**
     * Method under test: {@link BCrypt#hashpw(String, String)}
     */
    @Test
    void testHashpw()
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.hashpw("iloveyou", "Salt"));
        assertThrows(IllegalArgumentException.class, () -> BCrypt.hashpw("iloveyou", null));
        assertThrows(IllegalArgumentException.class, () -> BCrypt.hashpw("AAAAAAAA".getBytes("UTF-8"), "Salt"));
        assertThrows(IllegalArgumentException.class, () -> BCrypt.hashpw("AAAAAAAA".getBytes("UTF-8"), null));
    }

    /**
     * Method under test: {@link BCrypt#hashpw(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testHashpw2()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at mao.excel_to_sql_test.utils.password.BCrypt.hashpw(BCrypt.java:621)
        //   In order to prevent hashpw(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   hashpw(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        BCrypt.hashpw((String) null, "Salt");
    }

    /**
     * Method under test: {@link BCrypt#gensalt()}
     */
    @Test
    void testGensalt()
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by gensalt()
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        BCrypt.gensalt();
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int)}
     */
    @Test
    void testGensalt2() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.gensalt(1));
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int)}
     */
    @Test
    void testGensalt3() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by gensalt(int)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        BCrypt.gensalt(4);
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int)}
     */
    @Test
    void testGensalt4() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by gensalt(int)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        BCrypt.gensalt(31);
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int)}
     */
    @Test
    void testGensalt5() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.gensalt(97));
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int, SecureRandom)}
     */
    @Test
    void testGensalt6() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.gensalt(1, new SecureRandom()));
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int, SecureRandom)}
     */
    @Test
    void testGensalt7() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by gensalt(int, SecureRandom)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        BCrypt.gensalt(4, new SecureRandom());
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int, SecureRandom)}
     */
    @Test
    void testGensalt8() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by gensalt(int, SecureRandom)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        BCrypt.gensalt(31, new SecureRandom());
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int, SecureRandom)}
     */
    @Test
    void testGensalt9() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.gensalt(97, new SecureRandom()));
    }

    /**
     * Method under test: {@link BCrypt#gensalt(int, SecureRandom)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGensalt10() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at mao.excel_to_sql_test.utils.password.BCrypt.gensalt(BCrypt.java:741)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.gensalt(BCrypt.java:779)
        //   In order to prevent gensalt(int, SecureRandom)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   gensalt(int, SecureRandom).
        //   See https://diff.blue/R013 to resolve this issue.

        BCrypt.gensalt(Short.SIZE, (SecureRandom) null);
    }

    /**
     * Method under test: {@link BCrypt#gensalt(String)}
     */
    @Test
    void testGensalt11()
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.gensalt("Prefix"));
    }

    /**
     * Method under test: {@link BCrypt#gensalt(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGensalt12()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.StringIndexOutOfBoundsException: String index out of range: 2
        //       at java.lang.String.charAt(String.java:658)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.gensalt(BCrypt.java:732)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.gensalt(BCrypt.java:766)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.gensalt(BCrypt.java:802)
        //   In order to prevent gensalt(String)
        //   from throwing StringIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   gensalt(String).
        //   See https://diff.blue/R013 to resolve this issue.

        BCrypt.gensalt("$2");
    }

    /**
     * Method under test: {@link BCrypt#gensalt(String)}
     */
    @Test
    void testGensalt13()
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.gensalt("$2$2"));
    }

    /**
     * Method under test: {@link BCrypt#gensalt(String, int)}
     */
    @Test
    void testGensalt14() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.gensalt("Prefix", 1));
    }

    /**
     * Method under test: {@link BCrypt#gensalt(String, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGensalt15() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.StringIndexOutOfBoundsException: String index out of range: 2
        //       at java.lang.String.charAt(String.java:658)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.gensalt(BCrypt.java:732)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.gensalt(BCrypt.java:766)
        //   In order to prevent gensalt(String, int)
        //   from throwing StringIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   gensalt(String, int).
        //   See https://diff.blue/R013 to resolve this issue.

        BCrypt.gensalt("$2", 1);
    }

    /**
     * Method under test: {@link BCrypt#gensalt(String, int, SecureRandom)}
     */
    @Test
    void testGensalt16() throws IllegalArgumentException
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.gensalt("Prefix", 1, new SecureRandom()));
    }

    /**
     * Method under test: {@link BCrypt#gensalt(String, int, SecureRandom)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGensalt17() throws IllegalArgumentException
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.StringIndexOutOfBoundsException: String index out of range: 2
        //       at java.lang.String.charAt(String.java:658)
        //       at mao.excel_to_sql_test.utils.password.BCrypt.gensalt(BCrypt.java:732)
        //   In order to prevent gensalt(String, int, SecureRandom)
        //   from throwing StringIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   gensalt(String, int, SecureRandom).
        //   See https://diff.blue/R013 to resolve this issue.

        BCrypt.gensalt("$2", 1, new SecureRandom());
    }

    /**
     * Method under test: {@link BCrypt#checkpw(String, String)}
     */
    @Test
    void testCheckpw()
    {
        assertThrows(IllegalArgumentException.class, () -> BCrypt.checkpw("Plaintext", "Hashed"));
        assertThrows(IllegalArgumentException.class, () -> BCrypt.checkpw("Plaintext", null));
        assertThrows(IllegalArgumentException.class, () -> BCrypt.checkpw("AAAAAAAA".getBytes("UTF-8"), "Hashed"));
        assertThrows(IllegalArgumentException.class, () -> BCrypt.checkpw("AAAAAAAA".getBytes("UTF-8"), null));
    }

    /**
     * Method under test: {@link BCrypt#checkpw(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCheckpw2()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at mao.excel_to_sql_test.utils.password.BCrypt.checkpw(BCrypt.java:825)
        //   In order to prevent checkpw(String, String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   checkpw(String, String).
        //   See https://diff.blue/R013 to resolve this issue.

        BCrypt.checkpw((String) null, "Hashed");
    }

    /**
     * Method under test: {@link BCrypt#equalsNoEarlyReturn(String, String)}
     */
    @Test
    void testEqualsNoEarlyReturn()
    {
        assertTrue(BCrypt.equalsNoEarlyReturn("foo", "foo"));
        assertFalse(BCrypt.equalsNoEarlyReturn("$", "foo"));
    }
}

