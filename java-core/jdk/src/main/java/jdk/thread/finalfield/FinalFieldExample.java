package jdk.thread.finalfield;

/**
 * 多次执行，偶尔会只打印 i=3.
 * Created by why on 3/4/2017.
 */
public class FinalFieldExample {
    final int x;
    int y = 0;
    static FinalFieldExample f;

    public FinalFieldExample() {
        x = 3;
        y = 4;
    }

    static void writer() {
        f = new FinalFieldExample();
    }

    static void reader() {
        if (f != null) {
            int i = f.x; // guaranteed to see 3
            int j = f.y; // could see 0
            System.out.println("i="+i);
            System.out.println("j="+j);
        }
    }

    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            FinalFieldExample.writer();
        });
        Thread two = new Thread(() -> {
//            while (true) {
                FinalFieldExample.reader();
//            }
        });
//        one.setDaemon(true);
//        two.setDaemon(true);
        one.start();
        two.start();
    }
}