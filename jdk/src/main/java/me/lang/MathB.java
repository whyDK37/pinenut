package me.lang;

/**
 * 位运算
 * Created by drug on 2016/3/3.
 */
public class MathB {

    /**
     * 求两个数的平均值。
     * 求平均值，比如有两个int类型变量x、y,首先要求x+y的和，再除以2，
     * 但是有可能x+y的结果会超过int的最大表示范围，所以位运算就派上用场啦。
     *
     * @param x
     * @param y
     * @return
     */
    public static int avg(int x, int y) {
        return (x & y) + ((x ^ y) >> 1);
    }

    /**
     * 偶数:英文:even奇数英文:odd
     * 定义：整数中,能够被2整除的数,叫做偶数,不能被2整除的数,叫奇数.
     * 特别提示：偶数包括正偶数、负偶数和0.
     * 0是一个特殊的偶数.小学规定0为最小的偶数,但是在初中学习了负数,出现了负偶数时,0就不是最小的偶数了.
     * <pre>
     * a&1  = 0 偶数
     * a&1 =  1 奇数
     * </pre>
     *
     * @return 0 偶数,1 奇数
     */
    public static int oddOrEven(int i) {
        return i & 1;
    }

    /**
     * 求绝对值
     *
     * @param x
     * @return
     */
    public static int abs(int x) {
        int y;
        y = x >> 31;
        return (x ^ y) - y;  //or: (x+y)^y
    }

    /**
     * 求相反数
     *
     * @param x
     * @return
     */
    public static int inverseNumber(int x) {
        return (~x + 1);
    }
}
