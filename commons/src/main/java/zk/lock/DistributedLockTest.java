/**
 * mario.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package zk.lock;


/**
 * @author liming
 * @version $Id: DistributedLockTest.java, v 0.1 2015年8月5日 下午1:16:35 liming Exp $
 */
public class DistributedLockTest {

  static int value = 5;

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      Thread t = new Thread(new Runnable() {
        public void run() {
          try {
            DistributedLock lock = new DistributedLock("127.0.0.1:2181", value + "");
            lock.lock();
            if (value == 0) {
              System.out.println("===" + Thread.currentThread().getName());
            } else {
              Thread.sleep(200); // 获得锁之后可以进行相应的处理
              System.out.println("value:" + value-- + " " + Thread.currentThread().getName());
            }
            lock.unlock();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }, "jdk.thread " + i);
      t.start();
    }
  }
}
