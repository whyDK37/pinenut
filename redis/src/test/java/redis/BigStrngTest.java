package redis;

import foo.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.pojo.BigObject;
import redis.util.ObjectsTranscoder;

/**
 * Created by whydk on 2016/7/21.
 */
public class BigStrngTest {
    public static void main(String[] args) {
        {
            String host = "127.0.0.1";
            int port = 6379;

            Jedis jedis = JedisUtil.createJedis(host,port);

            for (int i = 0; i < 10000; i++) {
                jedis.set("obj"+i,"i am a big String for "+i);
            }

            long t1 = System.currentTimeMillis();
            String string = jedis.get("obj0");
            long t2 = System.currentTimeMillis();
            System.out.println(t2-t1);
            System.out.println(string);

            jedis.disconnect();
        }
    }
}
