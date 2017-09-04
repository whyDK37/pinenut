package jdk.thread.volatiles;

/**
 * Created by why on 3/2/2017.
 */
public class VolatileExample extends Thread {
  //设置类静态变量,各线程访问这同一共享变量
  private static boolean flag = false;

  public static void main(String[] args) throws Exception {
    VolatileExample volatileExample = new VolatileExample();
//        volatileExample.setDaemon(true);
    volatileExample.start();
    //sleep的目的是等待线程启动完毕,也就是说进入run的无限循环体了
//        Thread.sleep(10);

//        Thread flagThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                flag = true;
//                while (flag) {
//                    System.out.println("flagThread:" + flag);
//                }
//            }
//        });
//        flagThread.setDaemon(true);
//        flagThread.start();

    flag = true;
    volatileExample.join();
    System.out.println("done.");
  }

  //无限循环,等待flag变为true时才跳出循环
  public void run() {
    while (!flag) {
      System.out.println(flag);
    }
    System.out.println(flag);
  }
}
