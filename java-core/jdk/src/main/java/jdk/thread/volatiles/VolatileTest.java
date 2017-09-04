package jdk.thread.volatiles;

import java.util.concurrent.CountDownLatch;

/**
 * Created by drug on 2016/5/5.
 */
public class VolatileTest {

  static int i;

  public static void main(String[] args) throws InterruptedException {

    ThreadGroup tg = new ThreadGroup("increase");
    CountDownLatch controller = new CountDownLatch(2);

    Thread one = new Thread(tg, new Increase(10000, controller));
    Thread two = new Thread(tg, new Increase(10000, controller));
    one.start();
    two.start();

    controller.await();

    System.out.println(i);

  }

  static class Increase implements Runnable {
    private int loop;
    private CountDownLatch controller;

    Increase(int loop, CountDownLatch controller) {
      this.loop = loop;
      this.controller = controller;
    }

    @Override
    public void run() {
      for (int j = 0; j < loop; j++) {
        System.out.println(Thread.currentThread().getName() + " - " + i++);
      }
      controller.countDown();
    }
  }
}
