package jdk.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by drug on 2016/3/3.
 */
public class FailFastTest {
  /**
   * @desc:当i == 3时，修改list
   * @Project:test
   * @file:FailFastTest.java
   * @Authro:chenssy
   * @data:2014年7月26日
   */
  private static List<Integer> list = new ArrayList<Integer>();

  public static void main(String[] args) {
    //初始化集合
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    //遍历线程和移除线程同时操作同一集合会引起fail-fast。
    //原因是modCount 在移除操作是发生变化
    // final void checkForComodification() {
    //        innerfloat (modCount != expectedModCount)
    //            throw new ConcurrentModificationException();
    //    }
    new ThreadIterator().start();
    new ThreadRemove().start();
  }


  /**
   * @desc:线程one迭代list
   * @Project:test
   * @file:FailFastTest.java
   * @Authro:chenssy
   * @data:2014年7月26日
   */
  private static class ThreadIterator extends Thread {
    public void run() {
      Iterator<Integer> iterator = list.iterator();
      while (iterator.hasNext()) {
        int i = iterator.next();
        System.out.println("ThreadOne 遍历:" + i);
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

  }

  private static class ThreadRemove extends Thread {
    public void run() {
      int i = 0;
      while (i < 6) {
        System.out.println("ThreadTwo run：" + i);
        if (i == 3) {
          list.remove(i);
        }
        i++;
      }
    }

  }
}
