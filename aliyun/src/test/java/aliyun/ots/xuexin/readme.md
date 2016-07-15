# 整理
## 帖子索引表
【（用户ID+帖子ID+时间+班级ID） 帖子TYPE  】

教育圈       【 用户ID  时间  帖子ID 】 班级ID 帖子类型
班级群/主题群 【 用户ID  班级ID+帖子类型 时间 帖子ID 】

场景：校园查询所有帖子，查询条件：用户ID，时间
班级群查看班级相关帖子  查询条件：用户id，时间，班级ID

## 帖子赞表
【（帖子ID+赞ID+时间） 用户ID  帖子TYPE 班级ID】

【 帖子ID 赞ID 时间 班级ID 】用户ID

场景：在校园或班级群或主题群查看帖子赞。
查询条件：帖子ID。按赞时间排序，翻页时查询条件是最后一条帖子ID、赞ID、帖子时间。
## 帖子评论表
【（帖子ID+评论ID+时间） 用户ID 帖子TYPE  班级ID】被评论人ID

【 帖子ID 评论ID 时间 班级ID】用户ID 被评论人ID 评论内容

 场景：在校园或班级群或主题群查看帖子赞。
 查询条件：帖子ID，翻页时查询最后一条帖子ID、评论ID、时间
## 帖子分享表
【（帖子ID+分享ID+时间） 用户ID 帖子TYPE  班级ID】

【 帖子ID 分享ID 时间 班级ID】 用户ID

场景：在校园或班级群或主题群查看帖子赞。
查询条件：与评论相同

# 字段与数据类型的最长位数，拼接主键需要考虑预留位数
用户ID long  9位   如：999999999 9亿
时间   long 14位  如：99999999999999 - 5138-11-16
       long 13位                       2286-11-21
帖子ID long 9位   如：999999999 9亿
班级ID long 9位   如：999999999 9亿
评论ID long 15位
分享ID long 15位
赞ID   long 15位
帖子TYPE string 1位

为什么班级ID为分片键？为什么帖子类型，班级ID
sns_group中的createDate字段长度为15，前面补了两个0

# 新版本索引设计
_注释：【】中是主键，没在【】中的是属性。_

## 字段说明
用户ID username long
时间   createDate long
                long
帖子ID topicID  long    全局唯一
班级ID classid  long
评论ID replyID  long    全局唯一
分享ID shareid  long    全局唯一
赞ID   likeID   long    全局唯一
帖子类型 topictype string  2位
班级ID+帖子类型  classidtopictype string  中间用,号分割如：9000001,01

分享平台：shareplatform  string 、学信好友，2、学信群，3、微信，4、朋友圈，5、新浪微博，6、qq空间，7、
状态     status         string  长度1
回复评论ID replyPID     long
评论内容   content      string
评论人ID   replyuserid  long
## 教育圈索引
【用户ID ，帖子ID】时间，
## 班级群/主题群索引
【用户ID ，班级ID+帖子类型， 帖子ID】时间，

## 帖子赞表
【帖子ID，赞全局ID 】时间 ，用户ID ，状态

接口：
topicID likeID creatorName creator createDate

## 帖子评论表
【帖子ID，评论全局ID】时间 ，用户ID ，回复评论ID, 评论人ID， 评论内容 ， 状态

接口 "replyID": 2418133476952064,
                        "replyPID": null,
                        "topicID": 2417793839253504,
                        "replyContent": "哈哈测试来了",
                        "creator": "101001",
                        "creatorName": "郜金丹1oaisAASFDAQDF",
                        "createDate": 1436426129398,
                        "pEduReply": null
## 帖子分享表
【帖子ID，分享全局ID】时间 ，用户ID，分享平台 状态

## seqGenerator
新版本增加seqGenerator，用来生成全局增量ID。
全局ID可分多个分类：教育圈群，赞，评论，分享。
生成ID的规则是新生成的ID一定**比前一个大**，但是可以不连续。
如果你现在的ID是3，下一个可能是是4，也可能是15，也可能是501。