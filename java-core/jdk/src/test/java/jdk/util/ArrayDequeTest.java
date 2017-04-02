package jdk.util;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * Created by why on 4/2/2017.
 */
public class ArrayDequeTest {

    @Test
    public void test(){
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(1);
        arrayDeque.add(1);

        arrayDeque.remove(1);

        System.out.println(arrayDeque.size());
    }
}
