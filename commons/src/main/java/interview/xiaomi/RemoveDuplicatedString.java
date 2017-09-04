package interview.xiaomi;

import java.util.*;

/**
 * 去掉list中重复的字符串，要求在原list上直接修改。
 * Created by why on 2017/5/20.
 */
public class RemoveDuplicatedString {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("hello");
    list.add("world");
    list.add("hello");
    list.add("world");

    remove(list);

    System.out.println(list);
  }

  public static void remove(List<String> list) {
    Map<String, Boolean> map = new HashMap<>();
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      String next = iterator.next();

      if (map.get(next) != null) {
        iterator.remove();
      } else {
        map.put(next, Boolean.TRUE);
      }
    }
  }
}
