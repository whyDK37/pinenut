package jdk.util.concurrent;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by why on 3/6/2017.
 */
public class ConcurrentHashMapTest {

    @Test
    public void test() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        map.put("a", "a");
        map.putIfAbsent("a", "a");
        Assert.assertEquals(map.size(), 1);

        map.replace("a", "aa");


        map.merge("aaa", "b", (s, s2) -> s + s2);

        map.forEach((s, s2) -> {
            System.out.println("key:" + s + ", value:" + s2);
        });

        System.out.println(map.size());

    }
}
