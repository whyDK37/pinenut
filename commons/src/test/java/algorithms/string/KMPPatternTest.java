package algorithms.string;

import org.testng.annotations.Test;

/**
 * Created by why on 4/29/2017.
 */
public class KMPPatternTest {

    @Test
    public void test() {
        String t = "ababaaaba";

        int[] nxt = getNextVal(t);
        for (int i : nxt) {
            System.out.print(i);
        }
    }


    public int[] getNextVal(String str) {
        char[] chars = str.toCharArray();
        int[] next = new int[chars.length];

        int i = 1, j = 0;

        while (i < chars.length - 1) {//这个地方要注意是len-1  不是len，不然会数组越界的
//          i 表示后缀的单个字符
//          j 表示前缀的单个单词
            if (j == 0 || chars[i] == chars[j]) {
                ++i;
                ++j;
                if (chars[i] != chars[j])
                    next[i] = j;
                else
                    next[i] = next[j];
            } else {
                j = next[j];//若字符不相同，则j值回溯
            }
        }
        return next;
    }

    public int[] getNext(String str) {
        char[] chars = str.toCharArray();
        int[] next = new int[chars.length];

        int i = 1, j = 0;

        while (i < chars.length - 1) {//这个地方要注意是len-1  不是len，不然会数组越界的
//          i 表示后缀的单个字符
//          j 表示前缀的单个单词
            if (j == 0 || chars[i] == chars[j]) {
                ++i;
                ++j;
                next[i] = j;
            } else {
                j = next[j];//若字符不相同，则j值回溯
            }
        }
        return next;
    }


    /**
     * 具体的匹配
     *
     * @param str1
     * @param str2
     * @return
     */
    public int kMPMatcher(String str1, String str2) {

        int i = 0, j = -1;
        int arr[] = next(str2);

        while (i < str1.length() && j < str2.length()) {

            if (j == -1 || str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else j = arr[j];

        }
        if (j == str2.length())
            return i - j;


        return -1;
    }

    /**
     * next函数
     *
     * @param str
     * @return
     */
    public int[] next(String str) {

        int j = -1, i = 0;
        int arr[] = new int[str.length() + 1];
        arr[0] = -1;

        while (i < str.length()) {
            if (j == -1 || str.charAt(i) == str.charAt(j)) {
                j++;
                i++;
                arr[i] = j;
            } else
                j = arr[j];

        }


        return arr;
    }
}