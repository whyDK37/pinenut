package jdk.thread.pool.policy;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by drug on 2016/5/9.
 */
public class WorkStealingPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            String task = "task@" + i;
            System.out.println("put->" + task);
            //一个任务通过 execute(Runnable)方法被添加到线程池，任务就是一个 Runnable类型的对象，任务的执行方法就是 Runnable类型对象的run()方法
            //处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务
            //设此时线程池中的数量为currentPoolSize,若currentPoolSize>corePoolSize,则创建新的线程执行被添加的任务,
            //当corePoolSize+workQueue>currentPoolSize>=corePoolSize,新增任务被放入缓冲队列,
            //当maximumPoolSize>currentPoolSize>=corePoolSize+workQueue,建新线程来处理被添加的任务,
            //当currentPoolSize>=maximumPoolSize,通过 handler所指定的策略来处理新添加的任务
            //本例中可以同时可以被处理的任务最多为maximumPoolSize+WORKQUEUE=8个，其中最多5个在线程中正在处理，3个在缓冲队列中等待被处理
            //当currentPoolSize>corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数
            executorService.execute(new ThreadPoolTask(task));
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();


    }


    static class ThreadPoolTask implements Runnable, Serializable {
        private static final long serialVersionUID = -8568367025140842876L;
        private static int produceTaskSleepTime = 200;
        private Object threadPoolTaskData;

        public ThreadPoolTask(Object threadPoolTaskData) {
            super();
            this.threadPoolTaskData = threadPoolTaskData;
        }

        public void run() {
            System.out.println("start..." + threadPoolTaskData);
//            try {
            int i = 10000;
            while (i-- > 0) {
                ;
            }
            //模拟线程正在执行任务
//                TimeUnit.MILLISECONDS.sleep(produceTaskSleepTime);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("stop..." + threadPoolTaskData);
            threadPoolTaskData = null;
        }

        public Object getTask() {
            return this.threadPoolTaskData;
        }
    }
}
