package jdk.util.concurrent.atomic;

import jdk.User;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicIntegerFieldUpdater 可以保证某个实例的属性是原子操作。
 * Created by why on 5/6/2017.
 */
public class AtomicIntegerFieldUpdaterTest {

  @Test
  public void test() throws InterruptedException {
    AtomicIntegerFieldUpdater age = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

    User user = new User();


    ExecutorService executorService = Executors.newFixedThreadPool(10);
    for (int i = 0; i < 100; i++) {
      executorService.execute(() -> {
        age.addAndGet(user, 1);
      });
    }

    TimeUnit.MILLISECONDS.sleep(10);
    System.out.println(user.getAge());

  }
}
