package HackerRank.data_structures.arrays;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/array-left-rotation
 * Created by why on 4/26/2017.
 */
public class SparseArrays {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    int n = Integer.parseInt(scanner.nextLine());
    String[] strings = new String[n];
    for (int i = 0; i < n; i++) {
      strings[i] = scanner.nextLine();
    }

    int q = Integer.parseInt(scanner.nextLine());
    String[] qstrings = new String[q];
    for (int i = 0; i < q; i++) {
      qstrings[i] = scanner.nextLine();
    }


    int[] qcount = new int[q];
    for (int i = 0; i < qcount.length; i++) {
      int count = 0;
      for (int j = 0; j < strings.length; j++) {
        if (strings[j].equals(qstrings[i])) {
          count++;
        }
      }

      qcount[i] = count;
      System.out.println(count);
    }


  }
}
