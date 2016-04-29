package demo;

import foo.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * Created by drug on 2016/4/27.
 */
public class Test {
    public static void main(String[] args) {
        String host = "192.168.1.102";
        Jedis jedis = JedisUtil.createJedis(host);
        jedis.del("abc");
        jedis.append("abc","  abc");
        System.out.println(jedis.get("abc"));

    }
}
