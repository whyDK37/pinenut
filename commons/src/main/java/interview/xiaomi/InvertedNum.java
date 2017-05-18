package interview.xiaomi;

/**
 * Created by why on 2017/5/18.
 */
public class InvertedNum {

    public static int invertedNum(int[] array) {
        int num = 0;
        int len = array.length;
        for (int i = 0; i <= len - 1; ++i) {
            for (int j = i + 1; j <= len - 1; ++j) {
                if (array[i] > array[j]) {
                    ++num;
                }
            }
        }
        return num;
    }


    public static void main(String[] args) {
        int[] a = {4, 2, 2};
        int i = invertedNum(a);
        System.out.println(i);
    }
}
