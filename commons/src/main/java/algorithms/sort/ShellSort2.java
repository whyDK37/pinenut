package algorithms.sort;

/**
 * 希尔排序
 * Created by whydk on 2016/9/5.
 */
public class ShellSort2 {

  public static void main(String[] args) {
    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23
            , 34, 15, 35, 25, 53, 51};
    new ShellSort2().shellSort(a);
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + ", ");
    }
  }

  public void shellSort(int[] a) {
    int increment = a.length;

    do {

      increment = increment / 3 + 1; //增量序列
      int d = increment;

      for (int i = increment + 1; i <= a.length; i++) {

      }
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
    } while (true);
  }
}