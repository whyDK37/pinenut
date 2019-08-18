package jvm.gc;

/**
 * jvm 参数: -Xms10m -Xmx10m
 * jdk 11 ， old区会增长到3mb再回收到2mb， 按照这个程序的逻辑，应该只会有minigc，old区不会增加， 往后了解一下什么原因
 */
public class MiniGc {
    public static void main(String[] args) {
        int i = 0;
        while (true) {
            new Order(1L, "testUser");
            System.out.println(i++);
        }
    }
}
