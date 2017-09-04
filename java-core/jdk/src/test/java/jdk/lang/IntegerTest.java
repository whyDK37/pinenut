package jdk.lang;


import org.testng.annotations.Test;

import java.math.BigInteger;

/**
 * Created by drug on 2016/4/20.
 */
public class IntegerTest {

  @Test
  public void test() {
    System.out.println(MyInteger.MAX_VALUE);

    BigInteger integer = new BigInteger(MyInteger.MAX_VALUE + "999999999999999999999999999");
    System.out.println(integer.toString());
    System.out.println(integer.intValue());
  }
}
