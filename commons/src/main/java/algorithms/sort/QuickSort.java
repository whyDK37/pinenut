package algorithms.sort;

import java.util.Arrays;

/**
 * Created by whydk on 2016/9/5.
 */
public class QuickSort {


  public static void main(String[] args) {

    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34
            , 15, 35, 25, 53, 51};
    new QuickSort().quickSort(a);

    System.out.println(Arrays.toString(a));
  }

  public void quickSort(int[] a) {

    quick(a);


  }

  /**
   * 先选取一个关键字，比如选择第一个关键字，然后想尽办法将他放到一个位置，使得他缩编的值都比它小，
   * 我们将这样的关键字成为 pivot 轴。
   *
   * @param list
   * @param low
   * @param high
   * @return
   */
  public int getMiddle(int[] list, int low, int high) {

    int pivotKey = list[low];    //数组的第一个作为中轴

    while (low < high) {

      while (low < high && list[high] >= pivotKey) {
        high--;
      }

      swap(list, low, high);
//            list[low] = list[high];   //比中轴小的记录移到低端

      while (low < high && list[low] <= pivotKey) {
        low++;
      }

      swap(list, low, high);

//            list[high] = list[low];   //比中轴大的记录移到高端
    }

    list[low] = pivotKey;              //中轴记录到尾

    return low;                   //返回中轴的位置

  }

  private void swap(int[] list, int low, int high) {
    int tmp = list[low];
    list[low] = list[high];
    list[high] = tmp;
  }

  public void _quickSort(int[] list, int low, int high) {

    if (low < high) {

//            中心点
      int pivot = getMiddle(list, low, high);  //将list 数组进行一分为二
      _quickSort(list, low, pivot - 1);       //对低字表进行递归排序
      _quickSort(list, pivot + 1, high);       //对高字表进行递归排序
    }
  }

  public void quick(int[] a2) {

    if (a2.length > 0) {    //查看数组是否为空

      _quickSort(a2, 0, a2.length - 1);

    }

  }
}
