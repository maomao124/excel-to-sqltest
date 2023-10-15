package mao.excel_to_sql_test.utils.password;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class SHA256Test
{
    /**
     * Method under test: {@link SHA256#getSHA256(String)}
     */
    @Test
    void testGetSHA256()
    {
        assertEquals("e52887f6d3a662d18343baa18f5532ea3b90ad9cd7bd99f487bac200c6955ca0", SHA256.getSHA256("Str Text"));
        assertNull(SHA256.getSHA256(null));
        assertNull(SHA256.getSHA256(""));
    }

    /**
     * Method under test: {@link SHA256#getSHA256toUpperCase(String)}
     */
    @Test
    void testGetSHA256toUpperCase()
    {
        assertEquals("E52887F6D3A662D18343BAA18F5532EA3B90AD9CD7BD99F487BAC200C6955CA0",
                SHA256.getSHA256toUpperCase("Str Text"));
    }

    /**
     * Method under test: {@link SHA256#getSHA256toUpperCase(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetSHA256toUpperCase2()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at mao.excel_to_sql_test.utils.password.SHA256.getSHA256toUpperCase(SHA256.java:58)
        //   In order to prevent getSHA256toUpperCase(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getSHA256toUpperCase(String).
        //   See https://diff.blue/R013 to resolve this issue.

        SHA256.getSHA256toUpperCase(null);
    }

    /**
     * Method under test: {@link SHA256#getSHA256toUpperCase(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetSHA256toUpperCase3()
    {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at mao.excel_to_sql_test.utils.password.SHA256.getSHA256toUpperCase(SHA256.java:58)
        //   In order to prevent getSHA256toUpperCase(String)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   getSHA256toUpperCase(String).
        //   See https://diff.blue/R013 to resolve this issue.

        SHA256.getSHA256toUpperCase("");
    }
}

