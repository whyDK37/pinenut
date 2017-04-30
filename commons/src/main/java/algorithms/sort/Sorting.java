package algorithms.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by whydk on 2016/9/6.
 */
public class Sorting {
    // 冒泡排序法
    public void Maopao(int a[]) {

        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println("\n" + "采用冒泡排序法：");
    }


    // 插入排序法：
    public void Charu(int a[]) {
        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[i]) {
                    int temp = a[i];
                    for (int k = i; k > j; k--) {
                        a[k] = a[k--];
                    }
                    a[j] = temp;
                }
            }
        }
        System.out.println("\n" + "采用插入排序法：");
    }


    // 选择排序法：
    public void Xuanze(int a[]) {
        for (int i = 0; i < a.length; i++) {
            int position = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[position] > a[j]) {
                    int temp = a[position];
                    a[position] = a[j];
                    a[j] = temp;
                }
            }
        }
        System.out.println("\n" + "采用选择排序法：");
    }

    public void Print(int a[]) {

        System.out.println("从小到大排序结果为：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    public static void main(String[] args) {
        int a[] = new int[5];
        Sorting px = new Sorting();
        BufferedReader buf = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.println("请输入五个整数：");

        for (int i = 0; i < a.length; i++) {
            try {
                String s = buf.readLine();
                int j = Integer.parseInt(s);
                a[i] = j;
            } catch (Exception e) {
                System.out.println("出错了！必须输入整数,请重新输入!");
                i--;
            }
        }

        System.out.println("您输入的整数依次为：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
        System.out.println("\n" + "-------------");

        px.Maopao(a); // 调用冒泡算法
        px.Print(a);
        System.out.println("\n" + "-------------");
        px.Charu(a); // 调用插入算法
        px.Print(a);
        System.out.println("\n" + "-------------");

        px.Xuanze(a); // 调用选择算法
        px.Print(a);
    }
}
