package jdk.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLongArray;

/**
 * Created by why on 5/12/2017.
 */
public class AtomicLongArrayTest {
    @Test
    public void test() {
        AtomicLongArray atomicLong = new AtomicLongArray(1);
        atomicLong.addAndGet(0, 1);
    }
}
