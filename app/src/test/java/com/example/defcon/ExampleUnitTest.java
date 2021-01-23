package com.example.defcon;

import org.junit.Test;
import java.util.regex.*;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void regex_test(){
        Pattern clockREGEX = Pattern.compile("[0-2]?\\d:[0-5]\\d");
        assertTrue(clockREGEX.matcher("12:43").matches());
        assertFalse(clockREGEX.matcher("01:66").matches());
        assertTrue(clockREGEX.matcher("12:59").matches());
        assertTrue(clockREGEX.matcher("04:02").matches());
        assertTrue(clockREGEX.matcher("4:02").matches());
    }
}
