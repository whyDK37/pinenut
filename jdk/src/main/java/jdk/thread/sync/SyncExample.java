package jdk.thread.sync;

/**
 * Created by whydk on 2016/5/5.
 */
public class SyncExample {

    int a = 0;
    boolean flag = false;

    public synchronized void write(){
        System.out.println("write...");
        a = 1;
        flag = true;
        System.out.println("write end.");
    }

    public synchronized void read(){
        System.out.println("read...");
        if(flag){
            int i = a;
            System.out.println(i);
        }
        System.out.println("read end.");
    }

    @Override
    public String toString() {
        return "SyncExample{" +
                "a=" + a +
                ", flag=" + flag +
                '}';
    }

    public static void main(String[] args) {
        final SyncExample syncExample = new SyncExample();
        Thread write = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("write:"+syncExample.toString());
                syncExample.write();
            }
        });
        write.setName("write");

        Thread read = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("read:"+syncExample.toString());
                syncExample.read();
            }
        });
        read.setName("read");

        Thread sout = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("sout"+i);
                }

            }
        });
        sout.setName("sout");

        sout.start();
        write.start();
        read.start();
    }
}
