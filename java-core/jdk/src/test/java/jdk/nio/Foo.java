package jdk.nio;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by drug on 2016/2/29.
 */
public class Foo {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<Integer>();
    list.add(3);
    list.add(2);
    list.add(1);
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
    String abc = "abc";
    byte[] bytes = abc.getBytes();
//        System.out.println(String.Val);
  }

}
