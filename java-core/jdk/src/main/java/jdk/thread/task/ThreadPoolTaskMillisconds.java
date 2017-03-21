package jdk.thread.task;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 模拟 task 休眠 n 毫秒。
 */
public class ThreadPoolTaskMillisconds implements Runnable, Serializable {
        private static final long serialVersionUID = -8568367025140842876L;
        private static int produceTaskSleepTime = 200;
        private Object threadPoolTaskData;
        private int sleep;
        public ThreadPoolTaskMillisconds(Object threadPoolTaskData,int sleep) {
            super();
            this.sleep = sleep;
            this.threadPoolTaskData = threadPoolTaskData;
        }

        public void run() {
            System.out.println("start..." + threadPoolTaskData);
            try {
                //模拟线程正在执行任务
                TimeUnit.MILLISECONDS.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("stop..." + threadPoolTaskData);
            threadPoolTaskData = null;
        }

        public Object getTask() {
            return this.threadPoolTaskData;
        }
    }