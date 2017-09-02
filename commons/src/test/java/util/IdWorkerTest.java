package util;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * Created by huangchaoguang on 2017/7/20.
 */
public class IdWorkerTest {

    @Test
    public void testNextId() throws Exception {
        IdWorker worker2 = new IdWorker(2);
        IdWorker worker3 = new IdWorker(15);

        System.out.println(worker3.nextId());
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println(worker2.nextId());
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            System.out.println(worker2.nextId());
            worker2.nextId();
        }
        long t2 = System.currentTimeMillis() - t1;
    }
}