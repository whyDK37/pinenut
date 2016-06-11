package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by drug on 2016/5/5.
 */
public class VolatileTest {

    static volatile int i;

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup("increase");
        Thread one = new Thread(tg ,new Increase(100));
        Thread two = new Thread(tg ,new Increase(100));
        one.start();
        two.start();

        while(tg.activeCount() >0){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(i);

    }

    static class Increase implements Runnable{
        private int loop;
        Increase(int loop){
            this.loop = loop;
        }
        @Override
        public void run() {
            for (int j = 0; j < loop; j++) {
                System.out.println(Thread.currentThread().getName()+" - "+i++);
            }
        }
    }
}
