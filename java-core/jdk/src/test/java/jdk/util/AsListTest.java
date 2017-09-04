package jdk.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by drug on 2016/3/4.
 */
public class AsListTest {


  @Test
  public void test1() {
    int[] ints = {1, 2, 3, 4, 5};
    List list = Arrays.asList(ints);
    System.out.println("list 的类型:" + list.get(0).getClass());
    System.out.println("list.get(0) == ints：" + list.get(0).equals(ints));
  }

  @Test
  public void test2() {
    Integer[] ints = {1, 2, 3, 4, 5};
    List list = Arrays.asList(ints);
    System.out.println("list'size：" + list.size());
    System.out.println("list.get(0) 的类型:" + list.get(0).getClass());
    System.out.println("list.get(0) == ints[0]：" + list.get(0).equals(ints[0]));
  }
}
