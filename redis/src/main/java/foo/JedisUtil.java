package foo;

import redis.clients.jedis.Jedis;

public class JedisUtil {

    public static Jedis createJedis(String host) {
        Jedis jedis = new Jedis(host);
        return jedis;
    }

    public static Jedis createJedis(String host, int port) {
        Jedis jedis = new Jedis(host, port);

        return jedis;
    }

    public static Jedis createJedis(String host, int port, String passwrod) {
        Jedis jedis = new Jedis(host, port);

//        if (!StringUtils.isNotBlank(passwrod))
            jedis.auth(passwrod);
        
        return jedis;
    }
}