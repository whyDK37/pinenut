package mc.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by whydk on 2016/7/21.
 */
public class BigObject implements Serializable {

    private static final long serialVersionUID = 5936849971790901953L;
    private long topicID;//帖子ID
    private int scope;//1-教育圈，2-班级群，3-主题群
    private String topicTitle;//帖子标题
    private String topicContent;//帖子内容
    private String topicURL;//帖子内容链接
    private int topicType;//帖子类型
    private String images;//帖子图片
    private String topicLabels;//贴子标签列表
    private long stickBarID;//贴吧板块ID
    private String creator;//发帖人学信号
    private String creatorName;//发帖人姓名
    private String creatorAvatar;//发帖人头像
    private long createDate;//发帖时间
    private Integer countCollect;//收藏数
    private Integer countShare;//分享数
    private Integer countReply;//评论数
    private Integer countLike;//赞的数量
    private int isLike;//是否赞过0-无；1-赞过
    private String platform;//发帖人设备
    private String version;//发帖使用版本号
    private int allowReply;//是否允许评论,1允许,0不允许
    private int isEffective;//是否有效,1有效,0无效
    private int isPraise;//是否赞过0-无；1-赞过
    private int isShare;//是否分享过0-无；1-赞过
    private int countPraise;//赞的数量
    private int countRead;//阅读的数量
    private String audioApp;//APP文件路径
    private String audioWeb;//WEB文件路径
    private String audioUrl;//语音文件路径
    private int audioPlayTime;//播放时长，单位秒
    private int audioSize;//文件大小，单位KB
    private int topicStyle;//1-原帖子；2-分享；3-广告
    private String province;//省编码
    private String city;//市编码
    private String subjectIds;//学段范围,以英文逗号分隔
    private String roleIds;//用户范围,以英文逗号分隔
    private String provinceId;//省编码
    private String cityId;//市编码
    private Integer classID;//班级ID

    private List<EduPraise> eduLikeList;//教育圈点赞列表
    private List<EduPraise> eduPraiseList;//教育圈点赞列表
    private List<EduShare> eduShareList;//教育圈分享列表
    private List<EduReply> eduReplyList;//教育圈评论列表

    private List<String> imageList;//帖子图片列表


    //附加属性，用来做判断是否已经加载属性信息,默认为true
    private boolean loaded = true;

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public String getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(String subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public List<EduPraise> getEduLikeList() {
        return eduLikeList;
    }

    public void setEduLikeList(List<EduPraise> eduLikeList) {
        this.eduLikeList = eduLikeList;
    }

    public long getTopicID() {
        return topicID;
    }

    public void setTopicID(long topicID) {
        this.topicID = topicID;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getTopicURL() {
        return topicURL;
    }

    public void setTopicURL(String topicURL) {
        this.topicURL = topicURL;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTopicLabels() {
        return topicLabels;
    }

    public void setTopicLabels(String topicLabels) {
        this.topicLabels = topicLabels;
    }

    public long getStickBarID() {
        return stickBarID;
    }

    public void setStickBarID(long stickBarID) {
        this.stickBarID = stickBarID;
    }

    public int getTopicType() {
        return topicType;
    }

    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorAvatar() {
        return creatorAvatar;
    }

    public void setCreatorAvatar(String creatorAvatar) {
        this.creatorAvatar = creatorAvatar;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public Integer getCountCollect() {
        return countCollect;
    }

    public void setCountCollect(Integer countCollect) {
        this.countCollect = countCollect;
    }

    public Integer getCountShare() {
        return countShare;
    }

    public void setCountShare(Integer countShare) {
        this.countShare = countShare;
    }

    public Integer getCountReply() {
        return countReply;
    }

    public void setCountReply(Integer countReply) {
        this.countReply = countReply;
    }

    public Integer getCountLike() {
        return countLike;
    }

    public void setCountLike(Integer countLike) {
        this.countLike = countLike;
    }

    public List<EduShare> getEduShareList() {
        return eduShareList;
    }

    public void setEduShareList(List<EduShare> eduShareList) {
        this.eduShareList = eduShareList;
    }

    public List<EduReply> getEduReplyList() {
        return eduReplyList;
    }

    public void setEduReplyList(List<EduReply> eduReplyList) {
        this.eduReplyList = eduReplyList;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getAllowReply() {
        return allowReply;
    }

    public void setAllowReply(int allowReply) {
        this.allowReply = allowReply;
    }

    public int getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(int isEffective) {
        this.isEffective = isEffective;
    }


    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public int getIsShare() {
        return isShare;
    }

    public void setIsShare(int isShare) {
        this.isShare = isShare;
    }

    public int getCountPraise() {
        return countPraise;
    }

    public void setCountPraise(int countPraise) {
        this.countPraise = countPraise;
    }

    public int getCountRead() {
        return countRead;
    }

    public void setCountRead(int countRead) {
        this.countRead = countRead;
    }

    public String getAudioApp() {
        return audioApp;
    }

    public void setAudioApp(String audioApp) {
        this.audioApp = audioApp;
    }

    public String getAudioWeb() {
        return audioWeb;
    }

    public void setAudioWeb(String audioWeb) {
        this.audioWeb = audioWeb;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public int getAudioPlayTime() {
        return audioPlayTime;
    }

    public void setAudioPlayTime(int audioPlayTime) {
        this.audioPlayTime = audioPlayTime;
    }

    public int getAudioSize() {
        return audioSize;
    }

    public void setAudioSize(int audioSize) {
        this.audioSize = audioSize;
    }

    public int getTopicStyle() {
        return topicStyle;
    }

    public void setTopicStyle(int topicStyle) {
        this.topicStyle = topicStyle;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<EduPraise> getEduPraiseList() {
        return eduPraiseList;
    }

    public void setEduPraiseList(List<EduPraise> eduPraiseList) {
        this.eduPraiseList = eduPraiseList;
    }


    public static class EduReply implements Serializable {
        private long replyID;//": 2418133476952064,
        private long replyPID;//": null,
        private long topicID;//": 2417793839253504,
        private String replyContent;//": "哈哈测试来了",
        private long creator;//": "101001",
        private String creatorName;//": "郜金丹1oaisAASFDAQDF",
        private long createDate;//": 1436426129398,
        private long pEduReply;//": null

        public long getReplyID() {
            return replyID;
        }

        public long getReplyPID() {
            return replyPID;
        }

        public long getTopicID() {
            return topicID;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public long getCreator() {
            return creator;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public long getCreateDate() {
            return createDate;
        }

        public long getpEduReply() {
            return pEduReply;
        }

        public void setReplyID(long replyID) {
            this.replyID = replyID;
        }

        public void setReplyPID(long replyPID) {
            this.replyPID = replyPID;
        }

        public void setTopicID(long topicID) {
            this.topicID = topicID;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }

        public void setCreator(long creator) {
            this.creator = creator;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public void setpEduReply(long pEduReply) {
            this.pEduReply = pEduReply;
        }
    }

    public static class EduPraise implements Serializable {
        private long likeID;//": 2417840625453056,
        private long topicID;// 2417793839253504,
        private long creator;// "241801",
        private String creatorName;//: "123",
        private long createDate;//: 1436408255222

        public long getCreator() {
            return creator;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreator(long creator) {
            this.creator = creator;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public void setLikeID(long likeID) {
            this.likeID = likeID;
        }

        public void setTopicID(long topicID) {
            this.topicID = topicID;
        }

        public long getLikeID() {
            return likeID;
        }

        public long getTopicID() {
            return topicID;
        }
    }

    public static class EduShare implements Serializable {
        private long shareID;//": 2415000534714368,
        private long topicID;//": 2405548923635712,
        private String sharePlatform;//": 2,
        private long creator;//": "101101",
        private String creatorName;//": "钱晓林",

        public long getShareID() {
            return shareID;
        }

        public long getTopicID() {
            return topicID;
        }

        public String getSharePlatform() {
            return sharePlatform;
        }

        public long getCreator() {
            return creator;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public void setCreator(long creator) {
            this.creator = creator;
        }

        public void setSharePlatform(String sharePlatform) {
            this.sharePlatform = sharePlatform;
        }

        public void setTopicID(long topicID) {
            this.topicID = topicID;
        }

        public void setShareID(long shareID) {
            this.shareID = shareID;
        }

        private long createDate;//": 1436234909792
    }
}
