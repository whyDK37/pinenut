package algorithms.search;

/**
 * 顺序查找方法适合无需数据集合的查找。
 * Created by why on 4/30/2017.
 */
public class SequentialSearch {
    public static int sequentialSearcn(int[] a, int n) {

        for (int i = 0; i < a.length; i++) {
            if (a[i] == n)
                return i;
        }
        return -1;
    }

    /**
     * 哨兵方式。
     * a[0]，设置为哨兵key，在循环中省去了数组坐标越界的比较，效率有一定提高。
     *
     * @param a
     * @param key
     * @return
     */
    public static int sequentialSearcn2(int[] a, int key) {

        int i;
        a[0] = key;//设置哨兵
        i = a.length - 1;
        while (a[i] != key)
            i--;
        return i;
    }

    public static void main(String[] args) {
        int a[] = {0, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23
                , 34, 15, 35, 25, 53, 51};
        int i = SequentialSearch.sequentialSearcn2(a, 51);
        System.out.println(i);
    }
}
