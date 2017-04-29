package jdk.lang;

import org.testng.annotations.Test;

/**
 * Created by why on 4/29/2017.
 */
public class StringTest {

    @Test
    public void test() {
        String str = "abcabcabcabcabcabc";
        int bcd = str.indexOf("abcdex");
        System.out.println(bcd);
    }
}
