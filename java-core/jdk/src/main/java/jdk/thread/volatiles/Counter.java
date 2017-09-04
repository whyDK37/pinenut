package jdk.thread.volatiles;

import java.util.Random;

public class Counter {

  public static int threadNo = 0;

  public static int count = 0;
  private static Object lockObject = new Object();

  public static void inc() {
    try {
      int interval = new Random().nextInt(100) % 9 + 1;
      Thread.sleep(interval);
    } catch (InterruptedException e) {
    }
    synchronized (lockObject) {
      count++;
      System.out.println("Current count=" + count);
      threadNo = threadNo + 1;
    }
  }

  public static void main(String[] args) {
    final int THREAD_NO = 1000;
    for (int i = 0; i < THREAD_NO; i++) {
      new Thread(() -> {
        Counter.inc();
      }).start();
    }
    while (Counter.threadNo < THREAD_NO) {
      try {
        Thread.sleep(10);
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
    System.out.println("The Result count=" + Counter.count);
  }
}