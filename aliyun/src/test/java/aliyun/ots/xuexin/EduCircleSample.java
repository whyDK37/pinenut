package aliyun.ots.xuexin;


import aliyun.ots.OTSTable;
import aliyun.ots.OTSUtil;
import aliyun.ots.StringUtils;
import aliyun.ots.xuexin.pojo.TopicIndex;
import com.aliyun.openservices.ots.*;
import com.aliyun.openservices.ots.model.*;
import com.aliyun.openservices.ots.utils.Preconditions;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static aliyun.ots.OTSUtil.getClient;

/**
 * 【用户ID ，全局ID】时间，帖子类型,班级ID，
 */
public class EduCircleSample {

    private static final String tableName = "topic_index";
    private static final int putrows = 20;
    private static final int limit = 5;
    private static final boolean VERBOSE = true;

    static String[] pkname = {"userid", "topicid"};
    static PrimaryKeyType[] pktype = {PrimaryKeyType.INTEGER, PrimaryKeyType.INTEGER};

    static String[] colname = {"time", "topictype", "classid"};
    static PrimaryKeyType[] coltype = {PrimaryKeyType.INTEGER, PrimaryKeyType.INTEGER, PrimaryKeyType.INTEGER};

    public static final String Separator = ",";

    final static AtomicInteger seq = new AtomicInteger(1);

    static final List<String> columnget = new ArrayList<String>();

    public static void main(String args[]) {
        OTSClient client = getClient();
        OTSTable table = new OTSTable(tableName);

        for (int i = 0; i < pkname.length; i++) {
            columnget.add(pkname[i]);
        }
        for (int i = 0; i < colname.length; i++) {
            columnget.add(colname[i]);
        }
        try {
            table.addPrimaryKey(pkname, pktype);
            table.setCapacity(100, 200);
            OTSUtil.createTable(client, table);

            // 注意：创建表只是提交请求，OTS创建表需要一段时间。
            // 这里简单地等待一下，请根据您的实际逻辑修改。
            Thread.sleep(1000);
            OTSUtil.descTable(client, table.getTableName());

            // 插入多行数据。
            long time = System.currentTimeMillis();
            putRows(client, tableName, 9000001, 1001, time, 1);
            putRows(client, tableName, 9000002, 1002, time, 2);

            educircle(client, time);
//            clazz(client,time);
//            getRangeBACKWARD(client, tableName, BERBOSE,userid,topicid,time,classid);
        } catch (ServiceException e) {
            System.err.println("操作失败，详情：" + e.getErrorCode() + " - " + e.getMessage());
            e.printStackTrace();
            // 可以根据错误代码做出处理， OTS的ErrorCode定义在OTSErrorCode中。
            if (OTSErrorCode.QUOTA_EXHAUSTED.equals(e.getErrorCode())) {
                System.err.println("超出存储配额。");
            }
            // Request ID可以用于有问题时联系客服诊断异常。
            System.err.println("Request ID:" + e.getRequestId());
        } catch (ClientException e) {
            // 可能是网络不好或者是返回结果有问题
            System.err.println("请求失败，详情：" + e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            // 不留垃圾。
            try {
                OTSUtil.deleteTable(client, table);
            } catch (ServiceException e) {
                System.err.println("删除表格失败，原因：" + e.getMessage());
                e.printStackTrace();
            } catch (ClientException e) {
                System.err.println("删除表格请求失败，原因：" + e.getMessage());
                e.printStackTrace();
            }
            client.shutdown();
        }
    }

    private static void educircle(OTSClient client, long ptime) throws InstantiationException, IllegalAccessException {
        long time = ptime;
        long userid = 9000001, topicid = 0, classid = 0, topictype = 0;
        Direction direction = Direction.BACKWARD;
        System.out.println("查询用户 time 时间之 前 的 所有帖子");
        userid = 9000001;
        topicid = 1;
        classid = 0;
        time = ptime + putrows;
        List<TopicIndex> list = getRange(TopicIndex.class, client, tableName, userid, topicid, time, classid, topictype, direction);
        System.out.println("查询所有帖子");
        userid = 9000002L;
        topicid = putrows + 5L;
        classid = 0;
        time = 0;
        getRange(TopicIndex.class,client, tableName, userid, topicid, time, classid, topictype, direction);

        System.out.println("查询帖子id之后的帖子");
        userid = 9000002L;
        topicid = putrows + 5L;
        classid = 0;
        time = ptime + topicid;
        getRange(TopicIndex.class,client, tableName, userid, topicid, time, classid, topictype, direction);

        System.out.println("不存在的用户查询结果为0");
        userid = 9000000L;
        topicid = putrows + 3L;
        classid = 0;
        time = 0;
        getRange(TopicIndex.class,client, tableName, userid, topicid, time, classid, topictype, direction);

//        System.out.println("不存在的班级查询结果0");
//        userid = 9000002L;
//        topicid = 2L;
//        classid = 1001;
//        time = 0;
//        topictype = 3;
//        getRange(client, tableName, BERBOSE, userid, topicid, time, classid, topictype);

    }

    private static void putRows(OTSClient client, String tableName, int userid, int classid, long time, int topictype)
            throws OTSException, ClientException {
        int bid = 1;
        final int rowCount = putrows;
        List<RowPutChange> rows = new ArrayList<RowPutChange>(rowCount);
        for (int i = 0; i < rowCount; ++i) {
            RowPutChange rowChange = new RowPutChange(tableName);
            RowPrimaryKey primaryKey = new RowPrimaryKey();

            int pi = 0, ci = 0;
            primaryKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
            primaryKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(seq.getAndAdd(1)));
            rowChange.setPrimaryKey(primaryKey);

            rowChange.addAttributeColumn(colname[ci++], ColumnValue.fromLong(System.currentTimeMillis()));
            rowChange.addAttributeColumn(colname[ci++], ColumnValue.fromLong(topictype));
            rowChange.addAttributeColumn(colname[ci++], ColumnValue.fromLong(classid));
            rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_NOT_EXIST));
            rows.add(rowChange);
        }

        BatchWriteRowResult batchWriteRowResult = OTSUtil.batchRowRequest(client, rows);

        System.out.println(batchWriteRowResult);
    }

    private static <T> List<T> getRange(Class<T> clazz, OTSClient client, String tableName, long userid, long topicid,
                                        long time, long classid, long topictype, Direction direction)
            throws OTSException, ClientException, IllegalAccessException, InstantiationException {
        Preconditions.checkArgument(clazz != null, " Class must not be null.");
        System.out.println("getRange---------------------------------------------------------------------------------------");
        System.out.println("userid：" + userid + "- classid: " + classid + "- topicid: " + topicid + "- time: " + time + "- topictype: " + topictype);
        System.out.println("limit:" + limit);
//        System.out.println(T);
        // 演示一下如何按主键范围查找，这里查找uid从1-4（左开右闭）的数据
        int pi = 0, ci = 0;
        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();

        if (direction == Direction.FORWARD) {
            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], topicid == -1 ? PrimaryKeyValue.INF_MIN : PrimaryKeyValue.fromLong(topicid));
            pi = 0;
            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.INF_MAX);
        } else {
            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], topicid == -1 ? PrimaryKeyValue.INF_MAX : PrimaryKeyValue.fromLong(topicid));
            pi = 0;
            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.INF_MIN);
        }

        System.out.println(inclusiveStartKey.toString());
        System.out.println(exclusiveEndKey.toString());
        System.out.println();
        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小


        List<T> rows = OTSUtil.readLimitRows(client,clazz, tableName, inclusiveStartKey, exclusiveEndKey, limit, direction, columnget);
        if (VERBOSE)
            for (T row : rows) {
//                System.out.println("topictype 信息为：" + row.getColumns().get(pkname[0]));
                System.out.println("---");
            }

        System.out.println("本次查询数据条数：" + rows.size());
        System.out.println(rows);
        System.out.println("getRange---------------------------------------------------------------------------------------end");

        return rows;
    }

    private static Object getColumnValue(Map.Entry<String, ColumnValue> entry) {
        Object value = null;
        ColumnValue cv = entry.getValue();
        ColumnType ct = cv.getType();
        if (ct == ColumnType.STRING) {
            value = cv.asString();
        } else if (ct == ColumnType.INTEGER) {
            value = cv.asLong();
        } else if (ct == ColumnType.BOOLEAN) {
            value = cv.asBoolean();
        } else if (ct == ColumnType.BINARY) {
            value = cv.asBinary();
        } else if (ct == ColumnType.DOUBLE) {
            value = cv.asDouble();
        }
        return value;
    }
}
