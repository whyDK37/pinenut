package logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.annotations.Test;

import java.lang.ref.WeakReference;

/**
 * Created by root on 15-7-1.
 */
public class LogTest {

    @Test
    public void test() {
        Log logger = LogFactory.getLog(LogTest.class);
        logger.info("init");
    }

    public static void main(String[] args) {
        LogTest logTest = new LogTest();
        WeakReference<LogTest> wf = new WeakReference<LogTest>(logTest);

        int count = 0;
        wf.enqueue();
        while (true) {
            System.gc();
            if (wf.get() != null) {
                System.out.println(count++);
            } else {
                System.out.println("Object has framework.bean gc.");
                break;
            }
        }
    }

    public String get() {
        return "abc";
    }

    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
