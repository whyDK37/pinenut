package xmemcached;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.junit.Test;

public class MemcachedClientTest {
    public static void main(String[] args) {
        new MemcachedClientTest().test();
    }

    @Test
    public void test() {
//        MemcachedClientBuilder builder = new XMemcachedClientBuilder(
//                AddrUtil.getAddresses("10.11.155.26:11211 10.11.155.41:11211 10.10.76.31:11211 10.10.76.35:11211"),
//                new int[] { 1, 1, 1, 1 });
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(
                AddrUtil.getAddresses("192.168.1.102:11211"),
                new int[]{1, 1, 1, 1});
        // 设置连接池大小，即客户端个数  
        builder.setConnectionPoolSize(50);

        // 宕机报警  
        builder.setFailureMode(true);

        // 使用二进制文件  
        builder.setCommandFactory(new BinaryCommandFactory());

        MemcachedClient memcachedClient = null;
        try {
            memcachedClient = builder.build();
            try {
                // 设置/获取  
                memcachedClient.set("zlex", 36000, "set/get");
                assertEquals("set/get", memcachedClient.get("zlex"));

                // 替换  
                memcachedClient.replace("zlex", 36000, "replace");
                assertEquals("replace", memcachedClient.get("zlex"));

                // 移除  
                memcachedClient.delete("zlex");
                assertNull(memcachedClient.get("zlex"));
            } catch (TimeoutException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            } catch (MemcachedException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        } finally {
            if (memcachedClient != null) {
                try {
                    memcachedClient.shutdown();
                } catch (IOException e) {
                    // TODO Auto-generated catch block  
                    e.printStackTrace();
                }
            }
        }
    }

//    private void assertNull(Object zlex) {
//        if(!(zlex == null)){
//            throw new IllegalArgumentException(zlex+" not null");
//        }
//    }
//
//    private void assertEquals(String s, Object zlex) {
//        if(!s.equals(zlex)){
//            throw new IllegalArgumentException(s+" not equals "+zlex);
//
//        }
//    }
}  