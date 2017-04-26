package zk.producerconsumerqueues;

/**
 * Created by drug on 2016/4/26.
 */
public class BarrierTest {
    public static void main(String[] args) {
        String[] p = new String[]{"barrierTest", "127.0.0.1:2181", "100", "p"};
        SyncPrimitive.main(p);
    }
}
