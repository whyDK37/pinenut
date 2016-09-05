package sort;

/**
 * Created by whydk on 2016/9/5.
 */
public class SelectSort {

    public void selectSort() {

        int a[] = {1, 54, 6, 3, 78, 34, 12, 45};
        int position = 0;

        for (int i = 0; i < a.length; i++) {

            int j = i + 1;
            position = i;
            int temp = a[i];

            for (; j < a.length; j++) {
                if (a[j] < temp) {
                    temp = a[j];
                    position = j;
                }
            }

            a[position] = a[i];
            a[i] = temp;
        }


        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);

    }

    public static void main(String[] args) {
        new SelectSort().selectSort();
    }
}
