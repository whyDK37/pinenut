package jdk.lang;

import org.junit.Assert;

/**
 * Created by why on 2016/3/3.
 */
public class MathBTest {

    public static void main(String[] args) {

        System.out.println("testing avg:");
        System.out.println("avg " + Integer.MAX_VALUE + " - " + Integer.MAX_VALUE);
        Assert.assertEquals(MathB.avg(Integer.MAX_VALUE, Integer.MAX_VALUE), Integer.MAX_VALUE);
        System.out.println("avg " + Integer.MIN_VALUE + " - " + Integer.MIN_VALUE);
        Assert.assertEquals(MathB.avg(Integer.MIN_VALUE, Integer.MIN_VALUE), Integer.MIN_VALUE);

        System.out.println("testing oddOrEven");
        System.out.println("oddOrEven " + 0);
        Assert.assertEquals(MathB.oddOrEven(0), 0);
        System.out.println("oddOrEven " + 1);
        Assert.assertEquals(MathB.oddOrEven(1), 1);
        System.out.println("oddOrEven " + 2);
        Assert.assertEquals(MathB.oddOrEven(2), 0);
        System.out.println("oddOrEven " + 3);
        Assert.assertEquals(MathB.oddOrEven(3), 1);
        System.out.println("oddOrEven " + 4);
        Assert.assertEquals(MathB.oddOrEven(4), 0);
        System.out.println("oddOrEven " + 5);
        Assert.assertEquals(MathB.oddOrEven(5), 1);
        System.out.println("oddOrEven " + 6);
        Assert.assertEquals(MathB.oddOrEven(6), 0);
        System.out.println("oddOrEven " + 7);
        Assert.assertEquals(MathB.oddOrEven(7), 1);
        System.out.println("oddOrEven " + 8);
        Assert.assertEquals(MathB.oddOrEven(8), 0);


        System.out.println("abs ");
        System.out.println("abc " + Integer.MIN_VALUE + " - " + MathB.abs(Integer.MIN_VALUE));
        Assert.assertEquals(MathB.abs(Integer.MIN_VALUE), -Integer.MIN_VALUE);
        System.out.println("abc " + Integer.MAX_VALUE + " - " + MathB.abs(Integer.MAX_VALUE));
        Assert.assertEquals(MathB.abs(Integer.MAX_VALUE), Integer.MAX_VALUE);
        System.out.println("abc " + 0 + " - " + MathB.abs(0));
        Assert.assertEquals(MathB.abs(0), 0);

        System.out.println("inverseNumber");
        System.out.println("inverseNumber - " + Integer.MIN_VALUE+ " - " +MathB.inverseNumber(Integer.MIN_VALUE));
        System.out.println("inverseNumber - " + -1 + " - " +MathB.inverseNumber(-1));
        System.out.println("inverseNumber - " + 0 + " - " +MathB.inverseNumber(0));
        System.out.println("inverseNumber - " + 1 + " - " +MathB.inverseNumber(1));
        System.out.println("inverseNumber - " + Integer.MAX_VALUE+ " - " +MathB.inverseNumber(Integer.MAX_VALUE));

    }
}
