package boot.seq;

import foo.JedisAPI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * 序号生成器
 * Created by whydk on 2016/7/6.
 */
public class SeqGenerator {
    public static final Log logger = LogFactory.getLog(SeqGenerator.class);
    public static final long MAX_FETCH_COUNT = 500;
    public static final String KEY = "seq";

    public int step = 10000;
    public int threshold = 200;
    private long maxSeq = 0;
    private long maxFetchCount = MAX_FETCH_COUNT;
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

    public long loadNextPhase() {
        //持久化当前最大的 seq
        long maxseq = maxSeq;
        persistMaxSeq();

        logger.info("loadNextPhase");
        //加载下一阶段seq
        String[] seq = new String[step];
        for (int i = 0; i < step; i++) {
            seq[i] = ++maxseq + "";
        }
        return JedisAPI.rpush(KEY, seq);
    }

    public void persistMaxSeq() {
        maxSeq += step;
        // fixme
    }

    /**
     * @param fetchcount
     * @return 返回单个seq,或多个seq(用“,”分割)
     */
    public String nextSeq(final int fetchcount) {
        checkArgument(fetchcount > 0, "fetchcount must bigger than 0.");
        try {
            String nextseq = null;
            if (fetchcount == 1) {
                nextseq = JedisAPI.lpop(key);
            } else {
                //最多取seq 个数
                final long fc = fetchcount<=maxFetchCount?fetchcount:maxFetchCount;

                ensureCapacityInternal(fc);

                List<Object> rs = JedisAPI.pipeLine(new JedisAPI.PipeLineRequeest() {
                    @Override
                    public List<Object> doRequest(Pipeline pipeline) {
                        pipeline.lrange("seq", 0, fc-1);
                        pipeline.ltrim("seq", fc, -1);
                        return pipeline.syncAndReturnAll();
                    }
                });

                if(rs != null){
                    List<String> list = (List<String>)rs.get(0);
                    StringBuilder seqBuilder = new StringBuilder();
                    for(String seq:list){
                        seqBuilder.append(seq).append(",");
                    }
                    seqBuilder.deleteCharAt(seqBuilder.length()-1);
                    nextseq = seqBuilder.toString();
                }
            }
            if (nextseq != null)
                return nextseq;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    private void ensureCapacityInternal(long fc) {
        boolean hasEnoughSeq = hasEnoughSeq(fc+threshold);
        if(!hasEnoughSeq){
            loadNextPhase();
        }
    }

    public boolean needLoadNextPhase() {
        return hasEnoughSeq(threshold);
    }

    public boolean hasEnoughSeq(long needCount){
        try {
            long length = getCurrentLength();
            if (length < needCount)
                return true;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    public long getCurrentLength(){
        return JedisAPI.llen(key);
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }
}
