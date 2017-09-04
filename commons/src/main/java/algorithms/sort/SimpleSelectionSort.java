package algorithms.sort;

/**
 * 简单选择排序
 * Created by whydk on 2016/9/5.
 */
public class SimpleSelectionSort {

  public static void main(String[] args) {
    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23
            , 34, 15, 35, 25, 53, 51};
    new SimpleSelectionSort().selectionSort(a);

    for (int i : a) {
      System.out.print(i + ", ");
    }
  }

  public void selectionSort(int[] a) {


    int min = 0;

    for (int i = 0; i < a.length; i++) {

      min = i;//将当前下标定义为最小值小标
      for (int j = min + 1; j < a.length; j++) {//循环最小小标之后的数据
        if (a[min] > a[j]) {//如果有小于当前最小值的关键词
          min = j;     //将此关键字的下标赋值给min
        }
      }

      if (i != min) {//如果不相等，说明找到新的最小值，交换min和i的数据
        swap(a, i, min);
      }
    }


  }

  void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
