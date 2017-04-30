package algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by whydk on 2016/9/6.
 */
public class RadixSort {


    public void radixSort(int[] a) {

        sort(a);



    }

    public void sort(int[] array) {

        //首先确定排序的趟数;

        int max = array[0];

        for (int i = 1; i < array.length; i++) {

            if (array[i] > max) {

                max = array[i];

            }

        }

        int time = 0;

        //判断位数;

        while (max > 0) {

            max /= 10;

            time++;

        }


        //建立10个队列;

        List<ArrayList> queue = new ArrayList<ArrayList>();

        for (int i = 0; i < 10; i++) {

            ArrayList<Integer> queue1 = new ArrayList<Integer>();

            queue.add(queue1);

        }


        //进行time 次分配和收集;

        for (int i = 0; i < time; i++) {

            //分配数组元素;

            for (int j = 0; j < array.length; j++) {

                //得到数字的第time+1 位数;

                int x = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);


                ArrayList<Integer> queue2 = queue.get(x);

                queue2.add(array[j]);

                queue.set(x, queue2);

            }

            int count = 0;//元素计数器;

            //收集队列元素;

            for (int k = 0; k < 10; k++) {

                while (queue.get(k).size() > 0) {

                    ArrayList<Integer> queue3 = queue.get(k);

                    array[count] = queue3.get(0);

                    queue3.remove(0);

                    count++;

                }


            }

        }

    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 101, 56, 17, 18
                , 23, 34, 15, 35, 25, 53, 51};
        new RadixSort().radixSort(a);

        System.out.println(Arrays.toString(a));
    }

}


