package thread;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

/**
 * Created by drug on 2016/3/31.
 */
public class WaitAndNotify {

    public static void main(String[] args) throws Exception {
        final Waiter waiter = new Waiter();
        Thread notifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                out.println(Thread.currentThread().toString());
                try {
                    TimeUnit.SECONDS.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (waiter) {
                    out.println("notify");
                    waiter.notifyAll();
                }
            }
        }, "notifyThread");
        notifyThread.start();
        Thread waitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (waiter) {
                    try {
                        out.println(Thread.currentThread().toString());
                        waiter.wait();
                        out.println("wake up");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "waitThread");
        waitThread.start();
    }


    static class Waiter {
        private Object object = new Object();

        public void one(Waiter waiter) throws Exception {

        }
    }
}

