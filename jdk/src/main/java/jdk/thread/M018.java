package jdk.thread;

import static java.lang.System.*;

public class M018 {
    public static void main(String[] args) throws Exception {
        M018 m18 = new M018();
        m18.one(m18);
        out.println("Hello World!");
    }

    public void one(M018 n) throws Exception {
        Object obj = new Object();
        synchronized (this) {
            out.println(Thread.currentThread().toString());
            n.wait();
        }
    }
}  