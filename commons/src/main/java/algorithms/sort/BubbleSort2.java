package algorithms.sort;

/**
 * Created by whydk on 2016/9/5.
 */
public class BubbleSort2 {

  public static void main(String[] args) {
    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23
            , 34, 15, 35, 25, 53, 51};
    new BubbleSort2().bubbleSort(a);

    for (int i : a) {
      System.out.print(i + ", ");
    }
  }

  public void bubbleSort(int a[]) {

    for (int i = 0; i < a.length - 1; i++) {
//            注意 j 是从后往前循环
      for (int j = a.length - 2; j >= i; j--) {
//                若前者大于后者，交换两个元素。这里是与上一算法的差异
        if (a[j] > a[j + 1]) {
          swap(a, j, j + 1);
        }
      }
    }

  }

  void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
