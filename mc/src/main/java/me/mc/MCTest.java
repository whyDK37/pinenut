package me.mc;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.transcoders.StringTranscoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/**
 * Created by drug on 2016/4/29.
 */
public class MCTest {
    public static void main(String[] args) {
        String hostport = "192.168.1.102:11211";
        MemcachedClient client = MCUtil.createJedis(hostport);

        try {
            client.set("hello", 0, "Hello,xmemcached");
            String value = client.get("hello");
            System.out.println("hello=" + value);
//            memcachedClient.delete("hello");
//            value = memcachedClient.get("hello");
//            System.out.println("hello=" + value);


            if (!client.set("hello", 0, "world")) {
                System.err.println("set error");
            }

            if (client.add("hello", 0, "dennis")) {
                System.err.println("Add error,key is existed");
            }

            if (!client.replace("hello", 0, "dennis")) {
                System.err.println("replace error");
            }

            client.append("hello", " good");
            client.prepend("hello", "hello ");
            String name = client.get("hello", new StringTranscoder());
            System.out.println(name);
            client.deleteWithNoReply("hello");



        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

        try {
            MCStats(client);
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        try {
            client.shutdown();
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
