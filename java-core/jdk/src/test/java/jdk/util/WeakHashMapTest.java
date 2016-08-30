package jdk.util;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by whydk on 2016/8/30.
 */
public class WeakHashMapTest {
    static Map wMap = new WeakHashMap();

    public static void init() {
        wMap.put("1", "ding");
        wMap.put("2", "job");
    }

    public static void testWeakHashMap() {

        System.out.println("first get:" + wMap.get("1"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("next get:" + wMap.get("1"));
    }

    public static void main(String[] args) {
        init();
        testWeakHashMap();
    }

    public void test() {
        WeakHashMap weakHashMap = new WeakHashMap();
    }
}
