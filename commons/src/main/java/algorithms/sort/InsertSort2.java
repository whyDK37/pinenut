package algorithms.sort;

/**
 * 插入排序
 * Created by whydk on 2016/9/5.
 */
public class InsertSort2 {


    public void insertSort(int[] a) {


        int temp = 0;

        for (int i = 1; i < a.length; i++) {
            temp = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > temp) {
                a[j + 1] = a[j];  //将大于temp 的值整体后移一个单位
                j--;
            }
            a[j + 1] = temp;
        }


    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23,
                34, 15, 35, 25, 53, 51};

        new InsertSort2().insertSort(a);

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
    }
}
