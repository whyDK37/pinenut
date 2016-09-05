package jdk.util;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by whydk on 2016/8/30.
 */
public class WeakHashMapTest {
    static Map<Long, AtomicInteger> wMap = new WeakHashMap();

    @Test
    public void testWeakHashMap() throws IOException {
        File log = new File("D:\\\\log.txt");
        FileUtils.write(log,"");
        StringBuilder sb = new StringBuilder(10000);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            long time = System.currentTimeMillis();
            AtomicInteger atomicInteger = wMap.get(time);
            if (atomicInteger == null) {
                atomicInteger = new AtomicInteger(1);
                wMap.put(time, atomicInteger);
            }

            sb.append(time + " : " + atomicInteger.getAndAdd(1)).append(" ").append(wMap.size()).append("\n");
//            System.out.println(time + " : " + atomicInteger.getAndAdd(1));
//            System.out.println(wMap.size());
            if(sb.length()>10000){
                FileUtils.write(log,sb.toString(),true);
                sb.delete(0,sb.length()-1);
            }
        }
    }

}
