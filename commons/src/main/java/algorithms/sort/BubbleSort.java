package algorithms.sort;

/**
 * Created by whydk on 2016/9/5.
 */
public class BubbleSort {

    public void bubbleSort(int a[]) {

        for (int i = 0; i < a.length - 1; i++) {

            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    swap(a, i, j);
                }
            }
        }

    }

    void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23
                , 34, 15, 35, 25, 53, 51};
        new BubbleSort().bubbleSort(a);

        for (int i : a) {
            System.out.print(i + ", ");
        }
    }
}
