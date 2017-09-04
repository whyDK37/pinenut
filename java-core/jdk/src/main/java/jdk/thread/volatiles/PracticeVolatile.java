package jdk.thread.volatiles;

import java.util.Scanner;

public class PracticeVolatile {

  @SuppressWarnings("resource")
  public static void main(String[] args) {

    Task t1 = new Task(); // 创建一个线程类：Task的对象
    t1.start(); // 开始线程

    // 挂起程序，用户输入回车后，继续运行
    new Scanner(System.in).next();

    t1.shutdown(); // 结束线程

  }
}

class Task extends Thread {

  //1. 设置一个 volatile的boolean变量
  private volatile boolean running = true;

  //2. 明确run()方法运行的前提是, running是true
  public void run() {
    while (running) {
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("运行中");
    }
  }

  //3. 设置一个关闭方法，供其他线程使用，把running改成false
  //   这样其他线程就可以停止run()中进程
  public void shutdown() {
    running = false;
  }
}