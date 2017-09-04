package jdk.instrument;

import java.lang.reflect.Field;

class NodeTest1 {
  private int a = 13;
  private int b = 21;
}

public class Test001 {
  public static void main(String[] args) {
    NodeTest1 node = new NodeTest1();
    Field[] fields = NodeTest1.class.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        int i = field.getInt(node);
        field.setInt(node, i * 2);
        System.out.println(field.getInt(node));
      } catch (IllegalArgumentException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
    }
  }
}