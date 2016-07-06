package boot.seq;

import boot.pojo.Seq;
import foo.JedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * test : curl -X POST http://localhost:8080/seq
 */
@RestController
public class SeqController {

    public static final int count=10000;
    public static final String KEY = "seq";
    Jedis jedis;
    public SeqController(){
        String host = "127.0.0.1";
        int port = 6379;
        jedis = JedisUtil.createJedis(host,port);

        String[] seq = new String[count];
        for (int i = 0; i < count; i++) {
            seq[i] = i+"";
        }
        Long length = jedis.rpush(KEY,seq);
        System.out.println(length);
    }
    @RequestMapping("/seq")
    public Seq greeting(String sys, String type) {
        return new Seq(0,Long.valueOf(jedis.lpop(KEY)));
    }
}