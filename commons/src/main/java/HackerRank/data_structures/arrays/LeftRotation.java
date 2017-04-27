package HackerRank.data_structures.arrays;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/array-left-rotation
 * Created by why on 4/26/2017.
 */
public class LeftRotation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] split = scanner.nextLine().split("[ ]");

        int n = Integer.parseInt(split[0]);
        int leftRotation = Integer.parseInt(split[1]);

        String[] elements = scanner.nextLine().split("[ ]");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(elements[i]);
        }

//        do rotation
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
//            calc rotation index
            int left = 0;
            if (i - leftRotation < 0) {
                left = n + i - leftRotation;
            } else {
                left = i - leftRotation;
            }
            result[left] = array[i];
        }

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
