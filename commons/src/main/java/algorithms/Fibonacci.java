package algorithms;

/**
 * 斐波那契额
 * Created by why on 4/28/2017.
 */
public class Fibonacci {

    public static void main(String[] args) {
        for (int j = 1; j < 10; j++) {
            System.out.println(fib(j));
        }
    }

    /**
     * 递归
     *
     * @param i
     * @return
     */
    static int fib(int i) {
        if (i <= 2) {
            return 1;
        }
        return fib(i - 1) + fib(i - 2);
    }
}
