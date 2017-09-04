package jdk.util;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by whydk on 2016/8/3.
 */
public class HashMapTest {
  @Test
  public void test() {
    HashMap<String, Object> hashMap = new HashMap<String, Object>();

    for (int i = 0; i < 300; i++) {
      hashMap.put(i + "", i);
    }

    System.out.println(hashMap);
  }
}
