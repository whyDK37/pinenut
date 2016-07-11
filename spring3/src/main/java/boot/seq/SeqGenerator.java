package boot.seq;

/**
 * Created by whydk on 2016/7/7.
 */
public interface SeqGenerator {
    boolean init();

    long loadNextPhase();

    void persistMaxSeq();

    String nextSeq(int fetchcount);

    void ensureCapacity(long fc);

    boolean needLoadNextPhase();

    boolean hasEnoughSeq(long needCount);

    long getCurrentLength();

    long getThreshold();

    boolean isReady();

    boolean shutdown();


    void setSeqChecker(SeqChecker seqChecker) ;
}
