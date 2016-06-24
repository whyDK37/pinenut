package xmemcached;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * Created by drug on 2016/4/29.
 */
public class MCTest {

    private static Logger log = Logger.getLogger( MCTest.class.getName() );

    private static MemcachedClient mc;
    public static void main(String[] args) {
        String hostport = "192.168.1.102:11211 192.168.1.102:11212";
        try {
            mc = MCUtil.createJedis(hostport);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mc.set("hello", 0, "Hello,xmemcached");
            String value = mc.get("hello");
            System.out.println("hello=" + value);
//            memcachedClient.delete("hello");
//            value = memcachedClient.get("hello");
//            System.out.println("hello=" + value);

            if (!mc.set("hello", 0, "world")) {
                System.err.println("set error");
            }

            if (mc.add("hello", 0, "dennis")) {
                System.err.println("Add error,key is existed");
            }

            if (!mc.replace("hello", 0, "dennis")) {
                System.err.println("replace error");
            }

            mc.append("hello", " good");
            mc.prepend("hello", "hello ");
            String name = mc.get("hello", new StringTranscoder());
            System.out.println(name);
            mc.deleteWithNoReply("hello");



        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

        try {
            MCStats(mc);
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        try {
            mc.shutdown();
        } catch (IOException e) {
            System.err.println("Shutdown MemcachedClient fail");
            e.printStackTrace();
        }
    }

    private static void MCStats(MemcachedClient client) throws MemcachedException, InterruptedException, TimeoutException {
        Map<InetSocketAddress,Map<String,String>> result = client.getStats();
        Set<java.util.Map.Entry<InetSocketAddress,Map<String,String>>> resultEntry = result.entrySet();
        for (java.util.Map.Entry<InetSocketAddress,Map<String,String>> entry: resultEntry){
            System.out.println();
            System.out.println(entry.getKey().toString());
            Set<Map.Entry<String,String>> values = entry.getValue().entrySet();
            for (Map.Entry<String,String> ventry:values){
                System.out.println("\t"+ventry.getKey()+"-"+ventry.getValue());
            }
        }

    }
}
