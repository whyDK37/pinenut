package jdk.util;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.TreeMap;

/**
 * Created by why on 4/8/2017.
 */
public class TreeMapTest {

    @Test
    public void test() {
        TreeMap<Integer, Integer> treeMap = new TreeMap();
        treeMap.put(1, 1);
        treeMap.put(2, 2);
        treeMap.put(3, 3);
        treeMap.put(4, 4);

        Assert.assertTrue(treeMap.get(1) != null);
    }
}
