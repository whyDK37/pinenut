//package aliyun.ots.xuexin;
//
//
//import aliyun.ots.OTSTable;
//import aliyun.ots.OTSUtil;
//import aliyun.ots.StringUtils;
//import com.aliyun.openservices.ots.*;
//import com.aliyun.openservices.ots.model.*;
//import org.junit.Assert;
//
//import java.util.List;
//import java.util.Map;
//
//import static aliyun.ots.OTSUtil.getClient;
//
///**
// * 用户ID  时间  班级ID+帖子类型 帖子ID
// * userid  time class+topictype topicid
// */
//public class ClassGroupSample {
//
//    private static final String tableName = "topic_index";
//    private static final int putrows = 20;
//    private static final int limit = 5;
//    private static final boolean BERBOSE = true;
//    static String[] pkname = {"userid", "classntt", "time", "topicid"};
//    static PrimaryKeyType[] pktype = {PrimaryKeyType.INTEGER, PrimaryKeyType.STRING, PrimaryKeyType.INTEGER, PrimaryKeyType.INTEGER};
//    public static final String Separator = ",";
//
//    public static void main(String args[]) {
//        OTSClient client = getClient();
//        OTSTable table = new OTSTable(tableName);
//        try {
//            for (int i = 0; i < pkname.length; i++) {
//                table.addPrimaryKey(pkname[i], pktype[i]);
//            }
//            table.setReadCapacityUnit(100)
//                    .setWriteCapacityUnit(200);
//            // 创建表
//            OTSUtil.createTable(client, table);
//
//            // 注意：创建表只是提交请求，OTS创建表需要一段时间。
//            // 这里简单地等待一下，请根据您的实际逻辑修改。
//            Thread.sleep(1000);
//            OTSUtil.descTable(client, table.getTableName());
//            // 插入多行数据。
//            long time = System.currentTimeMillis();
//            putRows(client, tableName, 9000001, 1001, time, 1);
//            putRows(client, tableName, 9000001, 1001, time, 2);
//            putRows(client, tableName, 9000002, 1002, time, 1);
//            putRows(client, tableName, 9000002, 1002, time, 2);
//
//            educircle(client, time);
////            getRangeBACKWARD(client, tableName, BERBOSE,userid,topicid,time,classid);
//        } catch (ServiceException e) {
//            System.err.println("操作失败，详情：" + e.getErrorCode() + " - " + e.getMessage());
//            e.printStackTrace();
//            // 可以根据错误代码做出处理， OTS的ErrorCode定义在OTSErrorCode中。
//            if (OTSErrorCode.QUOTA_EXHAUSTED.equals(e.getErrorCode())) {
//                System.err.println("超出存储配额。");
//            }
//            // Request ID可以用于有问题时联系客服诊断异常。
//            System.err.println("Request ID:" + e.getRequestId());
//        } catch (ClientException e) {
//            // 可能是网络不好或者是返回结果有问题
//            System.err.println("请求失败，详情：" + e.getMessage());
//        } catch (InterruptedException e) {
//            System.err.println(e.getMessage());
//        } finally {
//            // 不留垃圾。
//            try {
//                OTSUtil.deleteTable(client, table);
////                deleteTable(client, tableName);
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
//    private static void educircle(OTSClient client, long ptime) {
//        long time, userid, topicid, classid, topictype;
//        List<Row> rows;
//        System.out.println("查询所有帖子");
//        userid = 9000002L;
//        topicid = putrows;
//        classid = 1002;
//        time = ptime + topicid;
//        topictype = 1;
//        rows = getRange(client, tableName, BERBOSE, userid, topicid, time, classid, topictype);
//        Assert.assertTrue(rows.size() > 0);
//
//        System.out.println("查询用户 time 时间之 前 的 所有帖子");
//        Row row = rows.get(rows.size() - 1);
//        Map<String, ColumnValue> columns = row.getColumns();
//        userid = columns.get(pkname[0]).asLong();
//        classid = Long.valueOf(columns.get(pkname[1]).asString().split(Separator)[0]);
//        topictype = Long.valueOf(columns.get(pkname[1]).asString().split(Separator)[1]);
//        time = columns.get(pkname[2]).asLong();
//        topicid = columns.get(pkname[3]).asLong();
//        rows = getRange(client, tableName, BERBOSE, userid, topicid, time, classid, topictype);
//        Assert.assertTrue(rows.size() > 0);
//
//        System.out.println("查询帖子id之后的帖子");
//        userid = 9000002L;
//        topicid = 1L;
//        classid = 1002;
//        time = ptime + topicid;
//        topictype = 1;
//        rows = getRange(client, tableName, BERBOSE, userid, topicid, time, classid, topictype);
//
//        System.out.println("不存在的用户查询结果为0");
//        userid = 9000000L;
//        topicid = 0L;
//        classid = 0;
//        time = 0;
//        topictype = 1;
//        rows = getRange(client, tableName, BERBOSE, userid, topicid, time, classid, topictype);
//        Assert.assertTrue(rows.size() == 0);
//
//        System.out.println("不存在的班级查询结果0");
//        userid = 9000002L;
//        topicid = 2L;
//        classid = 1003;
//        time = 0;
//        topictype = 1;
//        rows = getRange(client, tableName, BERBOSE, userid, topicid, time, classid, topictype);
//        Assert.assertTrue(rows.size() == 0);
//    }
//
//    private static void putRows(OTSClient client, String tableName, int userid, int classid, long time, int topictype)
//            throws OTSException, ClientException {
//        int bid = 1;
//        final int rowCount = putrows;
//        for (int i = 0; i < rowCount; ++i) {
//            RowPutChange rowChange = new RowPutChange(tableName);
//            RowPrimaryKey primaryKey = new RowPrimaryKey();
//
//            primaryKey.addPrimaryKeyColumn(pkname[0], PrimaryKeyValue.fromLong(userid));
//            primaryKey.addPrimaryKeyColumn(pkname[1], buildkey(classid, topictype));
//            primaryKey.addPrimaryKeyColumn(pkname[2], PrimaryKeyValue.fromLong(++time));
//            primaryKey.addPrimaryKeyColumn(pkname[3], PrimaryKeyValue.fromLong(i));
//            rowChange.setPrimaryKey(primaryKey);
//
//            rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_NOT_EXIST));
//
//            PutRowRequest request = new PutRowRequest();
//            request.setRowChange(rowChange);
//
//            PutRowResult result = client.putRow(request);
//            int consumedWriteCU = result.getConsumedCapacity()
//                    .getCapacityUnit().getWriteCapacityUnit();
//
////            if (BERBOSE)
////                System.out.println("成功插入数据, 消耗的写CU为：" + consumedWriteCU);
//        }
//
//        System.out.println(String.format("成功插入%d行数据。", rowCount));
//    }
//
//    private static PrimaryKeyValue buildkey(long classid, long topictype) {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(StringUtils.zeroPadString(classid + "", 9)).append(Separator);
//        stringBuilder.append(StringUtils.zeroPadString(topictype + "", 1)).append(Separator);
////        stringBuilder.append(StringUtils.zeroPadString(time + "", 13)).append(Separator);
////        stringBuilder.append(StringUtils.zeroPadString(topicid + "", 9)).append(Separator);
//        return PrimaryKeyValue.fromString(stringBuilder.toString());
//    }
//
//    private static List<Row> getRange(OTSClient client, String tableName, boolean printrows, long userid, long topicid,
//                                      long time, long classid, long topictype)
//            throws OTSException, ClientException {
//        System.out.println("getRange---------------------------------------------------------------------------------------");
////        System.out.println("userid：" + userid + "- classid: " + classid + "- topicid: " + topicid + "- time: " + time + "- topictype: " + topictype);
//        System.out.println("limit:" + limit);
//        // 演示一下如何按主键范围查找，
//        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
//        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
//        inclusiveStartKey.addPrimaryKeyColumn(pkname[0], PrimaryKeyValue.fromLong(userid));
//        inclusiveStartKey.addPrimaryKeyColumn(pkname[1], buildkey(classid, topictype));
//        inclusiveStartKey.addPrimaryKeyColumn(pkname[2], time == 0 ? PrimaryKeyValue.INF_MAX : PrimaryKeyValue.fromLong(time));
//        inclusiveStartKey.addPrimaryKeyColumn(pkname[3], topicid == 0 ? PrimaryKeyValue.INF_MAX : PrimaryKeyValue.fromLong(topicid));
//        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
//
//        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();
//        exclusiveEndKey.addPrimaryKeyColumn(pkname[0], PrimaryKeyValue.fromLong(userid));
//        exclusiveEndKey.addPrimaryKeyColumn(pkname[1], buildkey(classid, topictype));
//        exclusiveEndKey.addPrimaryKeyColumn(pkname[2], PrimaryKeyValue.INF_MIN);
//        exclusiveEndKey.addPrimaryKeyColumn(pkname[3], PrimaryKeyValue.INF_MIN);
//        System.out.println(inclusiveStartKey.toString());
//        System.out.println(exclusiveEndKey.toString());
//        System.out.println();
//        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
//
////        criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
////        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
////        criteria.setLimit(limit);
////        criteria.setDirection(Direction.BACKWARD);
////        GetRangeRequest request = new GetRangeRequest();
////        request.setRangeRowQueryCriteria(criteria);
////        GetRangeResult result = client.getRange(request);
////        List<Row> rows = result.getRows();
//
//        List<Row> rows = OTSUtil.readLimitRows(client, tableName, inclusiveStartKey, exclusiveEndKey, limit, Direction.BACKWARD);
//        if (printrows)
//            for (Row row : rows) {
//                row.getColumns();
//                for (String pk : pkname)
//                    System.out.println(pk + " 信息为：" + row.getColumns().get(pk));
////                  System.out.println("topictype 信息为：" + row.getColumns().get(pkname[0]));
//                System.out.println("---");
//            }
//
////        int consumedReadCU = result.getConsumedCapacity().getCapacityUnit()
////                .getReadCapacityUnit();
////        System.out.println("本次查询数据条数：" + rows.size());
////        System.out.println("本次读操作消耗的读CapacityUnit为：" + consumedReadCU);
//
//        System.out.println("getRange---------------------------------------------------------------------------------------end");
//
//        return rows;
//    }
//}
