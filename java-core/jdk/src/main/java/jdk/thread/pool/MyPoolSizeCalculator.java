package jdk.thread.pool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyPoolSizeCalculator extends PoolSizeCalculator {

    public static void main(String[] args) throws InterruptedException,
            InstantiationException,
            IllegalAccessException,
            ClassNotFoundException {
        MyPoolSizeCalculator calculator = new MyPoolSizeCalculator();
        calculator.calculateBoundaries(new BigDecimal(1.0), //cpu 的利用率，1就是100%  
                new BigDecimal(100000));
    }

    // u need to implement  
    protected long getCurrentThreadCPUTime() {
        return ManagementFactory.getThreadMXBean().getCurrentThreadCpuTime();
    }

    // u need to implement  
    protected Runnable creatTask() {
        return new AsyncIOTask();
    }

    // u need to implement  
    protected BlockingQueue<Runnable> createWorkQueue() {
        return new LinkedBlockingQueue();
    }

}


class AsyncIOTask implements Runnable {

    @Override
    public void run() {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            String getURL = "http://baidu.com";
            URL getUrl = new URL(getURL);

            connection = (HttpURLConnection) getUrl.openConnection();
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // empty loop  
            }
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {

                }
            }
            connection.disconnect();
        }
    }
}  