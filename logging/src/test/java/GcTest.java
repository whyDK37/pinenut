/**
 * Created by wanghuanyu on 2015/7/3.
 */
public class GcTest {
    public static final int onemb = 1024*1024;

    public static void main(String[] args) {
        byte[] testcase1,testcase2,testcase3,testcase4;
        testcase1 = new byte[2*onemb];
        testcase2 = new byte[2*onemb];
        testcase3 = new byte[4*onemb];
        testcase4 = new byte[4*onemb];
        testcase4 = new byte[3*onemb];

        try {
            Thread.currentThread().sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
