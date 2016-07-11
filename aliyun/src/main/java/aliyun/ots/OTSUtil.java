package aliyun.ots;

import com.aliyun.openservices.ots.ClientException;
import com.aliyun.openservices.ots.OTSException;
import com.aliyun.openservices.ots.ServiceException;
import com.aliyun.openservices.ots.model.*;
import com.aliyun.openservices.ots.model.condition.ColumnCondition;
import com.aliyun.openservices.ots.utils.Preconditions;
import javafx.util.Pair;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.openservices.ots.OTSClient;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 2016-7-5 王环宇
 */
public abstract class OTSUtil {
    private static final Log logger = LogFactory.getLog(OTSUtil.class);
    //    private static final Logger Log = LoggerFactory.getLogger(OTSUtil.class);
    private static OTSClient clientIns;

    /**
     * 批处理一次最大数据量
     */
    public static final int BATCH_THRESHOLD = 200;
//    public static final String ALIYUN_OTS_ENDPOINT = "http://ots.aliyuncs.com";
//    public static final String ALIYUN_ACCESS_ID = "8pO2VD2xEYk0vpGF";
//    public static final String ALIYUN_ACCESS_KEY = "2pDJZvDZN05TBeeLZZxGiIM2HOW1Yp";
//    public static final String ALIYUN_INSTANCE_NAME = "hi20479763@aliyun.com";

    public static final String ALIYUN_OTS_ENDPOINT = "http://xuexin-dev.cn-hangzhou.ots.aliyuncs.com";
    public static final String ALIYUN_ACCESS_ID = "8pO2VD2xEYk0vpGF";
    public static final String ALIYUN_ACCESS_KEY = "2pDJZvDZN05TBeeLZZxGiIM2HOW1Yp";
    public static final String ALIYUN_INSTANCE_NAME = "xuexin-dev";

    public static OTSClient getClient() {

        if (clientIns == null) {
//            String ALIYUN_INSTANCE_NAME = PropertyUtils.getInstance().getProp(
//                    "xuexin.ots.instance");
//            String ALIYUN_OTS_ENDPOINT = "http://"
//                    + ALIYUN_INSTANCE_NAME
//                    + "."
//                    + PropertyUtils.getInstance()
//                            .getProp("xuexin.ots.ALIYUN_OTS_ENDPOINT");

            clientIns = new OTSClient(ALIYUN_OTS_ENDPOINT,
                    ALIYUN_ACCESS_ID,
                    ALIYUN_ACCESS_KEY, ALIYUN_INSTANCE_NAME);
        }
        return clientIns;
    }

    /**
     * @param client
     * @param table
     * @throws ServiceException
     * @throws ClientException
     */
    public static void createTable(OTSClient client, OTSTable table)
            throws ServiceException, ClientException {
        TableMeta tableMeta = new TableMeta(table.getTableName());
        for (Map.Entry<String, PrimaryKeyType> entry : table.getPrimaryKey().entrySet()) {
            tableMeta.addPrimaryKeyColumn(entry.getKey(), entry.getValue());
        }

        CreateTableRequest request = new CreateTableRequest();
        request.setTableMeta(tableMeta);
        request.setReservedThroughput(table.getCapacityUnit());
        client.createTable(request);

    }

    public static void deleteTable(OTSClient client, OTSTable table)
            throws ServiceException, ClientException {
        deleteTable(client, table.getTableName());
    }

    private static void deleteTable(OTSClient client, String tableName)
            throws ServiceException, ClientException {
        DeleteTableRequest request = new DeleteTableRequest();
        request.setTableName(tableName);
        client.deleteTable(request);
    }

    /**
     * 更新table.注意：只能更新table的 ReservedThroughputChange信息。
     *
     * @param client
     * @param table
     * @throws ServiceException
     * @throws ClientException
     */
    private static void updateTable(OTSClient client, OTSTable table)
            throws ServiceException, ClientException {
        UpdateTableRequest request = new UpdateTableRequest();
        request.setTableName(table.getTableName());

        ReservedThroughputChange reservedThroughputChange = new ReservedThroughputChange();
        reservedThroughputChange.setReadCapacityUnit(table.getReadCapacityUnit());
        reservedThroughputChange.setWriteCapacityUnit(table.getWriteCapacityUnit());
        request.setReservedThroughputChange(reservedThroughputChange);

        client.updateTable(request);
    }

    /**
     * 批量添加记录，每次最大添加  OTSUtil.BATCH_THRESHOLD
     *
     * @param client
     * @param rowChanges
     * @return
     * @throws OTSException
     * @throws ClientException
     */
    public static BatchWriteRowResult batchRowRequest(OTSClient client, List<RowPutChange> rowChanges)
            throws OTSException, ClientException {
        return batchRowRequest(client, rowChanges, null);
    }

    /**
     * 批量记录操作
     *
     * @param client
     * @param rowChanges
     * @return
     * @throws OTSException
     * @throws ClientException
     */
    private static BatchWriteRowResult batchRowRequest(OTSClient client, List<RowPutChange> rowChanges, Condition condition)
            throws OTSException, ClientException {
        Preconditions.checkArgument(rowChanges.size() <= BATCH_THRESHOLD, "The number of rowChanges columns must be in range: [1, 200]");
        BatchWriteRowRequest batchWriteRowRequest = new BatchWriteRowRequest();

        for (RowPutChange rowPutChange : rowChanges) {
            if (condition != null) rowPutChange.setCondition(condition);
            batchWriteRowRequest.addRowPutChange(rowPutChange);
        }
        return client.batchWriteRow(batchWriteRowRequest);
    }

    /**
     * 获取tablename list
     *
     * @param client
     * @return
     */
    public static List<String> tableNameList(OTSClient client) {
        ListTableResult listTableResult = client.listTable();
        return listTableResult.getTableNames();
    }
//    /**
//     * 保存公众消息浏览记录
//     *
//     * @param username
//     * @version:v1.0
//     * @author:李大鹏
//     * @date:2014-5-19 下午3:25:14
//     */
//    public static void saveGzMsgViewHistory(Long msgID, String username) {
//        try {
//            RowPutChange rowChange = new RowPutChange("gzMsgViewHistory");
//            RowPrimaryKey primaryKey = new RowPrimaryKey();
//            primaryKey.addPrimaryKeyColumn("msgID",
//                    PrimaryKeyValue.fromLong(msgID));
//            primaryKey.addPrimaryKeyColumn("username",
//                    PrimaryKeyValue.fromString(username));
//            rowChange.setPrimaryKey(primaryKey);
//            rowChange.addAttributeColumn("createDate",
//                    ColumnValue.fromLong(System.currentTimeMillis()));
//            PutRowRequest request = new PutRowRequest();
//            request.setRowChange(rowChange);
//            OTSClient client = getClient();
//            client.putRow(request);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }

//    /**
//     *
//     * 方法描述 保存素材消息浏览记录
//     *
//     * @param msgID
//     * @param username
//     * @version:v1.0
//     * @author:zxl
//     * @date:2015-5-19 上午9:34:08
//     */
//    public static void saveGzMaterialViewHistory(Long msgID, String username) {
//        try {
//            RowPutChange rowChange = new RowPutChange("gzMaterialViewHistory");
//            RowPrimaryKey primaryKey = new RowPrimaryKey();
//            primaryKey.addPrimaryKeyColumn("msgID",
//                    PrimaryKeyValue.fromLong(msgID));
//            primaryKey.addPrimaryKeyColumn("username",
//                    PrimaryKeyValue.fromString(username));
//            rowChange.setPrimaryKey(primaryKey);
//            rowChange.addAttributeColumn("createDate",
//                    ColumnValue.fromLong(System.currentTimeMillis()));
//            PutRowRequest request = new PutRowRequest();
//            request.setRowChange(rowChange);
//            OTSClient client = getClient();
//            client.putRow(request);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }

//    /**
//     * 是否已经阅读了公众消息
//     *
//     * @param username
//     * @return
//     * @version:v1.0
//     * @author:李大鹏
//     * @date:2014-5-19 下午5:02:31
//     */
//    public static boolean isViewGzMsg(Long msgID, String username) {
//        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(
//                "gzMsgViewHistory");
//        RowPrimaryKey primaryKey = new RowPrimaryKey();
//        primaryKey
//                .addPrimaryKeyColumn("msgID", PrimaryKeyValue.fromLong(msgID));
//        primaryKey.addPrimaryKeyColumn("username",
//                PrimaryKeyValue.fromString(username));
//        criteria.setPrimaryKey(primaryKey);
//        Row row = null;
//        try {
//            OTSClient client = getClient();
//            GetRowRequest request = new GetRowRequest();
//            request.setRowQueryCriteria(criteria);
//            GetRowResult result = client.getRow(request);
//            row = result.getRow();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//
//        if (row != null && !row.getColumns().isEmpty())
//            return true;
//        else
//            return false;
//    }

//    /**
//     * 是否已经阅读了素材消息 方法描述
//     *
//     * @param msgID
//     * @param username
//     * @return
//     * @version:v1.0
//     * @author:zxl
//     * @date:2015-5-19 上午9:34:33
//     */
//    public static boolean isViewGzMaterial(Long msgID, String username) {
//        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(
//                "gzMaterialViewHistory");
//        RowPrimaryKey primaryKey = new RowPrimaryKey();
//        primaryKey
//                .addPrimaryKeyColumn("msgID", PrimaryKeyValue.fromLong(msgID));
//        primaryKey.addPrimaryKeyColumn("username",
//                PrimaryKeyValue.fromString(username));
//        criteria.setPrimaryKey(primaryKey);
//        Row row = null;
//        try {
//            OTSClient client = getClient();
//            GetRowRequest request = new GetRowRequest();
//            request.setRowQueryCriteria(criteria);
//            GetRowResult result = client.getRow(request);
//            row = result.getRow();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//
//        if (row != null && !row.getColumns().isEmpty())
//            return true;
//        else
//            return false;
//    }

//    /**
//     * 保存公众消息浏览记录(IP排重)
//     *
//     * @param msgID
//     * @param IP
//     * @version:v2.1
//     * @author:高青柏
//     * @date:2014-9-16 下午2:02:13
//     */
//    public static void saveGzMsgReaderNum(Long msgID, String IP) {
//        try {
//            RowPutChange rowChange = new RowPutChange("gzMsgReaderNum");
//            RowPrimaryKey primaryKey = new RowPrimaryKey();
//            primaryKey.addPrimaryKeyColumn("msgID",
//                    PrimaryKeyValue.fromLong(msgID));
//            primaryKey
//                    .addPrimaryKeyColumn("IP", PrimaryKeyValue.fromString(IP));
//            rowChange.setPrimaryKey(primaryKey);
//            rowChange.addAttributeColumn("createDate",
//                    ColumnValue.fromLong(System.currentTimeMillis()));
//            PutRowRequest request = new PutRowRequest();
//            request.setRowChange(rowChange);
//            OTSClient client = getClient();
//            client.putRow(request);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//    }

//    /**
//     * 是否已经阅读了公众消息（根据IP判断）
//     *
//     * @param msgID
//     * @param IP
//     * @return
//     * @version:v2.1
//     * @author:高青柏
//     * @date:2014-9-16 下午2:04:26
//     */
//    public static boolean isReaderGzMsg(Long msgID, String IP) {
//        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(
//                "gzMsgReaderNum");
//        RowPrimaryKey primaryKey = new RowPrimaryKey();
//        primaryKey
//                .addPrimaryKeyColumn("msgID", PrimaryKeyValue.fromLong(msgID));
//        primaryKey.addPrimaryKeyColumn("IP", PrimaryKeyValue.fromString(IP));
//        criteria.setPrimaryKey(primaryKey);
//        Row row = null;
//        try {
//            OTSClient client = getClient();
//            GetRowRequest request = new GetRowRequest();
//            request.setRowQueryCriteria(criteria);
//            GetRowResult result = client.getRow(request);
//            row = result.getRow();
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//
//        if (row != null && !row.getColumns().isEmpty())
//            return true;
//        else
//            return false;
//    }

    /**
     * 范围查询指定范围内的数据，返回指定页数大小的数据，并能根据offset跳过部分行。
     *
     * @param client
     * @param tableName
     * @param startKey
     * @param endKey
     * @param offset
     * @param pageSize
     * @return
     */
    public static Pair<List<Row>, RowPrimaryKey> readByPage(OTSClient client, String tableName,
                                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int offset, int pageSize) {
        Preconditions.checkArgument(offset >= 0, "Offset should not be negative.");
        Preconditions.checkArgument(pageSize > 0, "Page size should be greater than 0.");

        List<Row> rows = new ArrayList<Row>(pageSize);
        int limit = pageSize;
        int skip = offset;

        RowPrimaryKey nextStart = startKey;

        // 若查询的数据量很大，则一次请求有可能不会返回所有的数据，我们需要流式的将所需要的数据全部查出来。
        while (limit > 0 && nextStart != null) {
            // 构造GetRange的查询参数。
            // 注意：startPrimaryKey需要设置为上一次读到的位点，从上一次未读完的地方继续往下读，实现流式的范围查询。
            RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
            criteria.setInclusiveStartPrimaryKey(nextStart);
            criteria.setExclusiveEndPrimaryKey(endKey);

            // 需要设置正确的limit，这里期望读出的数据行数最多就是完整的一页数据以及需要过滤(offset)的数据
            criteria.setLimit(skip + limit);

            GetRangeRequest request = new GetRangeRequest();
            request.setRangeRowQueryCriteria(criteria);
            GetRangeResult response = client.getRange(request);
            for (Row row : response.getRows()) {
                if (skip > 0) {
                    skip--; // 对于offset之前的数据，我们需要过滤掉，采用的策略是读出来后在客户端进行过滤。
                } else {
                    rows.add(row);
                    limit--;
                }
            }

            // 设置下一次查询的起始位点
            nextStart = response.getNextStartPrimaryKey();
        }

        return new Pair<List<Row>, RowPrimaryKey>(rows, nextStart);
    }

    public static <T> List<T> readLimitRows(OTSClient client, Class<T> requiredType, String tableName,
                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int limit) {
        return readLimitRows(client, requiredType, tableName, startKey, endKey, limit, null, null, null);
    }

    public static <T> List<T> readLimitRows(OTSClient client, Class<T> requiredType, String tableName,
                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int limit, Direction direction) {
        return readLimitRows(client, requiredType, tableName, startKey, endKey, limit, direction, null, null);
    }

    public static <T> List<T> readLimitRows(OTSClient client, Class<T> requiredType, String tableName,
                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int limit, Direction direction, List<String> columnsToGet) {
        return readLimitRows(client, requiredType, tableName, startKey, endKey, limit, direction, columnsToGet, null);
    }

    /**
     * 读取指定行数的数据
     *
     * @param client       OTS客户端
     * @param requiredType 返回值类型
     * @param tableName
     * @param startKey     开始key
     * @param endKey       结束key
     * @param limit        读取数据行数
     * @param direction    排序方式
     * @param columnsToGet 返回列
     * @param filter       过滤条件
     * @return 查询结果集
     */
    public static <T> List<T> readLimitRows(OTSClient client, Class<T> requiredType, String tableName,
                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int limit, Direction direction,
                                            List<String> columnsToGet, ColumnCondition filter) {
        Preconditions.checkArgument(requiredType != null, "Class should not be null.");
        Preconditions.checkArgument(limit > 0, "Page size should be greater than 0.");

        RowPrimaryKey nextStart = startKey;

        // 构造GetRange的查询参数。
        // 注意：startPrimaryKey需要设置为上一次读到的位点，从上一次未读完的地方继续往下读，实现流式的范围查询。
        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
        criteria.setInclusiveStartPrimaryKey(nextStart);
        criteria.setExclusiveEndPrimaryKey(endKey);
        // 需要设置正确的limit，这里期望读出的数据行数最多就是完整的一页数据以及需要过滤(offset)的数据
        criteria.setLimit(limit);
        if (direction != null) criteria.setDirection(direction);
        if (columnsToGet != null) criteria.setColumnsToGet(columnsToGet);
        if (filter != null) criteria.setFilter(filter);

        GetRangeRequest request = new GetRangeRequest();
        request.setRangeRowQueryCriteria(criteria);
        GetRangeResult response = client.getRange(request);

        List<T> list = new ArrayList<T>();
        try {
            for (Row row : response.getRows()) {
                T t = requiredType.newInstance();
                for (Map.Entry<String, ColumnValue> entry : row.getColumns().entrySet()) {
                    String key = entry.getKey();
                    Object value = getColumnValue(entry);
                    BeanUtils.setProperty(t, key, value);
                }
                list.add(t);
            }
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
        return list;
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

    public static void descTable(OTSClient otsClient, String tableName) {
        DescribeTableRequest describeTableRequest = new DescribeTableRequest();
        describeTableRequest.setTableName(tableName);
        DescribeTableResult describeTableResult = otsClient.describeTable(describeTableRequest);

        Map<String, PrimaryKeyType> keyTypeMap = describeTableResult.getTableMeta().getPrimaryKey();
        for (Map.Entry<String, PrimaryKeyType> entry : keyTypeMap.entrySet()) {
            System.out.println("\t\t" + entry.getKey() + "-" + entry.getValue().toString());
        }
        ReservedThroughputDetails details = describeTableResult.getReservedThroughputDetails();
        System.out.println("\t\t" + details.getCapacityUnit().getReadCapacityUnit());
        System.out.println("\t\t" + details.getCapacityUnit().getWriteCapacityUnit());
        System.out.println("\t\t" + details.getNumberOfDecreasesToday());
        System.out.println("\t\t" + details.getNumberOfDecreasesToday());
    }
}