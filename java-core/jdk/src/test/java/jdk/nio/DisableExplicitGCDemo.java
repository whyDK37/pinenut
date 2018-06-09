package jdk.nio;

import java.nio.ByteBuffer;

/**
 * -XX:MaxDirectMemorySize=10m -XX:+PrintGC -XX:+DisableExplicitGC
 * -Xmx10m -Xms10m -XX:+UseG1GC -XX:+PrintGCDetails -XX:+PrintHeapAtGC -XX:+PrintGCDateStamps -XX:+PrintAdaptiveSizePolicy -XX:+PrintReferenceGC -verbose:gc -Xloggc:d:\gc.log
 * http://breezylee.iteye.com/blog/2043056
 */
public class DisableExplicitGCDemo {
    public static void main(String[] args) {
        System.out.println("starting");
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            ByteBuffer.allocateDirect(4024);
            System.out.println(i);
        }
        System.out.println("Done");
    }
}  