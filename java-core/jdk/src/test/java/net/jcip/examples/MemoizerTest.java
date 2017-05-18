package net.jcip.examples;

import org.testng.annotations.Test;

/**
 * Created by why on 5/16/2017.
 */
public class MemoizerTest {
    @Test
    public void testCompute() throws Exception {
        Memoizer<String, String> memoizer = new Memoizer(arg -> arg + "-1");
        String a = memoizer.compute("a");
        System.out.println(a);
    }

}