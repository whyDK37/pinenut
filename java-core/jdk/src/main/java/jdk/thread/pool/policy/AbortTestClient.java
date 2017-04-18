package jdk.thread.pool.policy;

import java.util.concurrent.*;

/**
 * 终止饱和策略
 *
 * @author zhangwei_david
 * @version $Id: AbortTestClient.java, v 0.1 2014年11月13日 下午3:03:47 zhangwei_david Exp $
 */
public class AbortTestClient {
    //线程池维护线程的最少数量
    private static final int COREPOOLSIZE = 1;
    //线程池维护线程的最大数量
    private static final int MAXINUMPOOLSIZE = 3;
    //线程池维护线程所允许的空闲时间
    private static final long KEEPALIVETIME = 2;
    //线程池维护线程所允许的空闲时间的单位
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;
    //线程池所使用的缓冲队列,这里队列大小为3
    private static final BlockingQueue<Runnable> WORKQUEUE = new ArrayBlockingQueue<>(1);
    //线程池对拒绝任务的处理策略：AbortPolicy为抛出异常；CallerRunsPolicy为重试添加当前的任务，他会自动重复调用execute()方法；DiscardOldestPolicy为抛弃旧的任务，DiscardPolicy为抛弃当前的任务
    private static final ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
    private static final ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(COREPOOLSIZE, MAXINUMPOOLSIZE, KEEPALIVETIME, UNIT, WORKQUEUE, Executors.defaultThreadFactory(), abortPolicy);

        executor.setThreadFactory(new MyThreadFactory("Test"));
//        final ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 3, 2, TimeUnit.SECONDS,
//                new LinkedBlockingQueue<Runnable>(5));

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 100; i++) {
            try {
                executor.execute(() -> {
                    //doNothing
                    try {
                        UNIT.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("第" + i + "次提交线程被拒绝!  executor:" + executor);

//                被拒绝休眠1秒钟，等待线程执行池空闲。😀😬😆😇️🤔😶🤑😯🙁😻☠️😺😻🤒🏽🖐🏻‍🏻‍🔧🤷🏽‍♀️‍️‍
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        }
        System.out.println(executor);
        executor.shutdown();
        System.out.println(executor);
    }

    private static class MyThreadFactory implements ThreadFactory {
        String name;

        public MyThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(name);
            return thread;
        }
    }
}