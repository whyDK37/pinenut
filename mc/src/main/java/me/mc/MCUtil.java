package me.mc;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import java.io.IOException;

/**
 * Created by drug on 2016/4/29.
 */
public class MCUtil {
    public static MemcachedClient createJedis(String hostport) {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses(hostport));

        MemcachedClient memcachedClient = null;
        try {
            memcachedClient = builder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memcachedClient;
    }
}
