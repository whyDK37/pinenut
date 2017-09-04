package jdk.thread.pool.policy;

import jdk.thread.task.ThreadPoolTaskMillisconds;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;


/**
 * AbortPolicy 策略
 */
public class ThreadPoolPolicyTest {
  //线程池维护线程的最少数量
  private static final int COREPOOLSIZE = 20;
  //线程池维护线程的最大数量
  private static final int MAXINUMPOOLSIZE = 100;
  //线程池维护线程所允许的空闲时间
  private static final long KEEPALIVETIME = 20;
  //线程池维护线程所允许的空闲时间的单位
  private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;
  //线程池所使用的缓冲队列,这里队列大小为3
  private static final BlockingQueue<Runnable> WORKQUEUE = new ArrayBlockingQueue<>(1000);
  //线程池对拒绝任务的处理策略：AbortPolicy为抛出异常；CallerRunsPolicy为重试添加当前的任务，他会自动重复调用execute()方法；DiscardOldestPolicy为抛弃旧的任务，DiscardPolicy为抛弃当前的任务
  private static final AbortPolicy abortPolicy = new AbortPolicy();
  private static final ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();

  public static void main(String[] args) throws Exception {
    // 初始化线程池
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(COREPOOLSIZE, MAXINUMPOOLSIZE, KEEPALIVETIME, UNIT, WORKQUEUE, Executors.defaultThreadFactory(), abortPolicy);
    for (int i = 1; i < 1000; i++) {
      String task = "task@" + i;
//            while (threadPool.getActiveCount() == MAXINUMPOOLSIZE) {
//                UNIT.sleep(10);
//            }
      threadPool.execute(new ThreadPoolTaskMillisconds(task, 200));
    }
    threadPool.shutdown();//关闭主线程，但线程池会继续运行，直到所有任务执行完才会停止。若不调用该方法线程池会一直保持下去，以便随时添加新的任务
  }

}

