package jdk.util.concurrent;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by why on 4/2/2017.
 */
public class ArrayBlockingQueueTest {

  @Test
  public void test() throws InterruptedException {
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(2);
    arrayBlockingQueue.offer(1);
    arrayBlockingQueue.offer(1);

    //不会抛出异常，但是返回值是false
    boolean offer = arrayBlockingQueue.offer(1);
    System.out.println("offer 失败是返回：" + offer);

    try {
      arrayBlockingQueue.add(1);
    } catch (Exception e) {
      System.out.println("add 方法在不成功的时候会抛出异常：" + e);
    }


    Object poll = arrayBlockingQueue.poll(1, TimeUnit.SECONDS);
    System.out.println(poll);

  }
}
