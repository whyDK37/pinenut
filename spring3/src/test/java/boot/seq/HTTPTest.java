package boot.seq;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by whydk on 2016/7/6.
 */
public class HTTPTest {
    private static final int COREPOOLSIZE = 1000;
    private static final int MAXINUMPOOLSIZE = 1000;
    private static final long KEEPALIVETIME = 5;
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORKQUEUE = new ArrayBlockingQueue<Runnable>(1000);
    private static final ThreadPoolExecutor.AbortPolicy HANDLER = new ThreadPoolExecutor.AbortPolicy();
    int fc = 100;
    @Test
    public void test() {
        Runnable caller = new Runnable() {
            @Override
            public void run() {
                doHttpCall();
            }
        };

        // 初始化线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(COREPOOLSIZE, MAXINUMPOOLSIZE, KEEPALIVETIME, UNIT, WORKQUEUE, HANDLER);
        for (int i = 1; i < 1000; i++) {
            String task = "task@" + i;
            System.out.println("put->" + task);
            threadPool.execute(caller);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(threadPool.getActiveCount() > 0);
//        threadPool.shutdown();//关闭主线程，但线程池会继续运行，直到所有任务执行完才会停止。若不调用该方法线程池会一直保持下去，以便随时添加新的任务

    }

    private String testUrl = "http://localhost:8080";

    @Test
    public void doHttpCall() {
        String url = testUrl + "/seq?fc="+fc;
        long start = System.currentTimeMillis();
        send(url, "");
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    private static String send(String url, String reqData) {
        System.out.println("请求参数:" + reqData);
        String returnStr = "init";
        try {
            long t1 = System.currentTimeMillis();
            URL targetUrl = new URL(url);
            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");


            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(reqData.getBytes());
            outputStream.flush();
            long t2 = System.currentTimeMillis();
            System.out.println("t2-t1:"+(t2-t1));
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader((httpConnection.getInputStream()),
                    "utf-8"));

            String output;
            System.out.println("Output from Server:");
            while ((output = responseBuffer.readLine()) != null) {
                returnStr += output;
            }
            System.out.println(returnStr);
            long t3 = System.currentTimeMillis();
            System.out.println("t3-t2:"+(t3-t2));

            httpConnection.disconnect();
            long t4 = System.currentTimeMillis();
            System.out.println("t4-t3:"+(t4-t3));

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return returnStr;
    }
}
