package mc.xmemcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * Created by drug on 2016/4/29.
 */
public class BigStringTest {


    private static MemcachedClient mc;

    public static void main(String[] args) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        String hostport = "127.0.0.1:11211";
        mc = MCUtil.createJedis(hostport);

//
        for (int i = 0; i < 10000; i++) {
            mc.set("obj" + i, 0, "i am a big string " + i);
        }

        long t1 = System.currentTimeMillis();
        String str = mc.get("obj1");
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
        System.out.println(str);

//        MCStats(mc);

        mc.shutdown();
    }

    private static void MCStats(MemcachedClient client) throws MemcachedException, InterruptedException, TimeoutException {
        Map<InetSocketAddress, Map<String, String>> result = client.getStats();
        Set<Map.Entry<InetSocketAddress, Map<String, String>>> resultEntry = result.entrySet();
        for (Map.Entry<InetSocketAddress, Map<String, String>> entry : resultEntry) {
            System.out.println();
            System.out.println(entry.getKey().toString());
            Set<Map.Entry<String, String>> values = entry.getValue().entrySet();
            for (Map.Entry<String, String> ventry : values) {
                System.out.println("\t" + ventry.getKey() + "-" + ventry.getValue());
            }
        }

    }
}
