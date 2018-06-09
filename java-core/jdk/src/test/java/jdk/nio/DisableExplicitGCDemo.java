package jdk.nio;

import java.nio.ByteBuffer;

/**
 * -XX:MaxDirectMemorySize=10m -XX:+PrintGC -XX:+DisableExplicitGC
 * -Xmx10m -Xms10m -XX:+UseG1GC
 * http://breezylee.iteye.com/blog/2043056
 */
public class DisableExplicitGCDemo {
  public static void main(String[] args) {
    System.out.println("starting");
    for (int i = 0; i < 100000; i++) {
      ByteBuffer.allocateDirect(128);
    }
    System.out.println("Done");
  }
}  