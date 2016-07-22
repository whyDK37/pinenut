package jdk.lang;

import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by drug on 2016/4/20.
 */
public class IntegerTest {

    @Test
    public void test(){
        System.out.println("test");
        BigInteger integer = new BigInteger(Integer.MAX_VALUE+"999999999999999999999999999");
        System.out.println(integer);
    }
}
