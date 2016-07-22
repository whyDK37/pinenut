package jdk.me.util;

import java.sql.Date;
import java.util.TreeMap;

/**
 * Created by whydk on 2016/6/30.
 */
public class TreeMapTest {
    public static void main(String[] args) {
        String abc;
        TreeMap<String,String> treeMap = new TreeMap();

        treeMap.put("a","a");
        treeMap.put("b","b");
        treeMap.put("1","1");
        treeMap.put("2","2");

        System.out.println(treeMap.toString());
        System.out.println(Long.MAX_VALUE-1457422927838L);
        System.out.println(new Date(1457422927838L));
        System.out.println(new Date(9999999999999L));
    }

    static class TK {

    }
}
