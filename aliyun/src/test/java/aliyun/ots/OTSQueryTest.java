//package aliyun.ots;
//
//
//import aliyun.ots.OTSQuery;
//import aliyun.ots.OTSTable;
//import aliyun.ots.OTSUtil;
//import aliyun.ots.xuexin.pojo.TopicIndex;
//import com.aliyun.openservices.ots.*;
//import com.aliyun.openservices.ots.model.*;
//import com.aliyun.openservices.ots.model.condition.RelationalCondition;
//import com.aliyun.openservices.ots.utils.Preconditions;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static aliyun.ots.OTSUtil.getClient;
//
///**
// * 【用户ID ，全局ID】时间，帖子类型,班级ID，
// */
//public class OTSQueryTest {
//
//    private static final String tableName = "topic_index";
//    private static final int putrows = 20;
//    private static final int limit = 5;
//    private static final boolean VERBOSE = true;
//
//    static String[] pkname = {"userid", "topicid"};
//    static PrimaryKeyType[] pktype = {PrimaryKeyType.INTEGER, PrimaryKeyType.INTEGER};
//
//    static String[] colname = {"time", "topictype", "classid"};
//    static PrimaryKeyType[] coltype = {PrimaryKeyType.INTEGER, PrimaryKeyType.INTEGER, PrimaryKeyType.INTEGER};
//
//    public static final String Separator = ",";
//
//    final static AtomicInteger seq = new AtomicInteger(1);
//
//    static final List<String> columnget = new ArrayList<String>();
//
//    public static void main(String args[]) {
//        OTSClient client = getClient();
//        OTSTable table = new OTSTable(tableName);
//
//        for (int i = 0; i < pkname.length; i++) {
//            columnget.add(pkname[i]);
//        }
//        for (int i = 0; i < colname.length; i++) {
//            columnget.add(colname[i]);
//        }
//        try {
//            table.addPrimaryKey(pkname, pktype);
//            table.setCapacity(100, 200);
//            OTSUtil.createTable(client, table);
//
//            // 注意：创建表只是提交请求，OTS创建表需要一段时间。
//            // 这里简单地等待一下，请根据您的实际逻辑修改。
//            Thread.sleep(1000);
//            OTSUtil.descTable(client, table.getTableName());
//
//            // 插入多行数据。
//            long time = System.currentTimeMillis();
//            putRows(client, tableName, 9000001, 1001, time, 1);
//            putRows(client, tableName, 9000002, 1002, time, 2);
//
//            educircle(client, time);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 不留垃圾。
//            try {
//                OTSUtil.deleteTable(client, table);
//            } catch (ServiceException e) {
//                System.err.println("删除表格失败，原因：" + e.getMessage());
//                e.printStackTrace();
//            } catch (ClientException e) {
//                System.err.println("删除表格请求失败，原因：" + e.getMessage());
//                e.printStackTrace();
//            }
//            client.shutdown();
//        }
//    }
//
//    private static void educircle(OTSClient client, long ptime) throws InstantiationException, IllegalAccessException {
//        long time = ptime;
//        long userid = 9000001, topicid = 0, classid = 0, topictype = 0;
//        Direction direction = Direction.BACKWARD;
//        System.out.println("查询用户 topic 时间之 前 的 所有帖子");
//        userid = 9000001;
//        topicid = 10;
//
//        OTSQuery<TopicIndex> otsQuery = new OTSQuery<TopicIndex>();
//        otsQuery.setTablename(tableName);
//        otsQuery.setDirection(direction);
//        otsQuery.setLimit(limit);
//        otsQuery.setRequiredType(TopicIndex.class);
//        otsQuery.addPrimaryKey(pkname[0],userid,PrimaryKeyType.INTEGER, OTSQuery.CompareOperator.EQUAL);
//        otsQuery.addPrimaryKey(pkname[1],topicid,PrimaryKeyType.INTEGER,OTSQuery.CompareOperator.LESS_EQUAL);
//        otsQuery.setColumnsToGet(columnget);
//
//        List<TopicIndex> list = OTSUtil.readLimitRows(client,otsQuery);
//        for(TopicIndex topicIndex:list){
//            System.out.println(topicIndex);
//        }
//
//        System.out.println("---------------------------");
//        otsQuery.setDirection(Direction.FORWARD);
//        list = OTSUtil.readLimitRows(client,otsQuery);
//        for(TopicIndex topicIndex:list){
//            System.out.println(topicIndex);
//        }
//
//    }
//
//    private static void putRows(OTSClient client, String tableName, int userid, int classid, long time, int topictype)
//            throws OTSException, ClientException {
//        int bid = 1;
//        final int rowCount = putrows;
//        List<RowPutChange> rows = new ArrayList<RowPutChange>(rowCount);
//        for (int i = 0; i < rowCount; ++i) {
//            RowPutChange rowChange = new RowPutChange(tableName);
//            RowPrimaryKey primaryKey = new RowPrimaryKey();
//
//            int pi = 0, ci = 0;
//            primaryKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
//            primaryKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(seq.getAndAdd(1)));
//            rowChange.setPrimaryKey(primaryKey);
//
//            rowChange.addAttributeColumn(colname[ci++], ColumnValue.fromLong(System.currentTimeMillis()));
//            rowChange.addAttributeColumn(colname[ci++], ColumnValue.fromLong(topictype));
//            rowChange.addAttributeColumn(colname[ci++], ColumnValue.fromLong(classid));
//            rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_NOT_EXIST));
//            rows.add(rowChange);
//        }
//
//        BatchWriteRowResult batchWriteRowResult = OTSUtil.batchRowRequest(client, rows);
//
//        System.out.println(batchWriteRowResult);
//    }
//
////    private static <T> List<T> getRange(Class<T> clazz, OTSClient client, String tableName, long userid, long topicid,
////                                        long time, long classid, long topictype, Direction direction)
////            throws OTSException, ClientException, IllegalAccessException, InstantiationException {
////        Preconditions.checkArgument(clazz != null, " Class must not be null.");
////        System.out.println("getRange---------------------------------------------------------------------------------------");
////        System.out.println("userid：" + userid + "- classid: " + classid + "- topicid: " + topicid + "- time: " + time + "- topictype: " + topictype);
////        System.out.println("limit:" + limit);
//////        System.out.println(T);
////        // 演示一下如何按主键范围查找，这里查找uid从1-4（左开右闭）的数据
////        int pi = 0, ci = 0;
////        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
////        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();
////
////        if (direction == Direction.FORWARD) {
////            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
////            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], topicid == -1 ? PrimaryKeyValue.INF_MIN : PrimaryKeyValue.fromLong(topicid));
////            pi = 0;
////            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
////            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.INF_MAX);
////        } else {
////            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
////            inclusiveStartKey.addPrimaryKeyColumn(pkname[pi++], topicid == -1 ? PrimaryKeyValue.INF_MAX : PrimaryKeyValue.fromLong(topicid));
////            pi = 0;
////            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.fromLong(userid));
////            exclusiveEndKey.addPrimaryKeyColumn(pkname[pi++], PrimaryKeyValue.INF_MIN);
////        }
////
////        System.out.println(inclusiveStartKey.toString());
////        System.out.println(exclusiveEndKey.toString());
////        System.out.println();
////        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
////
////
////        List<T> rows = OTSUtil.readLimitRows(client,clazz, tableName, inclusiveStartKey, exclusiveEndKey, limit, direction, columnget);
////        if (VERBOSE)
////            for (T row : rows) {
//////                System.out.println("topictype 信息为：" + row.getColumns().get(pkname[0]));
////                System.out.println("---");
////            }
////
////        System.out.println("本次查询数据条数：" + rows.size());
////        System.out.println(rows);
////        System.out.println("getRange---------------------------------------------------------------------------------------end");
////
////        return rows;
////    }
//
//}
