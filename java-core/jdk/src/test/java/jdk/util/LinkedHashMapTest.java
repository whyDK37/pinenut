package jdk.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by whydk on 2016/8/3.
 */
public class LinkedHashMapTest {
    @Test
    public void test(){
        LinkedHashMap<String,Object> linkedHashMap  = new LinkedHashMap<String, Object>(3);
        linkedHashMap.put("one","one");
        linkedHashMap.put("two","two");
        linkedHashMap.put("three","three");
        System.out.println(linkedHashMap);
    }
}
