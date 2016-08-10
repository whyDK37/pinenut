package redis;

import redis.util.JedisUtil;
import redis.pojo.BigObject;
import redis.clients.jedis.Jedis;
import redis.util.ObjectsTranscoder;

/**
 * Created by whydk on 2016/7/21.
 */
public class BigObjectTest {
    public static void main(String[] args) {
        {
            String host = "172.16.170.128";
            int port = 6379;

            Jedis jedis = JedisUtil.createJedis(host,port);

            ObjectsTranscoder<BigObject> objTranscoder = new ObjectsTranscoder<BigObject>();
            for (int i = 0; i < 10000; i++) {
                BigObject bo = new BigObject();
                byte[] result1 = objTranscoder.serialize(bo);
                jedis.set(("obj"+i).getBytes(),result1);
            }

            long t1 = System.currentTimeMillis();
            byte[]  b = jedis.get("obj0".getBytes());
            BigObject bigObject = objTranscoder.deserialize(b);
            long t2 = System.currentTimeMillis();
            System.out.println(t2-t1);
            System.out.println(bigObject);

            jedis.disconnect();
        }
    }
}
