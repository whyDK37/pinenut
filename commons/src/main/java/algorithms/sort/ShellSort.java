package algorithms.sort;

/**
 * 希尔排序
 * Created by whydk on 2016/9/5.
 */
public class ShellSort {

    public void shellSort(int[] a) {
        double d1 = a.length;

        while (true) {

            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;

            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < a.length; i += d) {
                    int j = i - d;
                    int temp = a[i];
                    for (; j >= 0 && temp < a[j]; j -= d) {
                        a[j + d] = a[j];
                    }
                    a[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23
                , 34, 15, 35, 25, 53, 51};
        new ShellSort().shellSort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
    }
}