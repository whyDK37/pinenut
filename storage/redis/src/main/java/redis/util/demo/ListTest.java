package redis.util.demo;

import redis.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * redis hash
 * Created by drug on 2016/4/27.
 */
public class ListTest {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6379;

        Jedis jedis = JedisUtil.createJedis(host,port);

        System.out.println("设置 seq 值并获取。");
        for (int i = 0; i < 200; i++) {
            jedis.rpush("seq",i+"");
        }
        for (int i = 0; i < 205; i++) {

            System.out.println(jedis.lpop("seq")+"        "+jedis.llen("seq"));
        }

//        System.out.println("修改 google，并获取所有值。");
//        jedis.hset("website", "google", "g.cn");
//        System.out.println(jedis.hgetAll("website"));
//
//        System.out.println("查看是不存在");
//        System.out.println("google exist = " + jedis.hexists("website", "google"));
//
//
//        System.out.println("查看所有的keys");
//        System.out.println(jedis.hkeys("website"));
//        System.out.println("查看所有的values");
//        System.out.println(jedis.hvals("website"));
//
//        System.out.println("hash长度");
//        System.out.println(jedis.hlen("website"));
//
//
//        System.out.println("数值类型increment");
//        jedis.hset("counter", "page_view", "0");
//        jedis.hincrBy("counter", "page_view", 100);
//        System.out.println(jedis.hget("counter", "page_view"));
//        jedis.hincrBy("counter", "page_view", -10);
//        System.out.println(jedis.hget("counter", "page_view"));
//        jedis.hincrByFloat("counter", "page_view", 1.22);
//        System.out.println(jedis.hget("counter", "page_view"));
//
//        System.out.println("");
//        jedis.hsetnx("nosql", "key-value-store", "redis");
//        System.out.println(jedis.hget("nosql","key-value-store"));
//        jedis.disconnect();
    }
}
