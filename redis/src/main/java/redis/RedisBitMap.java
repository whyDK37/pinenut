package redis;

import redis.JedisAPI;

import java.util.BitSet;

/**
 * Created by whydk on 2016/8/8.
 */
public class RedisBitMap {
    public static int uniqueCount(String action, String date) {
        String key = action + ":" + date;
        BitSet users = BitSet.valueOf(JedisAPI.get(key.getBytes()));
        return users.cardinality();
    }

    public static boolean setCount(String action, String date,long offset,boolean b) {
        String key = action + ":" + date;
        return JedisAPI.setbit(key,offset,b);
    }

    public static int uniqueCount(String action, String... dates) {
        BitSet all = new BitSet();
        for (String date : dates) {
            String key = action + ":" + date;
            BitSet users = BitSet.valueOf(JedisAPI.get(key.getBytes()));
            all.or(users);
        }
        return all.cardinality();
    }
}
