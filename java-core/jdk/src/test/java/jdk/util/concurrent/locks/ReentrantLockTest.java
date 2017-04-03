package jdk.util.concurrent.locks;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by why on 4/3/2017.
 */
public class ReentrantLockTest {

    @Test
    public void test() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(() -> {

            lock.tryLock();
            try {
                System.out.println("wait signal");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("got signal");
            lock.unlock();
        });

        Thread thread2 = new Thread(() -> {

            lock.tryLock();
            System.out.println("i got the lock");
            try {
                TimeUnit.SECONDS.sleep(1);
                condition.signal();
                System.out.println("i send a signal");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        thread2.start();
        TimeUnit.SECONDS.sleep(2);
    }
}
