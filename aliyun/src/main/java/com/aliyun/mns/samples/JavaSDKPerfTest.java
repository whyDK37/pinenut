package com.aliyun.mns.samples;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.http.ClientConfiguration;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.QueueMeta;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

public class JavaSDKPerfTest {
    private static MNSClient client = null;
    private static AtomicLong totalCount = new AtomicLong(0);

    private static String endpoint = null;
    private static String accessId = null;
    private static String accessKey = null;

    private static String queueName = "JavaSDKPerfTestQueue";
    private static int threadNum = 100;
    private static int totalSeconds = 180;

    protected static boolean parseConf() {
        String confFilePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "perf_test_config.properties";

        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(confFilePath));
            if (bis == null) {
                System.out.println("ConfFile not opened: " + confFilePath);
                return false;
            }
        } catch (FileNotFoundException e) {
            System.out.println("ConfFile not found: " + confFilePath);
            return false;
        }

        // load file
        Properties properties = new Properties();
        try {
            properties.load(bis);
        } catch(IOException e) {
            System.out.println("Load ConfFile Failed: " + e.getMessage());
            return false;
        } finally {
            try {
                bis.close();
            } catch (Exception e) {
                // do nothing
            }
        }

        // init the member parameters
        endpoint = properties.getProperty("Endpoint");
        System.out.println("Endpoint: " + endpoint);
        accessId = properties.getProperty("AccessId");
        System.out.println("AccessId: " + accessId);
        accessKey = properties.getProperty("AccessKey");

        queueName = properties.getProperty("QueueName", queueName);
        System.out.println("QueueName: " + queueName);
        threadNum = Integer.parseInt(properties.getProperty("ThreadNum", String.valueOf(threadNum)));
        System.out.println("ThreadNum: " + threadNum);
        totalSeconds = Integer.parseInt(properties.getProperty("TotalSeconds", String.valueOf(totalSeconds)));
        System.out.println("TotalSeconds: " + totalSeconds);

        return true;
    }

    public static void main(String[] args) {
        if (!parseConf()) {
            return;
        }

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setMaxConnections(threadNum);
        clientConfiguration.setMaxConnectionsPerRoute(threadNum);

        CloudAccount cloudAccount = new CloudAccount(accessId, accessKey, endpoint, clientConfiguration);
        client = cloudAccount.getMNSClient();

        CloudQueue queue = client.getQueueRef(queueName);
        queue.delete();

        QueueMeta meta = new QueueMeta();
        meta.setQueueName(queueName);
        client.createQueue(meta);

        // 1. Now check the SendMessage
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < threadNum; ++i){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        CloudQueue queue = client.getQueueRef(queueName);
                        Message message = new Message();
                        message.setMessageBody("Test");
                        long count = 0;
                        long startTime = System.currentTimeMillis();

                        System.out.println(startTime);
                        long endTime = startTime + totalSeconds * 1000;
                        while (true) {
                            for (int i = 0; i < 50; ++i) {
                                queue.putMessage(message);
                            }
                            count += 50;

                            if (System.currentTimeMillis() >= endTime) {
                                break;
                            }
                        }

                        System.out.println(System.currentTimeMillis());
                        System.out.println("Thread" + Thread.currentThread().getName() + ": " + String.valueOf(count));
                        totalCount.addAndGet(count);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, String.valueOf(i));
            thread.start();
            threads.add(thread);
        }

        for (int i = 0; i < threadNum; ++i) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("SendMessage QPS: ");
        System.out.println(totalCount.get() / totalSeconds);

        // 2. Now is the ReceiveMessage
        threads.clear();
        totalCount.set(0);

        totalSeconds = totalSeconds / 3; // To ensure that messages in queue are enough for receiving
        for (int i = 0; i < threadNum; ++i){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        CloudQueue queue = client.getQueueRef(queueName);
                        long count = 0;
                        long endTime = System.currentTimeMillis() + totalSeconds * 1000;

                        while (true) {
                            for (int i = 0; i < 50; ++i) {
                                queue.popMessage();
                            }
                            count += 50;

                            if (System.currentTimeMillis() >= endTime) {
                                break;
                            }
                        }

                        System.out.println("Thread" + Thread.currentThread().getName() + ": " + String.valueOf(count));
                        totalCount.addAndGet(count);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, String.valueOf(i));
            thread.start();
            threads.add(thread);
        }

        for (int i = 0; i < threadNum; ++i) {
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("ReceiveMessage QPS: ");
        System.out.println(totalCount.get() / totalSeconds);

        return;
    }
}
