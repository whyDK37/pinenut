package guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RateLimiterTester {

    final static RateLimiter rateLimiter = RateLimiter.create(2.0);

    public static void main(String[] args) {
        //速率是每秒两个许可
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            final int ts = i;
            tasks.add(() -> System.out.println(ts));
        }
        submitTasks(tasks, executorService);


    }

    static void submitTasks(List<Runnable> tasks, Executor executor) {
        for (Runnable task : tasks) {
            System.out.println(rateLimiter.acquire()); // 也许需要等待
            executor.execute(task);
        }
    }
}
