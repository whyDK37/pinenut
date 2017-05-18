package jdk.lang;

import org.junit.Assert;

/**
 * Created by why on 2016/3/3.
 */
public class MathBTest {

    public static void main(String[] args) {

        System.out.println("testing avg:");
        System.out.println("avg " + MyInteger.MAX_VALUE + " - " + MyInteger.MAX_VALUE);
        Assert.assertEquals(MathB.avg(MyInteger.MAX_VALUE, MyInteger.MAX_VALUE), MyInteger.MAX_VALUE);
        System.out.println("avg " + MyInteger.MIN_VALUE + " - " + MyInteger.MIN_VALUE);
        Assert.assertEquals(MathB.avg(MyInteger.MIN_VALUE, MyInteger.MIN_VALUE), MyInteger.MIN_VALUE);

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
        System.out.println("abc " + MyInteger.MIN_VALUE + " - " + MathB.abs(MyInteger.MIN_VALUE));
        Assert.assertEquals(MathB.abs(MyInteger.MIN_VALUE), -MyInteger.MIN_VALUE);
        System.out.println("abc " + MyInteger.MAX_VALUE + " - " + MathB.abs(MyInteger.MAX_VALUE));
        Assert.assertEquals(MathB.abs(MyInteger.MAX_VALUE), MyInteger.MAX_VALUE);
        System.out.println("abc " + 0 + " - " + MathB.abs(0));
        Assert.assertEquals(MathB.abs(0), 0);

        System.out.println("inverseNumber");
        System.out.println("inverseNumber - " + MyInteger.MIN_VALUE + " - " + MathB.inverseNumber(MyInteger.MIN_VALUE));
        System.out.println("inverseNumber - " + -1 + " - " +MathB.inverseNumber(-1));
        System.out.println("inverseNumber - " + 0 + " - " +MathB.inverseNumber(0));
        System.out.println("inverseNumber - " + 1 + " - " +MathB.inverseNumber(1));
        System.out.println("inverseNumber - " + MyInteger.MAX_VALUE + " - " + MathB.inverseNumber(MyInteger.MAX_VALUE));

    }
}
