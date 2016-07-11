package aliyun.ots.xuexin.pojo;

/**
 * Created by whydk on 2016/7/11.
 */
public class TopicIndex {
    private Long userid;
    private Long topicid;
    private Long time;
    private Long topictype;
    private Long classid;

    public Long getUserid() {
        return userid;
    }

    public Long getTopicid() {
        return topicid;
    }

    public Long getTime() {
        return time;
    }

    public Long getTopictype() {
        return topictype;
    }

    public Long getClassid() {
        return classid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setTopicid(Long topicid) {
        this.topicid = topicid;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setTopictype(Long topictype) {
        this.topictype = topictype;
    }

    public void setClassid(Long classid) {
        this.classid = classid;
    }

    @Override
    public String toString() {
        return "TopicIndex{" +
                "userid=" + userid +
                ", topicid=" + topicid +
                ", time=" + time +
                ", topictype=" + topictype +
                ", classid=" + classid +
                '}';
    }
}
