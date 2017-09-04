package algorithms.sort;

import java.util.Arrays;

/**
 * 尾递归
 * 防止数据分布及其不平衡，递归深度将趋近于n,而不是平衡的logn。
 * 这不仅仅是速度快慢的问题。因为调用栈是有限的，每次递归都会占用栈空间，极端情况会导致栈溢出。
 * Created by whydk on 2016/9/5.
 */
public class QuickSort3 {


  public static void main(String[] args) {

    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34
            , 15, 35, 25, 53, 51};
    new QuickSort3().quickSort(a);

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

//        三数取中法 median-of-three。三个关键字先进行排序，将中间数作为轴，一般是去左端、有段和中间三个数
    int m = low + (high - low) / 2;
    if (list[low] > list[high])
      swap(list, low, high);
    if (list[m] > list[high])
      swap(list, m, high);
    if (list[m] > list[low])
      swap(list, m, low);

    int pivotKey = list[low];    //数组的第一个作为中轴

    while (low < high) {

      while (low < high && list[high] >= pivotKey) {
        high--;
      }
//            swap(list, low, high);
      list[low] = list[high];   //比中轴小的记录移到低端，减少交换

      while (low < high && list[low] <= pivotKey) {
        low++;
      }
//            swap(list, low, high);
      list[high] = list[low];   //比中轴大的记录移到高端，减少交换
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
//            _quickSort(list, pivot + 1, high);       //对高字表进行递归排序
      low = pivot + 1;//尾递归
    }
  }

  public void quick(int[] a2) {

    if (a2.length > 0) {    //查看数组是否为空
      _quickSort(a2, 0, a2.length - 1);
    }

  }
}
