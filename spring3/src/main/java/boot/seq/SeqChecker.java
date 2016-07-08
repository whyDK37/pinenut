package boot.seq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by whydk on 2016/7/6.
 */
public class SeqChecker {
    public static final Log logger = LogFactory.getLog(SeqChecker.class);
    public static final int DEFAULT_CHECKTIME = 500;


    private SeqGenerator seqGenerator;
    private int checktime = 500;
    // fixme public to private
    public Thread checkerThread;
    private boolean start = false;

    /**
     * @param seqGenerator
     */
    public SeqChecker(SeqGenerator seqGenerator) {
        this(seqGenerator, DEFAULT_CHECKTIME);
    }

    public SeqChecker(SeqGenerator seqGenerator, int checktime) {
        this.seqGenerator = seqGenerator;
        this.checktime = checktime;
        init();
    }

    public void init() {
        checkerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    logger.info("do check");
                    try {
                        TimeUnit.MILLISECONDS.sleep(checktime);
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage(), e);
                    }
                    seqGenerator.ensureCapacity(seqGenerator.getThreshold());
                } while (start);
            }
        });

    }

    public void check() {
        start = true;
        checkerThread.start();
    }


    public boolean stop() {
        // fixme
        return false;
    }
}
