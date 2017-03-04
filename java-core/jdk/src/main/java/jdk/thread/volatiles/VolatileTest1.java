package jdk.thread.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * 测试 volatile ,多线程可以保证 volatile变量写的唯一性。
 * Created by whydk on 2016/5/5.
 */
public class VolatileTest1 {
    private static volatile int i;
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup("increase");
        Thread one = new Thread(tg,new Increase(100));
        Thread two = new Thread(tg,new Increase(100));
        Thread three = new Thread(tg,new Increase(100));
        one.start();
        two.start();
        three.start();
        while (tg.activeCount()>0){
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(i);
    }

    static class Increase implements Runnable{
        int loop;
        public Increase(int loop){
            this.loop = loop;
        }

        @Override
        public void run() {
            for (int j = 0; j < loop; j++) {
                i++;
            }
        }
    }

}
