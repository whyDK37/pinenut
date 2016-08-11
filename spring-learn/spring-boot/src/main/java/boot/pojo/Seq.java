package boot.pojo;

public class Seq {

    private long rc;
    private String seq;
    private String msg;
    public Seq(long rc) {
        this.rc = rc;
    }

    public long getRc() {
        return rc;
    }

    public String getSeq() {
        return seq;
    }

    public String getMsg() {
        return msg;
    }

    public Seq setSeq(String seq) {
        this.seq = seq;
        return this;
    }

    public Seq setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}