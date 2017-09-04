package org.commons.collections4;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.util.Map;

/**
 * 忽略key大小写的map
 */
public class CaseInsensitiveMapTest {

  public static void main(String[] args) {
    Map<String, String> map = new CaseInsensitiveMap<>();

    map.put("ONE", "one");
    map.put("one", "one");

    System.out.println(map.size());
  }
}
