package jdk.thread;

/**
 * 多运行几次，可能会得到1或0两种结果
 */
public class DataRace {
  static int a = 0;
 
  public static void main(String[] args) {
    new MyThread().start();
    a = 1;
  }
 
  public static class MyThread extends Thread {
    public void run() { 
      System.out.println(a);
    }
  }
}