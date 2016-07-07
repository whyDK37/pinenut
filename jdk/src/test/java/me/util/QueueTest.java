package me.util;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<String>();
        queue.offer("Hello");
        queue.offer("World!");
        queue.offer("你好！");
        System.out.println(queue.size());
        String str;
        while((str=queue.poll())!=null){
            System.out.print(str);
        }
        System.out.println();
        System.out.println(queue.size());
    }
}
