package me.math;

/**
 * Created by drug on 2016/3/2.
 */
public class Test {

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(-12));
//        System.out.println(Integer.toBinaryString(-12>>>1));
//
//        System.out.println(Integer.toBinaryString(12));
//        System.out.println(Integer.toBinaryString(12>>>1));
//        System.out.println(Integer.toBinaryString(12>>>1));
//
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(6));
        System.out.println(Integer.toBinaryString(7));
        System.out.println(Integer.toBinaryString(8));
        System.out.println(Integer.toBinaryString(9));
        System.out.println(Integer.toBinaryString(10));

        int h = 0x7FFFFFFF;
        System.out.println(hash(h));
    }

    static int hash(int h) {
        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
}
