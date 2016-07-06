package boot.pojo;

public class Seq {

    private final long rc;
    private final long seq;

    public Seq(long rc, long content) {
        this.rc = rc;
        this.seq = content;
    }

    public long getRc() {
        return rc;
    }

    public long getSeq() {
        return seq;
    }
}