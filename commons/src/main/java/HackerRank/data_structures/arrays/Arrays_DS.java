package HackerRank.data_structures.arrays;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/arrays-ds
 * <p>
 * An array is a type of data structure that stores elements of the same type in a contiguous block of memory. In an array,
 * <p>
 * , of size
 * <p>
 * , each memory location has some unique index,
 * <p>
 * (where
 * <p>
 * ), that can be referenced as
 * <p>
 * (you may also see it written as
 * <p>
 * ).
 * Given an array,
 * <p>
 * , of
 * <p>
 * integers, print each element in reverse order as a single line of space-separated integers.
 * Note: If you've already solved our C++ domain's Arrays Introduction challenge, you may want to skip this.
 * Input Format
 * The first line contains an integer,
 * <p>
 * (the number of integers in
 * <p>
 * ).
 * The second line contains
 * <p>
 * space-separated integers describing
 * <p>
 * .
 * Constraints
 * <p>
 * Output Format
 * Print all
 * <p>
 * integers in
 * <p>
 * in reverse order as a single line of space-separated integers.
 * Sample Input
 * 4
 * 1 4 3 2
 * Sample Output
 * 2 3 4 1
 * <p>
 * Created by why on 4/26/2017.
 */
public class Arrays_DS {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine();//跳过第一行结尾的回车
        String elements = scanner.nextLine();
        String[] split = elements.split("[ ]");

        int[] out = new int[n];
        for (int i = 0; i < n && i < split.length; i++) {
            out[i] = Integer.parseInt(split[i]);
        }

        for (int i = out.length - 1; i >= 0; i--) {
            System.out.print(out[i] + " ");
        }

    }
}
