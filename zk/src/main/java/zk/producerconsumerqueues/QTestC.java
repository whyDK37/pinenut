package zk.producerconsumerqueues;

/**
 * Created by drug on 2016/4/26.
 */
public class QTestC {
    public static void main(String[] args) {
        String[] c = new String[]{"qTest","127.0.0.1:2181","100","c"};
        SyncPrimitive.main(c);
    }
}
