package date_structures_and_algorithm_analysis_in_java.chapter1;

/**
 * Created by why on 2017/5/24.
 */
public class Test6 {
    public static void main(String[] args) {
        permute("ab");

//        permutation("abc".toCharArray(), 0);
    }

    /**
     * 求二维数组的排列组合
     *
     * @param arr
     */
    public static void permutation3(final String[][] arr, final int s,final  int e) {
        if(s == e-1){
            for (String[] strings : arr) {
                for (String string : strings) {
                    System.out.print(string);
                    break;
                }
            }
            System.out.println();
        }else{

        }

    }

    public static void permutation2(char[] str, int i) {
        if (i >= str.length)
            return;
        if (i == str.length - 1) {
            System.out.println(String.valueOf(str));
        } else {
            for (int j = i; j < str.length; j++) {
                swap(str, i, j); //交换两个字符

                permutation2(str, i + 1);

                swap(str, i, j); //交换两个字符
            }
        }
    }

    public static void permute(String str) {
        char[] chars = str.toCharArray();

        permute(chars, 0, chars.length);

    }

    public static void permute(char[] pStr, int begin, int end) {
        if (begin == end - 1) //只剩一个元素，最小子问题
        {
            System.out.println(String.valueOf(pStr));
        } else {
//            分解子问题
            for (int k = begin; k < end; k++) {
                swap(pStr, k, begin); //交换两个字符
                permute(pStr, begin + 1, end);
                swap(pStr, k, begin); //交换两个字符
            }
        }
    }

    private static void swap(char[] pStr, int k, int begin) {
        char ks = pStr[k];
        pStr[k] = pStr[begin];
        pStr[begin] = ks;
    }


}
