package HackerRank.data_structures.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/dynamic-array
 * <p>
 * Created by why on 4/26/2017.
 */
public class Dynamic_Array {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);


    String[] split = scanner.nextLine().split("[ ]");
    int n = Integer.parseInt(split[0]);
    int q = Integer.parseInt(split[1]);

    int[][] seqList = new int[q][3];
    int lastAnswer = 0;

    for (int i = 0; i < q; i++) {
      split = scanner.nextLine().split("[ ]");

      seqList[i][0] = Integer.parseInt(split[0]);
      seqList[i][1] = Integer.parseInt(split[1]);
      seqList[i][2] = Integer.parseInt(split[2]);
    }


    List S0 = new ArrayList();
    List S1 = new ArrayList();


  }
}
