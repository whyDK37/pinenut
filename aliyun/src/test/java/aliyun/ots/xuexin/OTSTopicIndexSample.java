//package aliyun.ots.xuexin;
//
//
//import aliyun.ots.OTSUtil;
//import aliyun.ots.StringUtils;
//import aliyun.ots.OTSTable;
//import com.aliyun.openservices.ots.*;
//import com.aliyun.openservices.ots.model.*;
//import javafx.util.Pair;
//
//import java.util.List;
//
//import static aliyun.ots.OTSUtil.getClient;
//
//public class OTSTopicIndexSample {
//    private static final String COLUMN_GID_NAME = "gid";
//    private static final String COLUMN_UID_NAME = "uid";
//    private static final String COLUMN_NAME_NAME = "name";
//    private static final String COLUMN_ADDRESS_NAME = "address";
//    private static final String COLUMN_AGE_NAME = "age";
//    private static final String COLUMN_MOBILE_NAME = "mobile";
//
//    private static final String tableName = "topic_index";
//    private static final int putrows = 20;
//    private static final boolean BERBOSE = true;
//
//    public static void main(String args[]) {
//        OTSClient client = getClient();
////        final String tableName = "sampleTable";
////        final int putrows = 100;
//        OTSTable table = new OTSTable(tableName);
//        try {
//            table.addPrimaryKey(COLUMN_GID_NAME, PrimaryKeyType.STRING)
//                    .addPrimaryKey(COLUMN_UID_NAME, PrimaryKeyType.STRING)
//                    .setReadCapacityUnit(100)
//                    .setWriteCapacityUnit(200);
//            // 创建表
//           OTSUtil.createTable(table);
//
//            // 注意：创建表只是提交请求，OTS创建表需要一段时间。
//            // 这里简单地等待一下，请根据您的实际逻辑修改。
//            Thread.sleep(1000);
//            OTSUtil.descTable(table.getTableName());
//            // 插入多行数据。
//            putRows(client, tableName,"9000001","1001","1");
//            putRows(client, tableName,"9000002","1002","2");
////            // 再取回来看看。
//            String userid="9000001",topicid="5",time="9999999999999",classid="1002";
//            getRange(client, tableName, BERBOSE,userid,topicid,time,classid,"0");
////            getRangeBACKWARD(client, tableName, BERBOSE,userid,topicid,time,classid);
////            getCount(client, tableName);
//            //类似分页查询
////            readByPage(client, tableName);
//        } catch (ServiceException e) {
//            System.err.println("操作失败，详情：" + e.getErrorCode() + " - " + e.getMessage());
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
//                OTSUtil.deleteTable(table);
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
//    private static void getCount(OTSClient client, String tableName) {
//        // 演示一下如何按主键范围查找，这里查找uid从1-4（左开右闭）的数据
//        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
//        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
//        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.fromLong(1));
//        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.INF_MIN); // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
//
//        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();
//        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.fromLong(4));
//        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.INF_MAX); // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
//
//        criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
//        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
//        criteria.addColumnsToGet(new String[]{COLUMN_GID_NAME});
//        GetRangeRequest request = new GetRangeRequest();
//        request.setRangeRowQueryCriteria(criteria);
//        GetRangeResult result = client.getRange(request);
//        List<Row> rows = result.getRows();
//
//        System.out.println("本次查询数据条数：" + rows.size());
//        System.out.println("本次读操作消耗的读CapacityUnit为：" + result.getConsumedCapacity().getCapacityUnit()
//                .getReadCapacityUnit());
//    }
//
//    private static void createTable(OTSClient client, String tableName)
//            throws ServiceException, ClientException {
//        TableMeta tableMeta = new TableMeta(tableName);
//        tableMeta.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyType.INTEGER);
//        tableMeta.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyType.INTEGER);
//        // 将该表的读写CU都设置为100
//        CapacityUnit capacityUnit = new CapacityUnit(100, 100);
//
//        CreateTableRequest request = new CreateTableRequest();
//        request.setTableMeta(tableMeta);
//        request.setReservedThroughput(capacityUnit);
//        client.createTable(request);
//
//        System.out.println("表已创建");
//    }
//
//    private static void deleteTable(OTSClient client, String tableName)
//            throws ServiceException, ClientException {
//        DeleteTableRequest request = new DeleteTableRequest();
//        request.setTableName(tableName);
//        client.deleteTable(request);
//
//        System.out.println("表已删除");
//    }
//
//    private static void putRows(OTSClient client, String tableName,String userid,String classid,String topictype)
//            throws OTSException, ClientException {
//        int bid = 1;
//        final int rowCount = putrows;
//        String time = String.valueOf(System.currentTimeMillis());
//        for (int i = 0; i < rowCount; ++i) {
//            RowPutChange rowChange = new RowPutChange(tableName);
//            RowPrimaryKey primaryKey = new RowPrimaryKey();
//            primaryKey.addPrimaryKeyColumn(COLUMN_GID_NAME, buildkey(userid,i+"",time,classid));
//            primaryKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.fromString(topictype));
//            rowChange.setPrimaryKey(primaryKey);
//
////            rowChange.addAttributeColumn(COLUMN_NAME_NAME, ColumnValue.fromString("小" + Integer.toString(i + 1)));
////            rowChange.addAttributeColumn(COLUMN_MOBILE_NAME, ColumnValue.fromString("111111111"));
////            rowChange.addAttributeColumn(COLUMN_ADDRESS_NAME, ColumnValue.fromString("中国A地"));
////            rowChange.addAttributeColumn(COLUMN_AGE_NAME, ColumnValue.fromLong(20));
//            rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_NOT_EXIST));
//
//            PutRowRequest request = new PutRowRequest();
//            request.setRowChange(rowChange);
//
//            PutRowResult result = client.putRow(request);
//            int consumedWriteCU = result.getConsumedCapacity()
//                    .getCapacityUnit().getWriteCapacityUnit();
//
//            if (BERBOSE)
//                System.out.println("成功插入数据, 消耗的写CU为：" + consumedWriteCU);
//        }
//
//        System.out.println(String.format("成功插入%d行数据。", rowCount));
//    }
//
//    private static PrimaryKeyValue buildkey(String userid,String topicid,String time,String classid){
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(StringUtils.zeroPadString(userid,9)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString(topicid,9)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString(time,13)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString(classid,9));
//        return PrimaryKeyValue.fromString(stringBuilder.toString());
//    }
//
//
//    private static PrimaryKeyValue buildkeyMin(){
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(StringUtils.zeroPadString("",9)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString("",9)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString("",13)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString("",9));
//        return PrimaryKeyValue.fromString(stringBuilder.toString());
//    }
//
//    private static PrimaryKeyValue buildkeyMax(){
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(StringUtils.zeroPadString("999999999",9)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString("999999999",9)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString("9999999999999",13)).append(",");
//        stringBuilder.append(StringUtils.zeroPadString("999999999",9));
//        return PrimaryKeyValue.fromString(stringBuilder.toString());
//    }
//
////    private static void getRange(OTSClient client, String tableName, boolean printrows)
////            throws OTSException, ClientException {
////        System.out.println("getRange---------------------------------------");
////        // 演示一下如何按主键范围查找，这里查找uid从1-4（左开右闭）的数据
////        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
////        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
////        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_GID_NAME, buildkeyMin());
////        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.INF_MIN);
////        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
////
////        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();
////        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_GID_NAME, buildkeyMax());
////        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.INF_MAX);
////        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
////
////        criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
////        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
//////        criteria.setDirection(Direction.BACKWARD);
////        GetRangeRequest request = new GetRangeRequest();
////        request.setRangeRowQueryCriteria(criteria);
////        GetRangeResult result = client.getRange(request);
////        List<Row> rows = result.getRows();
////        if (printrows)
////            for (Row row : rows) {
////                System.out.println("gid信息为：" + row.getColumns().get(COLUMN_GID_NAME));
////                System.out.println("uid信息为：" + row.getColumns().get(COLUMN_UID_NAME));
////                System.out.println("name信息为：" + row.getColumns().get(COLUMN_NAME_NAME));
////                System.out.println("address信息为：" + row.getColumns().get(COLUMN_ADDRESS_NAME));
////                System.out.println("mobile信息为：" + row.getColumns().get(COLUMN_MOBILE_NAME));
////                System.out.println("age信息为：" + row.getColumns().get(COLUMN_AGE_NAME));
////                System.out.println("----------------------------------------------------");
////            }
////
////        int consumedReadCU = result.getConsumedCapacity().getCapacityUnit()
////                .getReadCapacityUnit();
////        System.out.println("本次查询数据条数：" + rows.size());
////        System.out.println("本次读操作消耗的读CapacityUnit为：" + consumedReadCU);
////
////        System.out.println("getRange---------------------------------------end");
////    }
//
//    private static void getRange(OTSClient client, String tableName, boolean printrows,String userid,String topicid,
//                                 String time,String classid,String topictype)
//            throws OTSException, ClientException {
//        System.out.println("getRange---------------------------------------");
//        System.out.println(buildkey(userid,topicid,time,classid).asString());
//        // 演示一下如何按主键范围查找，这里查找uid从1-4（左开右闭）的数据
//        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
//        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
//        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_GID_NAME, buildkey(userid,topicid,time,classid));
//        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.fromString(topictype));
//        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
//
//        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();
//        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.INF_MAX);
//        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.INF_MAX);
//        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小
//
//        criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
//        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
//        criteria.setLimit(10);
////        criteria.setDirection(Direction.BACKWARD);
//        GetRangeRequest request = new GetRangeRequest();
//        request.setRangeRowQueryCriteria(criteria);
//        GetRangeResult result = client.getRange(request);
//        List<Row> rows = result.getRows();
//        if (printrows)
//            for (Row row : rows) {
//                System.out.println("gid信息为：" + row.getColumns().get(COLUMN_GID_NAME));
//                System.out.println("uid信息为：" + row.getColumns().get(COLUMN_UID_NAME));
//                System.out.println("name信息为：" + row.getColumns().get(COLUMN_NAME_NAME));
//                System.out.println("address信息为：" + row.getColumns().get(COLUMN_ADDRESS_NAME));
//                System.out.println("mobile信息为：" + row.getColumns().get(COLUMN_MOBILE_NAME));
//                System.out.println("age信息为：" + row.getColumns().get(COLUMN_AGE_NAME));
//                System.out.println("----------------------------------------------------");
//            }
//
//        int consumedReadCU = result.getConsumedCapacity().getCapacityUnit()
//                .getReadCapacityUnit();
//        System.out.println("本次查询数据条数：" + rows.size());
//        System.out.println("本次读操作消耗的读CapacityUnit为：" + consumedReadCU);
//
//        System.out.println("getRange---------------------------------------end");
//    }
//
//    private static void readByPage(OTSClient client, String tableName) {
//        System.out.println("readByPage-------------------------------");
//        int pageSize = 8;
//        int offset = 33;
//
//        RowPrimaryKey startKey = new RowPrimaryKey();
//        startKey.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.INF_MIN);
//        startKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.INF_MIN);
//
//        RowPrimaryKey endKey = new RowPrimaryKey();
//        endKey.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.INF_MAX);
//        endKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.INF_MAX);
//        // 读第一页，从范围的offset=33的行开始读起
//        Pair<List<Row>, RowPrimaryKey> result = OTSUtil.readByPage(client, tableName, startKey, endKey, offset, pageSize);
//        for (Row row : result.getKey()) {
//            System.out.println(row.getColumns());
//        }
//        System.out.println("Total rows count: " + result.getKey().size());
//
//        // 顺序翻页，读完范围内的所有数据
//        startKey = result.getValue();
//        while (startKey != null) {
//            System.out.println("============= start read next page ==============");
//            result = OTSUtil.readByPage(client, tableName, startKey, endKey, 0, pageSize);
//            for (Row row : result.getKey()) {
//                System.out.println(row.getColumns());
//            }
//            startKey = result.getValue();
//            System.out.println("Total rows count: " + result.getKey().size());
//        }
//
//        System.out.println("readByPage-------------------------------end");
//    }
//}
