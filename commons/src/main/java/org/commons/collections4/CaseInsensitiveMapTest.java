package org.commons.collections4;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

/**
 * 忽略key大小写的map
 */
public class CaseInsensitiveMapTest {

  public static void main(String[] args) {
    CaseInsensitiveMap<String, String> map = new CaseInsensitiveMap();

    map.put("one", "one");
    map.put("ONE", "one");

    System.out.println(map.size());
  }
}
