package jdk.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by whydk on 2016/8/8.
 */
public class SchedulerTest {
  private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

  public static void main(String[] args) {
    long delay = 2;
    scheduler.scheduleWithFixedDelay(new Speaker("a"), 0, delay, TimeUnit.SECONDS);
    scheduler.scheduleWithFixedDelay(new Speaker("b"), 0, delay, TimeUnit.SECONDS);
  }

  static class Speaker implements Runnable {
    private String s;

    public Speaker(String s) {
      this.s = s;
    }

    @Override
    public void run() {
      System.out.println(s);
    }
  }
}
