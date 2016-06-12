package demo;

import foo.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * Created by drug on 2016/4/27.
 */
public class StringTest {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6379;

        Jedis jedis = JedisUtil.createJedis(host,port);
        jedis.del("abc");
        jedis.append("abc", "  abc");
        System.out.println(jedis.get("abc"));

        jedis.disconnect();

    }
}
