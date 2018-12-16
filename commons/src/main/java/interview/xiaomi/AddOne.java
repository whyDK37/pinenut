package interview.xiaomi;

import java.util.Arrays;

// fixme move to point to offer
/**
 * 用一个数组表示一个整数，每一位表示整数的一位。
 * 实现一个整数 +1 的方法
 * Created by why on 2017/5/18.
 */
public class AddOne {

  public static int[] addOne(int[] array) {
    int len = array.length;
    for (int i = len - 1; i >= 0; i--) {
      if (array[i] == 9) {
        array[i] = 0;
      } else {
        array[i]++;
        break;
      }
    }

    if (array[0] == 0) {
      int[] result = new int[len + 1];
      result[0] = 1;
      return result;
    } else {
      return array;
    }
  }


  public static void main(String[] args) {
    int[] a = {4, 1, 3, 4, 5, 6, 8, 2};
    System.out.println(Arrays.toString(addOne(a)));
  }
}
