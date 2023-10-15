package mao.excel_to_sql_test.utils.password;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class SHA1Test
{
    /**
     * Method under test: {@link SHA1#getSHA1(String)}
     */
    @Test
    void testGetSHA1()
    {
        assertEquals("0bb9e82643c021a547d6c2d973c9219fd6d3a479", SHA1.getSHA1("Str Text"));
        assertNull(SHA1.getSHA1(null));
        assertNull(SHA1.getSHA1(""));
    }

    /**
     * Method under test: {@link SHA1#getSHA1toUpperCase(String)}
     */
    @Test
    void testGetSHA1toUpperCase()
    {
        assertEquals("0BB9E82643C021A547D6C2D973C9219FD6D3A479", SHA1.getSHA1toUpperCase("Str Text"));
    }

    /**
     * Method under test: {@link SHA1#getSHA1toUpperCase(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetSHA1toUpperCase2()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at mao.excel_to_sql_test.utils.password.SHA1.getSHA1toUpperCase(SHA1.java:58)
        //   In order to prevent getSHA1toUpperCase(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getSHA1toUpperCase(String).
        //   See https://diff.blue/R013 to resolve this issue.

        SHA1.getSHA1toUpperCase(null);
    }

    /**
     * Method under test: {@link SHA1#getSHA1toUpperCase(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetSHA1toUpperCase3()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at mao.excel_to_sql_test.utils.password.SHA1.getSHA1toUpperCase(SHA1.java:58)
        //   In order to prevent getSHA1toUpperCase(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getSHA1toUpperCase(String).
        //   See https://diff.blue/R013 to resolve this issue.

        SHA1.getSHA1toUpperCase("");
    }
}

