package mao.excel_to_sql_test.utils.password;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MD5Test
{
    /**
     * Method under test: {@link MD5#getMD5(String)}
     */
    @Test
    void testGetMD5()
    {
        assertEquals("0e9c20d9b237aecc65de77a491061be5", MD5.getMD5("27c7cf400229103e00c6d8830029e29b"));
        assertEquals("128be35ca6bd84af0474c270b3297880",
                MD5.getMD5("27c7cf400229103e00c6d8830029e29b27c7cf400229103e00c6d8830029e29b"));
        assertEquals("3f286781ece9336f1118831e858aa0d7",
                MD5.getMD5("27c7cf400229103e00c6d8830029e29bNot all who wander are lost"));
        assertEquals("2a5a9545eac7b25f6f7eeda0adfb4c24",
                MD5.getMD5("Not all who wander are lostNot all who wander are lost42"));
    }

    /**
     * Method under test: {@link MD5#getMD5toUpperCase(String)}
     */
    @Test
    void testGetMD5toUpperCase()
    {
        assertEquals("0E9C20D9B237AECC65DE77A491061BE5", MD5.getMD5toUpperCase("27c7cf400229103e00c6d8830029e29b"));
        assertEquals("128BE35CA6BD84AF0474C270B3297880",
                MD5.getMD5toUpperCase("27c7cf400229103e00c6d8830029e29b27c7cf400229103e00c6d8830029e29b"));
        assertEquals("3F286781ECE9336F1118831E858AA0D7",
                MD5.getMD5toUpperCase("27c7cf400229103e00c6d8830029e29bNot all who wander are lost"));
        assertEquals("2A5A9545EAC7B25F6F7EEDA0ADFB4C24",
                MD5.getMD5toUpperCase("Not all who wander are lostNot all who wander are lost42"));
    }
}

