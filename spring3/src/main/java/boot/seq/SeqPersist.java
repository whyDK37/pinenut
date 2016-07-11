package boot.seq;

/**
 * Created by whydk on 2016/7/7.
 */
public interface SeqPersist {

    boolean persist(String key, int value);
}
