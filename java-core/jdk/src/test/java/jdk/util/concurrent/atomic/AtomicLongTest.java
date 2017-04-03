package jdk.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by why on 4/3/2017.
 */
public class AtomicLongTest {

    @Test
    public void test(){
        AtomicLong atomicLong = new AtomicLong(1);
        atomicLong.addAndGet(1);

        System.out.println(atomicLong.get());
    }
}
