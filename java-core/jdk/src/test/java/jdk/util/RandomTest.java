package jdk.util;

import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by why on 5/2/2017.
 */
public class RandomTest {
  @Test
  public void test() {
    Random random = new Random();
    System.out.println(random.nextInt());
    System.out.println(random.nextInt());
    System.out.println(random.nextInt());

    random = new Random(2);
    System.out.println(random.nextInt());
    System.out.println(random.nextInt());
    System.out.println(random.nextInt());
  }
}
