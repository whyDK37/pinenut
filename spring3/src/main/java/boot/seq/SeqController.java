package boot.seq;

import boot.pojo.Seq;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * test : curl -X POST http://localhost:8080/seq
 */
@RestController
public class SeqController {
    public static final Log logger = LogFactory.getLog(SeqController.class);

    public static final int step = 10000;
    public static final int threshold = 1000;
    private int maxseq = 0;
    public static final String KEY = "seq";
    SeqGenerator seqGenerator;

    public SeqController() {

        seqGenerator = new SeqRedisGenerator(KEY, maxseq, step, threshold);
        seqGenerator.init();

        SeqChecker seqChecker = new SeqChecker(seqGenerator);
        seqChecker.check();

        seqGenerator.setSeqChecker(seqChecker);
    }

    @RequestMapping("/seq")
    public Seq greeting(String sys,
                        String type,
                        @RequestParam(value = "fc", required = false, defaultValue = "1") int fc) {
        String seq = seqGenerator.nextSeq(fc);
        if (seq == null) {
            logger.warn("未能获取序号，请重新申请");
            return new Seq(1).setMsg("未能获取序号，请重新申请");
        }
        return new Seq(0).setSeq(seq);
    }
}