package boot.seq;

/**
 * Created by whydk on 2016/7/11.
 */
public interface SeqGeneratorManager {

    SeqGenerator getSeqGenerator(String key);
}
