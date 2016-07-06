package demo;

import foo.JedisUtil;
import redis.clients.jedis.Jedis;

/**
 * redis hash
 * Created by drug on 2016/4/27.
 */
public class ListTest2 {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 6379;

        Jedis jedis = JedisUtil.createJedis(host,port);
        long start = System.nanoTime();
        int count = 100;
        String[] seq = new String[count];
        for (int i = 0; i < count; i++) {
            seq[i] = i+"";
//            jedis.rpush("seq",i+"");
        }
        Long length = jedis.rpush("seq",seq);
        long end = System.nanoTime();
        System.out.println(length);
        System.out.println(end-start);
        // 清空 seq
        jedis.ltrim("seq",0,0);
        jedis.lpop("seq");
    }
}
