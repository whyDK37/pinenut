package redis;

import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Random;  
  
import org.junit.Test;  
  
import redis.clients.jedis.Jedis;  
  
/** 
 * 一次string-hash优化 
 * 原文链接：http://blog.csdn.net/liqfyiyi/article/details/50894013
 * @author carlosfu 
 * @Date 2015-11-8 
 * @Time 下午7:27:45 
 */  
public class TestRedisMemoryOptimize {  
  
    private final static int TOTAL_USER_COUNT = 1000000;  
  
    private final static String HOST = "127.0.0.1";  
    private final static String PASSWORD = "xue$Xin+2@!3";

    private final static int PORT = 6379;  
  
    /** 
     * 纯字符串 
     */  
    @Test  
    public void testString() {  
        int mBatchSize = 2000;  
        Jedis jedis = null;  
        try {  
            jedis = new Jedis(HOST, PORT);
            jedis.auth(PASSWORD);
            List<String> kvsList = new ArrayList<String>(mBatchSize);
            for (int i = 1; i <= TOTAL_USER_COUNT; i++) {  
                String key = "u:" + i;  
                kvsList.add(key);  
                String value = "v:" + i;  
                kvsList.add(value);  
                if (i % mBatchSize == 0) {  
                    System.out.println(i);  
                    jedis.mset(kvsList.toArray(new String[kvsList.size()]));  
                    kvsList = new ArrayList<String>(mBatchSize);  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (jedis != null) {  
                jedis.close();  
            }  
        }  
    }  
  
    /** 
     * 纯hash 
     */  
    @Test  
    public void testHash() {  
        int mBatchSize = 2000;  
        String hashKey = "allUser";  
        Jedis jedis = null;  
        try {  
            jedis = new Jedis(HOST, PORT);
            jedis.auth(PASSWORD);
            Map<String, String> kvMap = new HashMap<String, String>();
            for (int i = 1; i <= TOTAL_USER_COUNT; i++) {  
                String key = "u:" + i;  
                String value = "v:" + i;  
                kvMap.put(key, value);  
                if (i % mBatchSize == 0) {  
                    System.out.println(i);  
                    jedis.hmset(hashKey, kvMap);  
                    kvMap = new HashMap<String, String>();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (jedis != null) {  
                jedis.close();  
            }  
        }  
    }  
  
    /** 
     * segment hash 
     */  
    @Test  
    public void testSegmentHash() {  
        int segment = 100;  
        Jedis jedis = null;  
        try {  
            jedis = new Jedis(HOST, PORT);
            jedis.auth(PASSWORD);
            Map<String, String> kvMap = new HashMap<String, String>();
            for (int i = 1; i <= TOTAL_USER_COUNT; i++) {  
                String key = "f:" + String.valueOf(i % segment);  
                String value = "v:" + i;  
                kvMap.put(key, value);  
                if (i % segment == 0) {  
                    System.out.println(i);  
                    int hash = (i - 1) / segment;  
                    jedis.hmset("u:" + String.valueOf(hash), kvMap);  
                    kvMap = new HashMap<String, String>();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (jedis != null) {  
                jedis.close();  
            }  
        }  
    }  
  
}