package redis;

import org.junit.Test;

/**
 * Created by whydk on 2016/8/8.
 */
public class RedisBitMapsTest {
    String action = "read";
    String date = "today";
    @Test
    public void setupBitMap(){
        System.out.println("put date into redis");
        for (int i = 0; i < 100000000; i++) {
            RedisBitMap.setCount(action,date,i,true);
        }
        System.out.println("put date into redis done");

    }
    @Test
    public void setupBitMap2(){
        System.out.println("put date into redis");
        for (int i = 100000000; i > 0; i--) {
            RedisBitMap.setCount(action,date,i,true);
        }
        System.out.println("put date into redis done");

    }
    @Test
    public void getBitMap(){
        long start = System.currentTimeMillis();
        int count = RedisBitMap.uniqueCount(action,date);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(count);
    }
}
