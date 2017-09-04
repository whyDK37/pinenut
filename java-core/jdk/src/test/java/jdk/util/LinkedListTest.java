package jdk.util;

import org.junit.Test;

import java.util.LinkedList;

/**
 * Created by why on 2016/8/7.
 */
public class LinkedListTest {
  private static String[] EMPTY_CHEESE_ARRAY = new String[2];
  private static LinkedList<String> cheesesInStock = new LinkedList<>();

  @Test
  public void main() {
    cheesesInStock.add("1");

    EMPTY_CHEESE_ARRAY[0] = "3";
    EMPTY_CHEESE_ARRAY[1] = "4";
    String[] rs = cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
    System.out.println("copy list item to array");
    System.out.println("length:" + rs.length);
    for (String s : rs) {
      System.out.print(s + " ");
    }

    cheesesInStock.add("2");
    cheesesInStock.add("3");
    rs = cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
    System.out.println("copy array item to list");
    System.out.println("length:" + rs.length);
    for (String s : rs) {
      System.out.print(s + " ");
    }

    EMPTY_CHEESE_ARRAY = new String[0];
    cheesesInStock.clear();
    rs = cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
    System.out.println("return empty array");
    System.out.println("length:" + rs.length);
    for (String s : rs) {
      System.out.print(s + " ");
    }

  }

}
