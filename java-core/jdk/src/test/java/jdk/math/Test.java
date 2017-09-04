package jdk.math;

import java.util.HashMap;

/**
 * Created by drug on 2016/3/2.
 */
public class Test {

  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(13));
    HashMap hashMap = new HashMap();
    hashMap.put(new Long(0), null);
    hashMap.put(new Integer(0), null);
    hashMap.put(null, null);
    hashMap.put(null, null);
    System.out.println(hashMap.size());
    System.out.println(3 << 1);
//        System.out.println(MyInteger.toBinaryString(-12));
//        System.out.println(MyInteger.toBinaryString(-12>>>1));
//
//        System.out.println(MyInteger.toBinaryString(12));
//        System.out.println(MyInteger.toBinaryString(12>>>1));
//        System.out.println(MyInteger.toBinaryString(12>>>1));
//
//        System.out.println(MyInteger.toBinaryString(1));
//        System.out.println(MyInteger.toBinaryString(2));
//        System.out.println(MyInteger.toBinaryString(3));
//        System.out.println(MyInteger.toBinaryString(4));
//        System.out.println(MyInteger.toBinaryString(5));
//        System.out.println(MyInteger.toBinaryString(6));
//        System.out.println(MyInteger.toBinaryString(7));
//        System.out.println(MyInteger.toBinaryString(8));
//        System.out.println(MyInteger.toBinaryString(9));
//        System.out.println(MyInteger.toBinaryString(10));
//
//        int h = 0x7FFFFFFF;
//        System.out.println(hash(h));
  }

  static int hash(int h) {
    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
  }
}
