package algorithms.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by whydk on 2016/9/6.
 */
public class BinarySearch {

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<Integer>();
        addIntegerInSequence(a, 1, 10);
        print(a);
        int pos = binarySearch2(a, 10);
        if (pos != -1) {
            System.out.print("Element found: " + pos);
        } else {
            System.out.print("Element not found");
        }
    }

    /**
     * 二分查找法
     *
     * @param a
     * @param value 待查找元素
     * @return
     */
    public static int binarySearch(List<Integer> a, int value) {
        int size = a.size();
        int low = 0, high = size - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (a.get(mid) < value) {
                low = low + 1;
            } else if (a.get(mid) > value) {
                high = high - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 优化后的折半查找法
     *
     * @param a
     * @param value
     * @return
     */
    public static int binarySearch2(List<Integer> a, int value) {
        int size = a.size();
        int low = 0, high = size - 1;
        int mid;
        while (low <= high) {
            //优化的关键一行代码
            mid = low + (high - low) * (value - a.get(low)) / (a.get(high) - a.get(low));
            if (a.get(mid) < value) {
                low = low + 1;
            } else if (a.get(mid) > value) {
                high = high - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 填充顺序元素到数组
     *
     * @param a
     * @param begin 开始元素
     * @param size  大小
     */
    public static void addIntegerInSequence(List<Integer> a, int begin, int size) {
        for (int i = begin; i < begin + size; i++) {
            a.add(i);
        }
    }

    /**
     * 打印数组
     *
     * @param a
     */
    public static void print(List<Integer> a) {
        Iterator<Integer> i = a.iterator();
        while (i.hasNext()) {
            System.out.print(i.next() + " ");
        }
        System.out.println("");
    }


///// JAVA 库中的二分查找使用非递归方式实现，返回结果与前面写的有所不同：找不到时返回的是负数，但不一定是-1

    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {

        int low = fromIndex;
        int high = toIndex - 1;


        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];
            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }
}