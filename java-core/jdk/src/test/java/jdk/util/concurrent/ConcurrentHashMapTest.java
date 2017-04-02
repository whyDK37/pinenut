package jdk.util.concurrent;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by why on 3/6/2017.
 */
public class ConcurrentHashMapTest {

    @Test
    public void test(){
        ConcurrentHashMap<String,String> concurrentHashMapTest = new ConcurrentHashMap<>(16);

        concurrentHashMapTest.put("a", "a");

    }
}
