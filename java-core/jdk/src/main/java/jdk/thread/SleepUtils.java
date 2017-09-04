package jdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by whydk on 3/13/2017.
 */
public class SleepUtils {
  public static void second(int i) {
    try {
      TimeUnit.SECONDS.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
