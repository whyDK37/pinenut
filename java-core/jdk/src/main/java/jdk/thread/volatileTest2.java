package jdk.thread;

/**
 * Created by why on 2/28/2017.
 */
public class volatileTest2 {

    public static void main(String[] args) {
        final LoopMayNeverEnd loopMayNeverEnd = new LoopMayNeverEnd();
        Thread one = new Thread(() -> {
            loopMayNeverEnd.work();
        });
        Thread two = new Thread(() -> {
            while (true) {
                loopMayNeverEnd.stopWork();
            }
        });
        one.setDaemon(true);
        two.setDaemon(true);
        one.start();
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        two.start();
    }


    static class LoopMayNeverEnd {
        volatile boolean done = false;
        int i=0;
        void work() {
            while (!done) {
// do work
                System.out.println("working  "+i++);
            }
        }

        void stopWork() {
            done = true;
        }
    }
}
