package com.aliyun.ots;

import com.aliyun.openservices.ots.OTSClient;

/**
 * Created by whydk on 2016/7/1.
 */
public class OTSClientEnhance {
    OTSClient client;

    public void shutdown() {
        client.shutdown();
    }
}
