package jdk.util.concurrent.locks;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by why on 5/6/2017.
 */
public class ReentrantReadWriteLockTest {

    @Test
    public void test() throws InterruptedException {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


        Thread read = new Thread(() -> {


        });

        Thread write = new Thread(() -> {

        });

    }
}
