package jdk.thread.volatiles;

/**
 * Created by whydk on 3/14/2017.
 */
public class VolatileExample2 {

    int a = 0;
    volatile boolean flag = false;

    public void writer() {

        a = 1; //1

        flag = true; //2
    }

    public void reader() {
        if (flag) { //3

            int i = a; //4
            System.out.println(i);
//                        ……
        }
    }

    public static void main(String[] args) {

        VolatileExample2 volatileExample2 = new VolatileExample2();
        Thread flagThread = new Thread(() -> {
            volatileExample2.writer();
        });

        Thread readerThread = new Thread(() -> {
            volatileExample2.reader();
        });

        flagThread.start();
        readerThread.start();
    }
}
