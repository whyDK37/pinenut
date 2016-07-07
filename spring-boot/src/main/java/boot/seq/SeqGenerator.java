package boot.seq;

import foo.JedisAPI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;

/**
 * 序号生成器
 * Created by whydk on 2016/7/6.
 */
public class SeqGenerator {
    public static final Log logger = LogFactory.getLog(SeqGenerator.class);

    public static final String KEY = "seq";

    public int step = 10000;
    public int threshold = 200;
    private long maxSeq = 0;
    private String key;

    /**
     * @param key       redis key
     * @param maxSeq    当前最大的序号
     * @param step      seq 增长步长
     * @param threshold 如果key长度达到 threshold，加载下一阶段序号
     */
    public SeqGenerator(String key, long maxSeq, int step, int threshold) {
        this.maxSeq = maxSeq;
        this.key = key;
        this.step = step;
        this.threshold = threshold;
    }

    /**
     * 初始化 序列（清空key）,并加载下一阶段序号
     *
     * @return
     */
    public boolean init() {
        cleanKey();
        this.loadNextPhase();
        return true;
    }

    protected void cleanKey() {

        JedisAPI.ltrim(KEY, 0, 0);
        JedisAPI.lpop(KEY);
    }

    public void loadNextPhase() {
        //持久化当前最大的 seq
        long maxseq = maxSeq;
        persistMaxSeq();

        logger.info("loadNextPhase");
        //加载下一阶段seq
        String[] seq = new String[step];
        for (int i = 0; i < step; i++) {
            seq[i] = ++maxseq + "";
        }
        Long length = JedisAPI.rpush(KEY, seq);

    }

    public void persistMaxSeq() {
        maxSeq += step;
        // fixme
    }

    /**
     * @return
     */
    public Long nextSeq() {
        try {
            long start = System.currentTimeMillis();
            String ns = JedisAPI.lpop(key);
            long end = System.currentTimeMillis();
            System.out.println("next seq : "+(end - start));
            if (ns != null)
                return Long.valueOf(ns);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return -1L;
    }

    public boolean needLoadNextPhase() {
        try {
            long length = JedisAPI.llen(key);
            if (length < threshold)
                return true;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

}
