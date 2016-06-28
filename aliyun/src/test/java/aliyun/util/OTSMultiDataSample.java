package aliyun.util;


import java.util.List;

import com.aliyun.openservices.ots.ClientException;
import com.aliyun.openservices.ots.ServiceException;
import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.OTSErrorCode;
import com.aliyun.openservices.ots.OTSException;
import com.aliyun.openservices.ots.model.*;
import com.aliyun.openservices.ots.model.condition.ColumnCondition;
import com.aliyun.openservices.ots.model.condition.ColumnConditionType;
import com.google.protobuf.ByteString;

public class OTSMultiDataSample {
    private static final String COLUMN_GID_NAME = "gid";
    private static final String COLUMN_UID_NAME = "uid";
    private static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_ADDRESS_NAME = "address";
    private static final String COLUMN_AGE_NAME = "age";
    private static final String COLUMN_MOBILE_NAME = "mobile";

    public static void main(String args[]) {
//        final String endPoint = "http://";
//        final String accessId = "xxxx";
//        final String accessKey = "yyyy";
//        final String instanceName = "zzzz";
//
//        OTSClient client = new OTSClient(endPoint, accessId, accessKey,
//                instanceName);
        OTSClient client = OTSUtil.getClient();
        final String tableName = "sampleTable";

        try {
            // 创建表
            createTable(client, tableName);

            // 注意：创建表只是提交请求，OTS创建表需要一段时间。
            // 这里简单地等待30秒，请根据您的实际逻辑修改。
            Thread.sleep(500);

            // 插入多行数据。
            putRows(client, tableName);
            // 再取回来看看。
            getRange(client, tableName);
//            getCount(client, tableName);
        } catch (ServiceException e) {
            System.err.println("操作失败，详情：" + e.getErrorCode() +" - "+ e.getMessage());
            // 可以根据错误代码做出处理， OTS的ErrorCode定义在OTSErrorCode中。
            if (OTSErrorCode.QUOTA_EXHAUSTED.equals(e.getErrorCode())) {
                System.err.println("超出存储配额。");
            }
            // Request ID可以用于有问题时联系客服诊断异常。
            System.err.println("Request ID:" + e.getRequestId());
        } catch (ClientException e) {
            // 可能是网络不好或者是返回结果有问题
            System.err.println("请求失败，详情：" + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } finally {
            // 不留垃圾。
            try {
                deleteTable(client, tableName);
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

    private static void getCount(OTSClient client, String tableName) {
        // 演示一下如何按主键范围查找，这里查找uid从1-4（左开右闭）的数据
        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_GID_NAME,PrimaryKeyValue.fromLong(1));
        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_UID_NAME,PrimaryKeyValue.INF_MIN); // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小

        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();
        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_GID_NAME,PrimaryKeyValue.fromLong(4));
        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_UID_NAME,PrimaryKeyValue.INF_MAX); // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小

        criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
        criteria.addColumnsToGet(new String[]{COLUMN_GID_NAME});
        GetRangeRequest request = new GetRangeRequest();
        request.setRangeRowQueryCriteria(criteria);
        GetRangeResult result = client.getRange(request);
        List<Row> rows = result.getRows();
//        for (Row row : rows) {
//            System.out.println("name信息为："
//                    + row.getColumns().get(COLUMN_NAME_NAME));
//            System.out.println("address信息为："
//                    + row.getColumns().get(COLUMN_ADDRESS_NAME));
//            System.out.println("mobile信息为："
//                    + row.getColumns().get(COLUMN_MOBILE_NAME));
//            System.out
//                    .println("age信息为：" + row.getColumns().get(COLUMN_AGE_NAME));
//        }

        System.out.println("本次查询数据条数：" + rows.size());
        System.out.println("本次读操作消耗的读CapacityUnit为：" + result.getConsumedCapacity().getCapacityUnit()
                .getReadCapacityUnit());
    }

    private static void createTable(OTSClient client, String tableName)
            throws ServiceException, ClientException {
        TableMeta tableMeta = new TableMeta(tableName);
        tableMeta.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyType.INTEGER);
        tableMeta.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyType.INTEGER);
        // 将该表的读写CU都设置为100
        CapacityUnit capacityUnit = new CapacityUnit(100, 100);

        CreateTableRequest request = new CreateTableRequest();
        request.setTableMeta(tableMeta);
        request.setReservedThroughput(capacityUnit);
        client.createTable(request);

        System.out.println("表已创建");
    }

    private static void deleteTable(OTSClient client, String tableName)
            throws ServiceException, ClientException {
        DeleteTableRequest request = new DeleteTableRequest();
        request.setTableName(tableName);
        client.deleteTable(request);

        System.out.println("表已删除");
    }

    private static void putRows(OTSClient client, String tableName)
            throws OTSException, ClientException {
        int bid = 1;
        final int rowCount = 10;
        for (int i = 0; i < rowCount; ++i) {
            RowPutChange rowChange = new RowPutChange(tableName);
            RowPrimaryKey primaryKey = new RowPrimaryKey();
            primaryKey.addPrimaryKeyColumn(COLUMN_GID_NAME,PrimaryKeyValue.fromLong(i));
            primaryKey.addPrimaryKeyColumn(COLUMN_UID_NAME,PrimaryKeyValue.fromLong(i));
            rowChange.setPrimaryKey(primaryKey);

            rowChange.addAttributeColumn(COLUMN_NAME_NAME,ColumnValue.fromString("小" + Integer.toString(i + 1)));
            rowChange.addAttributeColumn(COLUMN_MOBILE_NAME,ColumnValue.fromString("111111111"));
            rowChange.addAttributeColumn(COLUMN_ADDRESS_NAME,ColumnValue.fromString("中国A地"));
            rowChange.addAttributeColumn(COLUMN_AGE_NAME,ColumnValue.fromLong(20));
            rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_NOT_EXIST));

            PutRowRequest request = new PutRowRequest();
            request.setRowChange(rowChange);

            PutRowResult result = client.putRow(request);
            int consumedWriteCU = result.getConsumedCapacity()
                    .getCapacityUnit().getWriteCapacityUnit();

            System.out.println("成功插入数据, 消耗的写CU为：" + consumedWriteCU);
        }

        System.out.println(String.format("成功插入%d行数据。", rowCount));
    }

    private static void getRange(OTSClient client, String tableName)
            throws OTSException, ClientException {
        // 演示一下如何按主键范围查找，这里查找uid从1-4（左开右闭）的数据
        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
        RowPrimaryKey inclusiveStartKey = new RowPrimaryKey();
        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_GID_NAME,PrimaryKeyValue.fromLong(0));
        inclusiveStartKey.addPrimaryKeyColumn(COLUMN_UID_NAME,PrimaryKeyValue.INF_MIN);
        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小

        RowPrimaryKey exclusiveEndKey = new RowPrimaryKey();
        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_GID_NAME, PrimaryKeyValue.fromLong(4));
        exclusiveEndKey.addPrimaryKeyColumn(COLUMN_UID_NAME, PrimaryKeyValue.INF_MAX);
        // 范围的边界需要提供完整的PK，若查询的范围不涉及到某一列值的范围，则需要将该列设置为无穷大或者无穷小

        criteria.setInclusiveStartPrimaryKey(inclusiveStartKey);
        criteria.setExclusiveEndPrimaryKey(exclusiveEndKey);
//        criteria.setDirection(Direction.BACKWARD);
        GetRangeRequest request = new GetRangeRequest();
        request.setRangeRowQueryCriteria(criteria);
        GetRangeResult result = client.getRange(request);
        List<Row> rows = result.getRows();
        for (Row row : rows) {
            System.out.println("gid信息为：" + row.getColumns().get(COLUMN_GID_NAME));
            System.out.println("uid信息为：" + row.getColumns().get(COLUMN_UID_NAME));
            System.out.println("name信息为：" + row.getColumns().get(COLUMN_NAME_NAME));
            System.out.println("address信息为：" + row.getColumns().get(COLUMN_ADDRESS_NAME));
            System.out.println("mobile信息为：" + row.getColumns().get(COLUMN_MOBILE_NAME));
            System.out.println("age信息为：" + row.getColumns().get(COLUMN_AGE_NAME));
            System.out.println("----------------------------------------------------");
        }

        int consumedReadCU = result.getConsumedCapacity().getCapacityUnit()
                .getReadCapacityUnit();
        System.out.println("本次查询数据条数："+rows.size());
        System.out.println("本次读操作消耗的读CapacityUnit为：" + consumedReadCU);
    }
}
