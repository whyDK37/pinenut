package boot.seq;

import foo.JedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by whydk on 2016/7/6.
 */
public class SeqGeneratorTest {
    public static final Log logger = LogFactory.getLog(SeqController.class);

    public static final int step = 100;
    public static final int threshold = 20;
    private int maxseq = 0;
    public static final String KEY = "seq";
    Jedis jedis;
    SeqGenerator seqGenerator;

    @Test
    public void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("abc");
                }
            }
        }).start();
        String host = "127.0.0.1";
        int port = 6379;
        jedis = JedisUtil.createJedis(host, port);

        seqGenerator = new SeqGenerator(KEY, maxseq, step, threshold);
        seqGenerator.init();

        SeqChecker seqChecker = new SeqChecker(seqGenerator,10);
        seqChecker.check();
    }
}
