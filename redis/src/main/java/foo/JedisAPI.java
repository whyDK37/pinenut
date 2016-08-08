/**
 * Copyright (C) 2013 北京学信科技有限公司
 *
 * @className:com.xuexin.bizserver.cache.JedisAPI
 * @version:v1.0.0 
 * @author:why
 * 
 * Modification History:
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2013-6-25       why                        v1.0.0        create
 *
 */
package foo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

/**
 * @Description : Redis操作接口，简单操作可以通过调用提供的快速接口实现，复杂的操作自己获取jedis实例，
 *              使用过程中出现异常应调用returnResource方法销毁异常的实例
 *              ，使用完成ying调用returnResource方法归还实例，具体使用方法参考快速接口
 * @Creation Date : 2013-6-25 上午10:39:09
 */
public class JedisAPI {
    public static final Log logger = LogFactory.getLog(JedisAPI.class);
    private static JedisPool pool = null;
    /**
     * 从JedisPool中获取Jedis实例
     * 
     * @return Jedis实例
     * @version:v1.0
     * @date:2013-6-25 上午10:37:35
     */
    public static Jedis getResource() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。

            // 设置最大连接数
            config.setMaxTotal(1000);
            // 设置最大阻塞时间，记住是毫秒数milliseconds
            config.setMaxWaitMillis(1000 * 60);
            // 设置空间连接
            config.setMaxIdle(10);
            config.setTestOnBorrow(true);

            // 创建连接池
            pool = new JedisPool(config, "127.0.0.1", 6379);
        }
        return pool.getResource();
    }

    /**
     * 返还连接池
     *
     * @param jedis jedis实例
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-25 上午10:37:50
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
//            pool.returnResource(jedis);
        }
    }

    /**
     * 销毁出现异常的jedis实例
     *
     * @param jedis jedis实例
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-25 上午10:44:31
     */
    public static void brokenResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
//            pool.returnBrokenResource(jedis);
        }
    }

    /**
     * 给指定的key设置过期时间
     *
     * @param key
     * @param seconds
     * @version:v1.0
     * @author:李大鹏
     * @date:2014-2-7 下午5:24:11
     */
    public static void expire(final String key, final int seconds) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.expire(key, seconds);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 设置到期时间
     *
     * @param key
     * @param unixTime
     * @version:v1.0
     * @author:李大鹏
     * @date:2014-8-4 下午6:46:42
     */
    public static void expireAt(final String key, final long unixTime) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.expireAt(key, unixTime);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }

    /**
     * 快速取String类型数据
     *
     * @param key
     * @return
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-25 上午10:38:15
     */
    public static String get(String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static List<String> mget(final String... keys) {
        List<String> resList = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            resList = jedis.mget(keys);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return resList;
    }

    /**
     * 快速存String类型数据
     *
     * @param key
     * @param value
     * @return 操作状态
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-25 上午11:04:54
     */
    public static String set(String key, String value) {
        Jedis jedis = null;
        String statusCode = null;
        try {
            jedis = getResource();
            statusCode = jedis.set(key, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    public static boolean setbit(String key, long offset, boolean value) {
        Jedis jedis = null;
        boolean statusCode = false;
        try {
            jedis = getResource();
            statusCode = jedis.setbit(key, offset, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    public static boolean getbit(String key, long offset) {
        Jedis jedis = null;
        boolean statusCode = false;
        try {
            jedis = getResource();
            statusCode = jedis.getbit(key, offset);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    public static Long incr(String key) {
        Jedis jedis = null;
        Long res = null;
        try {
            jedis = getResource();
            res = jedis.incr(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return res;
    }

    /**
     * 存入hash表
     *
     * @param key   hash表名
     * @param field 字段
     * @param value 值
     * @return 更新返回 0,新增返回 1
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:09:17
     */
    public static long hset(String key, String field, String value) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            jedis = getResource();
            statusCode = jedis.hset(key, field, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * 从hash表取数据
     *
     * @param key   hash表名
     * @param field 字段
     * @return
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:18:33
     */
    public static String hget(String key, String field) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static String hmset(final String key, final Map<String, String> hash) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.hmset(key, hash);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static List<String> hmget(String key, String... fields) {
        List<String> value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.hmget(key, fields);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    /**
     * 获得hash中所有的键值对
     *
     * @param key
     * @return
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-7-4 下午1:20:32
     */
    public static Map<String, String> hgetAll(String key) {
        Map<String, String> map = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            map = jedis.hgetAll(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return map;
    }

    /**
     * 获取hash中所有key
     *
     * @param key
     * @return
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-8-5 下午3:59:59
     */
    public static Set<String> hkeys(String key) {
        Set<String> set = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            set = jedis.hkeys(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return set;
    }

    public static List<String> hvals(String key) {
        List<String> list = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            list = jedis.hvals(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return list;
    }

    /**
     * 从hash表取数据
     *
     * @param key   hash表名
     * @return
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:18:33
     */
    public static Long hdel(String key, String... fields) {
        Long value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.hdel(key, fields);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    public static Long hincrBy(String key, String field, int value) {
        Long res = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            res = jedis.hincrBy(key, field, value);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return res;
    }

    /**
     * @param key
     * @param members
     * @return 添加成功返回0，要添加的对象已存在返回1
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:26:01
     */
    public static long sadd(String key, String... members) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            jedis = getResource();
            statusCode = jedis.sadd(key, members);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * @param key
     * @return
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:32:16
     */
    public static Set<String> smembers(String key) {
        Jedis jedis = null;
        Set<String> members = null;
        try {
            jedis = getResource();
            members = jedis.smembers(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return members;
    }

    /**
     * @param key
     * @param member
     * @return 添加成功返回0，要添加的对象已存在返回1
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:26:01
     */
    public static long zadd(String key, double score, String member) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            jedis = getResource();
            statusCode = jedis.zadd(key, score, member);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    public static long zrem(String key, String... member) {
        Jedis jedis = null;
        long statusCode = -1;
        try {
            jedis = getResource();
            statusCode = jedis.zrem(key, member);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return statusCode;
    }

    /**
     * @param key
     * @return
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:32:16
     */
    public static Set<String> zrevrangeByScore(final String key,
                                               final String max, final String min, final int offset,
                                               final int count) {
        Jedis jedis = null;
        Set<String> members = null;
        try {
            jedis = getResource();
            members = jedis.zrevrangeByScore(key, max, min, offset, count);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return members;
    }

    public static Set<String> zrangeByScore(final String key, final long min,
                                            final long max) {
        Jedis jedis = null;
        Set<String> members = null;
        try {
            jedis = getResource();
            members = jedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return members;
    }

    public static long zremrangeByScore(final String key, final long start,
                                        final long end) {
        Jedis jedis = null;
        long value = 0l;
        try {
            jedis = getResource();
            value = jedis.zremrangeByScore(key, start, end);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
        return value;
    }

    /**
     * 在list头部添加
     *
     * @param key
     * @param members
     * @return 元素添加完成后list的长度
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:43:02
     */
    public static long lpush(String key, String... members) {
        Jedis jedis = null;
        long length = -1;
        try {
            jedis = getResource();
            length = jedis.lpush(key, members);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return length;
    }

    /**
     * 在list尾部取值并删除最后一条
     *
     * @param key
     * @return 元素添加完成后list的长度
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:43:02
     */
    public static String rpop(String key) {
        Jedis jedis = null;
        String str = null;
        try {
            jedis = getResource();
            str = jedis.rpop(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return str;
    }

    /**
     * 在list尾部添加
     *
     * @param key
     * @param members
     * @return 元素添加完成后list的长度
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-26 下午2:43:02
     */
    public static long rpush(String key, String... members) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("rpush key :" + key);
            logger.debug("rpush members :" + members);
        }
        Jedis jedis = null;
        long length = -1;
        try {
            jedis = getResource();
            length = jedis.rpush(key, members);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return length;
    }

    /**
     * 是否存在所给的key值
     *
     * @param key
     * @return 验证结果
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-25 上午11:06:59
     */
    public static boolean exists(String key) {
        boolean value = false;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.exists(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    /**
     * 删除所给Keys,如果所给的keys中有不存在的key，则不会对那些不存在的key执行任何操作
     *
     * @param keys
     * @return 删除key的个数
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-25 上午11:24:36
     */
    public static long delete(String... keys) {
        long value = 0;
        Jedis jedis = null;
        try {
            jedis = getResource();
            value = jedis.del(keys);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return value;
    }

    /**
     * key所对应的存储类型
     *
     * @param key
     * @return"none"/"string"/"list"/"set"/"zset"/"hash"
     * @version:v1.0
     * @author:李大鹏
     * @date:2013-6-25 下午1:14:59
     */
    public static String type(String key) {
        String type = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            type = jedis.type(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }

        return type;
    }


    public static void ltrim(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.ltrim(key, start, end);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            e.printStackTrace();
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }

    public static String lpop(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.lpop(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            throw e;
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }

    public static long llen(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return jedis.llen(key);
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            throw e;
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }


    public static List<Object> pipeLine(PipeLineRequeest pipeLineRequeest) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getResource();
            return pipeLineRequeest.doRequest(jedis.pipelined());
        } catch (Exception e) {
            // 释放redis对象
            brokenResource(jedis);
            throw e;
        } finally {
            // 返还到连接池
            returnResource(jedis);
        }
    }

    public interface PipeLineRequeest {
        List<Object> doRequest(Pipeline pipeline);
    }
}
