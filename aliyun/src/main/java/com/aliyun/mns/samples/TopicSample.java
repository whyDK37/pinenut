package com.aliyun.mns.samples;


import com.aliyun.mns.common.utils.ServiceSettings;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class TopicSample {

    public static void main(String[] args) throws IOException {
        Enumeration<URL> urls = ClassLoader.getSystemResources("aliyun-mns.properties");
        URL url;
        while (urls.hasMoreElements()) {
            url = urls.nextElement();
            System.out.println(url.toString());
            ServiceSettings.load(url.getFile());
            break;
        }
        System.out.println(ServiceSettings.getMNSAccessKeyId());
        Sample sample = new Sample();
        sample.runTopic();
        sample.clear();

        System.exit(0);
    }


}
