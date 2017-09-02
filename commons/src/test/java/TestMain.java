/**
 * Created by why on 2017/5/24.
 */
public class TestMain {
    public static void main(String[] args) {
        printOut(123456789);
    }

    public static void printOut(int n) {
        if (n >= 10) {
            printOut(n / 10);
        }
        printDigit(n % 10);
    }

    private static void printDigit(int n) {
        System.out.print(n);
    }
}
