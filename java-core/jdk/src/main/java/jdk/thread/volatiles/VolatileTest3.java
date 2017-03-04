package jdk.thread.volatiles;

import java.util.concurrent.CountDownLatch;

/**
 * Created by drug on 2016/5/5.
 */
public class VolatileTest3 {

    static int i;

    public static void main(String[] args) throws InterruptedException {

        ThreadGroup tg = new ThreadGroup("increase");
        CountDownLatch controller = new CountDownLatch(1);

        Thread one = new Thread(tg, new Increase(10000, controller));
        Thread two = new Thread(tg, () -> {
            System.out.println(i);
        });
        one.start();
        two.start();

        controller.await();

        System.out.println("done:" + i);

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
//            for (int j = 0; j < loop; j++) {
//                System.out.println(Thread.currentThread().getName() + " - " + i++);
//            }
            i = loop;
            controller.countDown();
            while (true) {

            }
        }
    }
}
