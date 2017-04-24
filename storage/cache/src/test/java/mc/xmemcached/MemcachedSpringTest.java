package mc.xmemcached;

import static org.junit.Assert.*;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemcachedSpringTest {

    private ApplicationContext app;
    private MemcachedClient memcachedClient;

    @Before
    public void init() {
        app = new ClassPathXmlApplicationContext("spring-mc.xml");
        memcachedClient = (MemcachedClient) app.getBean("memcachedClient");
    }

    @Test
    public void test() {
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
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
    }
}  
