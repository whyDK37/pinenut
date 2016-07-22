package redis.util.demo;

import foo.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

import java.util.List;

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
        jedis.getClient();
        long end = System.nanoTime();
        Pipeline pipeline = jedis.pipelined();
        pipeline.lrange("seq",0,19);
        pipeline.ltrim("seq",20,-1);

        List<Object> rs = pipeline.syncAndReturnAll();
        List<String> list = (List<String>)rs.get(0);
        StringBuilder seqBuilder = new StringBuilder();
        for(String s:list){
            seqBuilder.append(s).append(",");
        }
        seqBuilder.deleteCharAt(seqBuilder.length()-1);
        System.out.println(seqBuilder.toString());

        // 清空 seq
//        jedis.ltrim("seq",0,0);
//        jedis.lpop("seq");
    }
}
