package jdk.lang;


import org.testng.annotations.Test;

import java.math.BigInteger;

/**
 * Created by drug on 2016/4/20.
 */
public class BigIntegerTest {

    @Test
    public void test() {
        BigInteger integer = new BigInteger("47389543034934881");
        BigInteger integer2 = new BigInteger("47389543034934882");
        System.out.println(integer.subtract(integer2));
    }
}
