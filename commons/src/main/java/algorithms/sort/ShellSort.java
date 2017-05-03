package algorithms.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * Created by whydk on 2016/9/5.
 */
public class ShellSort {

    public void shellSort(int[] a) {
        double d1 = a.length;

        while (true) {

//            这里的 增量 的选取非常关键。可究竟应该选取什么样的增量才是最好的，目前还是一个数学难题。
//            迄今为止还没有人找到一种最好的增量排序。
            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;
            System.out.println("d=" + d);

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
        System.out.println(Arrays.toString(a));
    }
}