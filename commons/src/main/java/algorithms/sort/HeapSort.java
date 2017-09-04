package algorithms.sort;

import java.util.Arrays;

/**
 * 对排序
 * Created by whydk on 2016/9/5.
 */
public class HeapSort {


  public static void main(String[] args) {
    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23,
            34, 15, 35, 25, 53, 51};
    new HeapSort().heapSort(a);
    System.out.println(Arrays.toString(a));
  }

  public void heapSort(int[] a) {

    int arrayLength = a.length;

    //循环建堆
    for (int i = 0; i < arrayLength - 1; i++) {

      //建堆
      buildMaxHeap(a, arrayLength - 1 - i);
      //交换堆顶和最后一个元素

      swap(a, 0, arrayLength - 1 - i);
      System.out.println(Arrays.toString(a));
    }

  }

  private void swap(int[] data, int i, int j) {
    int tmp = data[i];
    data[i] = data[j];
    data[j] = tmp;
  }

  //对data 数组从0到lastIndex 建大顶堆
  private void buildMaxHeap(int[] data, int lastIndex) {

    //从lastIndex 处节点（最后一个节点）的父节点开始
    for (int i = (lastIndex - 1) / 2; i >= 0; i--) {

      //k 保存正在判断的节点
      int k = i;

      //如果当前k节点的子节点存在
      while (k * 2 + 1 <= lastIndex) {

        //k 节点的左子节点的索引
        int biggerIndex = 2 * k + 1;
        //如果biggerIndex 小于lastIndex，即biggerIndex+1 代表的k 节点的右子节点存在

        if (biggerIndex < lastIndex) {
          //若果右子节点的值较大

          if (data[biggerIndex] < data[biggerIndex + 1]) {
            //biggerIndex 总是记录较大子节点的索引
            biggerIndex++;
          }
        }

        //如果k节点的值小于其较大的子节点的值
        if (data[k] < data[biggerIndex]) {

          //交换他们
          swap(data, k, biggerIndex);
          //将biggerIndex 赋予k，开始while 循环的下一次循环，重新保证k节点的值大于其左右子节点的值
          k = biggerIndex;
        } else {
          break;
        }
      }
    }
  }
}

