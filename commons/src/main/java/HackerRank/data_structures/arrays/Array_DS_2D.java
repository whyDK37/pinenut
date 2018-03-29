package HackerRank.data_structures.arrays;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/2d-array
 * <p>
 * Context
 * Given a
 * <p>
 * <p>
 * 2D Array,
 * <p>
 * :
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * We define an hourglass in
 * <p>
 * to be a subset of values with indices falling in this pattern in
 * <p>
 * 's graphical representation:
 * a b c
 * d
 * e f g
 * There are
 * <p>
 * hourglasses in
 * <p>
 * , and an hourglass sum is the sum of an hourglass' values.
 * Task
 * Calculate the hourglass sum for every hourglass in
 * <p>
 * , then print the maximum hourglass sum.
 * Note: If you have already solved the Java domain's Java 2D Array challenge, you may wish to skip this challenge.
 * Input Format
 * There are
 * <p>
 * lines of input, where each line contains
 * <p>
 * space-separated integers describing 2D Array
 * <p>
 * ; every value in
 * <p>
 * will be in the inclusive range of
 * <p>
 * <p>
 * to
 * <p>
 * .
 * Constraints
 * <p>
 * Output Format
 * Print the largest (maximum) hourglass sum found in
 * <p>
 * .
 * BytebuddySample Input
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 2 4 4 0
 * 0 0 0 2 0 0
 * 0 0 1 2 4 0
 * BytebuddySample Output
 * 19
 * Explanation
 * <p>
 * contains the following hourglasses:
 * 1 1 1   1 1 0   1 0 0   0 0 0
 * 1       0       0       0
 * 1 1 1   1 1 0   1 0 0   0 0 0
 * <p>
 * 0 1 0   1 0 0   0 0 0   0 0 0
 * 1       1       0       0
 * 0 0 2   0 2 4   2 4 4   4 4 0
 * <p>
 * 1 1 1   1 1 0   1 0 0   0 0 0
 * 0       2       4       4
 * 0 0 0   0 0 2   0 2 0   2 0 0
 * <p>
 * 0 0 2   0 2 4   2 4 4   4 4 0
 * 0       0       2       0
 * 0 0 1   0 1 2   1 2 4   2 4 0
 * The hourglass with the maximum sum (
 * <p>
 * ) is:
 * 2 4 4
 * 2
 * 1 2 4
 * <p>
 * Created by why on 4/26/2017.
 */
public class Array_DS_2D {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int cubeSize = 6;

    int[][] cube = new int[cubeSize][cubeSize];
    for (int i = 0; i < cubeSize; i++) {
      String s = scanner.nextLine();
      String[] split = s.split("[ ]");
      for (int i1 = 0; i1 < split.length; i1++) {
        cube[i][i1] = Integer.parseInt(split[i1]);
      }
    }

    int maxsum = 0;
    for (int i = 2; i < cubeSize; i++) {
      for (int j = 2; j < cubeSize; j++) {
        int tmp = cube[i - 2][j - 2] + cube[i - 2][j - 1] + cube[i - 2][j - 0];
        tmp += cube[i - 1][j - 1];
        tmp += cube[i - 0][j - 2] + cube[i - 0][j - 1] + cube[i - 0][j - 0];

        maxsum = maxsum > tmp ? maxsum : tmp;
      }
    }

    System.out.println(maxsum);
  }
}
