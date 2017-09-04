package HackerRank.data_structures.arrays;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/array-left-rotation
 * Created by why on 4/26/2017.
 */
public class AlgorithmicCrush {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

//        接收参数
    String[] split = scanner.nextLine().split("[ ]");
    int n = Integer.parseInt(split[0]);//数组长度
    int m = Integer.parseInt(split[1]);//参数行数

    String[] param = new String[m];
    for (int i = 0; i < m; i++) {
      param[i] = scanner.nextLine();
    }

    int[] values = new int[n];
//        增加数据中元素的值
    for (int i = 0; i < m; i++) {
      String[] split1 = param[i].split("[ ]");
      int s = Integer.parseInt(split1[0]);
      int e = Integer.parseInt(split1[1]);
      int v = Integer.parseInt(split1[2]);
      for (int j = s - 1; j < e; j++) {
        values[j] = values[j] + v;
      }
    }

    //选出最大的值
    int maxValue = 0;
    for (int i = 0; i < values.length - 1; i++) {
      maxValue = values[i] > values[i + 1] ? values[i] : values[i + 1];
    }

    System.out.println(maxValue);

  }
}
