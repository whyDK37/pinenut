package boot.pojo;

public class Seq {

    private long rc;
    private long seq;
    private String msg;
    public Seq(long rc, long seq) {
        this.rc = rc;
        this.seq = seq;
    }
    public Seq(long rc, String msg) {
        this.rc = rc;
        this.msg = msg;
    }

    public long getRc() {
        return rc;
    }

    public long getSeq() {
        return seq;
    }
}