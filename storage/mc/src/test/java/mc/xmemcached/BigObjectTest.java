package mc.xmemcached;

import foo.BigObject;
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
public class BigObjectTest {


    private static MemcachedClient mc;

    public static void main(String[] args) throws IOException, InterruptedException, MemcachedException, TimeoutException {
        String hostport = "172.16.170.128:11211";
        mc = MCUtil.createJedis(hostport);

//        mc.set("hello", 0, "Hello,mc.xmemcached");
//        String value = mc.get("hello");
//        System.out.println("hello=" + value);
//
        for (int i = 0; i < 10000; i++) {
            BigObject bigObject = new BigObject();
//            System.out.println(bigObject.toString());
            mc.set("obj"+i,0,bigObject);
        }

        long t1 = System.currentTimeMillis();
        BigObject bo = mc.get("obj1");
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println(bo.toString());

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
