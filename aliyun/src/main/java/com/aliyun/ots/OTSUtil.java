/**
 * Copyright (C) 2014 北京学信科技有限公司
 *
 * @className:com.xuexin.util.OTSUtil
 * @version:v1.0.0
 * @author:李大鹏 Modification History:
 * Date         Author      Version     Description
 * -----------------------------------------------------------------
 * 2014-4-9       李大鹏                        v1.0.0        create
 */
package com.aliyun.ots;

import com.aliyun.openservices.ots.OTSException;
import com.aliyun.openservices.ots.model.*;
import com.aliyun.openservices.ots.utils.Preconditions;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.openservices.ots.OTSClient;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OTSUtil {
    private static final Log logger = LogFactory.getLog(OTSUtil.class);
    private static OTSClient clientIns;


    /***
     * 批处理一次最大数据量
     */
    public static final int BATCH_THRESHOLD = 200;

    // fixme 上线时去掉
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
     * 保存公众消息浏览记录
     *
     * @param msgID
     * @param username
     * @version:v1.0
     * @author:李大鹏
     * @date:2014-5-19 下午3:25:14
     */
    public static void saveGzMsgViewHistory(Long msgID, String username) {
        try {
            RowPutChange rowChange = new RowPutChange("gzMsgViewHistory");
            RowPrimaryKey primaryKey = new RowPrimaryKey();
            primaryKey.addPrimaryKeyColumn("msgID",
                    PrimaryKeyValue.fromLong(msgID));
            primaryKey.addPrimaryKeyColumn("username",
                    PrimaryKeyValue.fromString(username));
            rowChange.setPrimaryKey(primaryKey);
            rowChange.addAttributeColumn("createDate",
                    ColumnValue.fromLong(System.currentTimeMillis()));
            PutRowRequest request = new PutRowRequest();
            request.setRowChange(rowChange);
            OTSClient client = getClient();
            client.putRow(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 方法描述 保存素材消息浏览记录
     *
     * @param msgID
     * @param username
     * @version:v1.0
     * @author:zxl
     * @date:2015-5-19 上午9:34:08
     */
    public static void saveGzMaterialViewHistory(Long msgID, String username) {
        try {
            RowPutChange rowChange = new RowPutChange("gzMaterialViewHistory");
            RowPrimaryKey primaryKey = new RowPrimaryKey();
            primaryKey.addPrimaryKeyColumn("msgID",
                    PrimaryKeyValue.fromLong(msgID));
            primaryKey.addPrimaryKeyColumn("username",
                    PrimaryKeyValue.fromString(username));
            rowChange.setPrimaryKey(primaryKey);
            rowChange.addAttributeColumn("createDate",
                    ColumnValue.fromLong(System.currentTimeMillis()));
            PutRowRequest request = new PutRowRequest();
            request.setRowChange(rowChange);
            OTSClient client = getClient();
            client.putRow(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 是否已经阅读了公众消息
     *
     * @param msgID
     * @param username
     * @return
     * @version:v1.0
     * @author:李大鹏
     * @date:2014-5-19 下午5:02:31
     */
    public static boolean isViewGzMsg(Long msgID, String username) {
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(
                "gzMsgViewHistory");
        RowPrimaryKey primaryKey = new RowPrimaryKey();
        primaryKey
                .addPrimaryKeyColumn("msgID", PrimaryKeyValue.fromLong(msgID));
        primaryKey.addPrimaryKeyColumn("username",
                PrimaryKeyValue.fromString(username));
        criteria.setPrimaryKey(primaryKey);
        Row row = null;
        try {
            OTSClient client = getClient();
            GetRowRequest request = new GetRowRequest();
            request.setRowQueryCriteria(criteria);
            GetRowResult result = client.getRow(request);
            row = result.getRow();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (row != null && !row.getColumns().isEmpty())
            return true;
        else
            return false;
    }

    /**
     * 是否已经阅读了素材消息 方法描述
     *
     * @param msgID
     * @param username
     * @return
     * @version:v1.0
     * @author:zxl
     * @date:2015-5-19 上午9:34:33
     */
    public static boolean isViewGzMaterial(Long msgID, String username) {
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(
                "gzMaterialViewHistory");
        RowPrimaryKey primaryKey = new RowPrimaryKey();
        primaryKey
                .addPrimaryKeyColumn("msgID", PrimaryKeyValue.fromLong(msgID));
        primaryKey.addPrimaryKeyColumn("username",
                PrimaryKeyValue.fromString(username));
        criteria.setPrimaryKey(primaryKey);
        Row row = null;
        try {
            OTSClient client = getClient();
            GetRowRequest request = new GetRowRequest();
            request.setRowQueryCriteria(criteria);
            GetRowResult result = client.getRow(request);
            row = result.getRow();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (row != null && !row.getColumns().isEmpty())
            return true;
        else
            return false;
    }

    /**
     * 保存公众消息浏览记录(IP排重)
     *
     * @param msgID
     * @param IP
     * @version:v2.1
     * @author:高青柏
     * @date:2014-9-16 下午2:02:13
     */
    public static void saveGzMsgReaderNum(Long msgID, String IP) {
        try {
            RowPutChange rowChange = new RowPutChange("gzMsgReaderNum");
            RowPrimaryKey primaryKey = new RowPrimaryKey();
            primaryKey.addPrimaryKeyColumn("msgID",
                    PrimaryKeyValue.fromLong(msgID));
            primaryKey
                    .addPrimaryKeyColumn("IP", PrimaryKeyValue.fromString(IP));
            rowChange.setPrimaryKey(primaryKey);
            rowChange.addAttributeColumn("createDate",
                    ColumnValue.fromLong(System.currentTimeMillis()));
            PutRowRequest request = new PutRowRequest();
            request.setRowChange(rowChange);
            OTSClient client = getClient();
            client.putRow(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 是否已经阅读了公众消息（根据IP判断）
     *
     * @param msgID
     * @param IP
     * @return
     * @version:v2.1
     * @author:高青柏
     * @date:2014-9-16 下午2:04:26
     */
    public static boolean isReaderGzMsg(Long msgID, String IP) {
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(
                "gzMsgReaderNum");
        RowPrimaryKey primaryKey = new RowPrimaryKey();
        primaryKey
                .addPrimaryKeyColumn("msgID", PrimaryKeyValue.fromLong(msgID));
        primaryKey.addPrimaryKeyColumn("IP", PrimaryKeyValue.fromString(IP));
        criteria.setPrimaryKey(primaryKey);
        Row row = null;
        try {
            OTSClient client = getClient();
            GetRowRequest request = new GetRowRequest();
            request.setRowQueryCriteria(criteria);
            GetRowResult result = client.getRow(request);
            row = result.getRow();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        if (row != null && !row.getColumns().isEmpty())
            return true;
        else
            return false;
    }


//    /**
//     * 范围查询指定范围内的数据，返回指定页数大小的数据，并能根据offset跳过部分行。
//     *
//     * @param client
//     * @param tableName
//     * @param startKey
//     * @param endKey
//     * @param offset
//     * @param pageSize
//     * @return
//     */
//    public static List<Row> readByPage(OTSClient client, String tableName,
//                                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int offset, int pageSize) {
//        Preconditions.checkArgument(offset >= 0, "Offset should not be negative.");
//        Preconditions.checkArgument(pageSize > 0, "Page size should be greater than 0.");
//
//        List<Row> rows = new ArrayList<Row>(pageSize);
//        int limit = pageSize;
//        int skip = offset;
//
//        RowPrimaryKey nextStart = startKey;
//
//        // 若查询的数据量很大，则一次请求有可能不会返回所有的数据，我们需要流式的将所需要的数据全部查出来。
//        while (limit > 0 && nextStart != null) {
//            // 构造GetRange的查询参数。
//            // 注意：startPrimaryKey需要设置为上一次读到的位点，从上一次未读完的地方继续往下读，实现流式的范围查询。
//            RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
//            criteria.setInclusiveStartPrimaryKey(nextStart);
//            criteria.setExclusiveEndPrimaryKey(endKey);
//
//            // 需要设置正确的limit，这里期望读出的数据行数最多就是完整的一页数据以及需要过滤(offset)的数据
//            criteria.setLimit(skip + limit);
//
//            GetRangeRequest request = new GetRangeRequest();
//            request.setRangeRowQueryCriteria(criteria);
//            GetRangeResult response = client.getRange(request);
//            for (Row row : response.getRows()) {
//                if (skip > 0) {
//                    skip--; // 对于offset之前的数据，我们需要过滤掉，采用的策略是读出来后在客户端进行过滤。
//                } else {
//                    rows.add(row);
//                    limit--;
//                }
//            }
//
//            // 设置下一次查询的起始位点
//            nextStart = response.getNextStartPrimaryKey();
//        }
//
//        return rows;
//    }
//
//    @Deprecated
//    public static <T> List<T> readLimitRows(OTSClient client, Class<T> requiredType, String tableName,
//                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int limit) {
//        return readLimitRows(client, requiredType, tableName, startKey, endKey, limit, null, null, null);
//    }
//
//    @Deprecated
//    public static <T> List<T> readLimitRows(OTSClient client, Class<T> requiredType, String tableName,
//                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int limit, Direction direction) {
//        return readLimitRows(client, requiredType, tableName, startKey, endKey, limit, direction, null, null);
//    }
//
//    @Deprecated
//    public static <T> List<T> readLimitRows(OTSClient client, Class<T> requiredType, String tableName,
//                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int limit, Direction direction, List<String> columnsToGet) {
//        return readLimitRows(client, requiredType, tableName, startKey, endKey, limit, direction, columnsToGet, null);
//    }

//    /**
//     * 读取指定行数的数据
//     *
//     *
//     * @param client       OTS客户端
//     * @param requiredType 返回值类型
//     * @param tableName
//     * @param startKey     开始key
//     * @param endKey       结束key
//     * @param limit        读取数据行数
//     * @param direction    排序方式
//     * @param columnsToGet 返回列
//     * @param filter       过滤条件
//     * @return 查询结果集
//     * @deprecated
//     * replaced by <code>OTSUtil.readLimitRows()</code>.
//     *
//     */
//    @Deprecated
//    public static <T> List<T> readLimitRows(OTSClient client, Class<T> requiredType, String tableName,
//                                            RowPrimaryKey startKey, RowPrimaryKey endKey, int limit, Direction direction,
//                                            List<String> columnsToGet, ColumnCondition filter) {
//        Preconditions.checkArgument(requiredType != null, "Class should not be null.");
//        Preconditions.checkArgument(limit > 0, "Page size should be greater than 0.");
//
//        RowPrimaryKey nextStart = startKey;
//
//        // 构造GetRange的查询参数。
//        // 注意：startPrimaryKey需要设置为上一次读到的位点，从上一次未读完的地方继续往下读，实现流式的范围查询。
//        RangeRowQueryCriteria criteria = new RangeRowQueryCriteria(tableName);
//        criteria.setInclusiveStartPrimaryKey(nextStart);
//        criteria.setExclusiveEndPrimaryKey(endKey);
//        // 需要设置正确的limit，这里期望读出的数据行数最多就是完整的一页数据以及需要过滤(offset)的数据
//        criteria.setLimit(limit);
//        if (direction != null) criteria.setDirection(direction);
//        if (columnsToGet != null) criteria.setColumnsToGet(columnsToGet);
//        if (filter != null) criteria.setFilter(filter);
//
//        GetRangeRequest request = new GetRangeRequest();
//        request.setRangeRowQueryCriteria(criteria);
//        GetRangeResult response = client.getRange(request);
//
//        List<T> list = new ArrayList<T>();
//        try {
//            for (Row row : response.getRows()) {
//                T t = requiredType.newInstance();
//                for (Map.Entry<String, ColumnValue> entry : row.getColumns().entrySet()) {
//                    String key = entry.getKey();
//                    Object value = getColumnValue(entry);
//                    BeanUtils.setProperty(t, key, value);
//                }
//                list.add(t);
//            }
//        } catch (InstantiationException e) {
//
//        } catch (IllegalAccessException e) {
//
//        } catch (InvocationTargetException e) {
//
//        }
//        return list;
//    }


    public static Object getColumnValue(Map.Entry<String, ColumnValue> entry) {
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


    /**
     * @param table
     */
    public static void createTable(OTSTable table) {
        TableMeta tableMeta = new TableMeta(table.getTableName());
        for (OTSTable.OTSPrimaryKey otsPrimaryKey : table.getPrimaryKey()) {
            tableMeta.addPrimaryKeyColumn(otsPrimaryKey.getKey(), otsPrimaryKey.getKeyType());
        }

        CreateTableRequest request = new CreateTableRequest();
        request.setTableMeta(tableMeta);
        request.setReservedThroughput(table.getCapacityUnit());

        try {
            OTSClient client = getClient();
            client.createTable(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    public static void deleteTable(OTSTable table) {
        deleteTable(table.getTableName());
    }

    private static void deleteTable(String tableName) {
        DeleteTableRequest request = new DeleteTableRequest();
        request.setTableName(tableName);

        try {
            OTSClient client = getClient();
            client.deleteTable(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void descTable(String tableName) {
        DescribeTableRequest describeTableRequest = new DescribeTableRequest();
        describeTableRequest.setTableName(tableName);


        try {
            OTSClient client = getClient();
            DescribeTableResult describeTableResult = client.describeTable(describeTableRequest);

            Map<String, PrimaryKeyType> keyTypeMap = describeTableResult.getTableMeta().getPrimaryKey();
            for (Map.Entry<String, PrimaryKeyType> entry : keyTypeMap.entrySet()) {
                System.out.println("\t\t" + entry.getKey() + "-" + entry.getValue().toString());
            }
            ReservedThroughputDetails details = describeTableResult.getReservedThroughputDetails();
            System.out.println("\t\t" + details.getCapacityUnit().getReadCapacityUnit());
            System.out.println("\t\t" + details.getCapacityUnit().getWriteCapacityUnit());
            System.out.println("\t\t" + details.getNumberOfDecreasesToday());
            System.out.println("\t\t" + details.getNumberOfDecreasesToday());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 更新table.注意：只能更新table的 ReservedThroughputChange信息。
     *
     * @param table
     */
    private static void updateTable(OTSTable table) {
        UpdateTableRequest request = new UpdateTableRequest();
        request.setTableName(table.getTableName());

        ReservedThroughputChange reservedThroughputChange = new ReservedThroughputChange();
        reservedThroughputChange.setReadCapacityUnit(table.getReadCapacityUnit());
        reservedThroughputChange.setWriteCapacityUnit(table.getWriteCapacityUnit());
        request.setReservedThroughputChange(reservedThroughputChange);

        try {
            OTSClient client = getClient();
            client.updateTable(request);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 批量添加记录，每次最大添加  OTSUtil.BATCH_THRESHOLD
     *
     * @param rowChanges
     * @return
     * @throws OTSException
     */
    public static BatchWriteRowResult batchRowRequest(List<RowPutChange> rowChanges)
            throws OTSException {
        return batchRowRequest(rowChanges, null);
    }

    /**
     * 批量记录操作
     *
     * @param rowChanges
     * @return
     * @throws OTSException
     */
    private static BatchWriteRowResult batchRowRequest(List<RowPutChange> rowChanges, Condition condition)
            throws OTSException {
        Preconditions.checkArgument(rowChanges.size() <= BATCH_THRESHOLD, "The number of rowChanges columns must be in range: [1, 200]");
        BatchWriteRowRequest batchWriteRowRequest = new BatchWriteRowRequest();

        for (RowPutChange rowPutChange : rowChanges) {
            if (condition != null) rowPutChange.setCondition(condition);
            batchWriteRowRequest.addRowPutChange(rowPutChange);
        }

        try {
            OTSClient client = getClient();
            return client.batchWriteRow(batchWriteRowRequest);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;

    }

    /**
     * @param client
     * @param otsQuery
     * @param <T>
     * @return
     */
    public static <T> List<T> readLimitRows(OTSClient client, OTSQuery<T> otsQuery) {
        Preconditions.checkArgument(otsQuery.getRequiredType() != null, "Class should not be null.");
        Preconditions.checkArgument(otsQuery.getLimit() > 0, "Page size should be greater than 0.");

        GetRangeRequest request = new GetRangeRequest();
        request.setRangeRowQueryCriteria(otsQuery.getRangeRowQueryCriteria());
        GetRangeResult response = client.getRange(request);

        List<T> list = new ArrayList<T>();
        try {
            for (Row row : response.getRows()) {
                T t = otsQuery.getRequiredType().newInstance();
                for (Map.Entry<String, ColumnValue> entry : row.getColumns().entrySet()) {
                    String key = entry.getKey();
                    Object value = getColumnValue(entry);
                    System.out.println(key + " : " + entry);
                    BeanUtils.setProperty(t, key, value);
                }
                list.add(t);
            }

            // FIXME 处理异常
        } catch (InstantiationException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
        return list;

    }

    public static <T> List<T> readLimitRows(OTSQuery<T> otsQuery) {
        return readLimitRows(getClient(), otsQuery);

    }

    public static boolean putRowFromPojo(OTSTable table, Object object) {
        List<Object> list = new ArrayList<Object>(1);
        list.add(object);
        BatchWriteRowResult batchWriteRowResult = putRowsFromPojo(table, list);
        return batchWriteRowResult.getPutRowStatus().get(table.getTableName()).get(0).isSucceed();
    }

    /**
     * @param table
     * @param list
     * @return
     * @throws OTSException
     */
    public static BatchWriteRowResult putRowsFromPojo(OTSTable table, List<Object> list)
            throws OTSException {
        List<RowPutChange> rows = new ArrayList<RowPutChange>();
        for (Object pojo : list) {
            RowPutChange rowChange = new RowPutChange(table.getTableName());

            RowPrimaryKey primaryKey = assemblyPrimaryKey(table, pojo);
            rowChange.setPrimaryKey(primaryKey);

            assemblyAttributeColumns(table, pojo, rowChange);

            rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_NOT_EXIST));
            rows.add(rowChange);
        }

        BatchWriteRowResult batchWriteRowResult = OTSUtil.batchRowRequest(rows);
        return batchWriteRowResult;
    }

    private static void assemblyAttributeColumns(OTSTable table, Object pojo, RowPutChange rowChange) {
        for (OTSTable.OTSColumn column : table.getColumns()) {
            try {
//                rowChange.addAttributeColumn(column.getKey(), buildColumnValue(column, BeanUtil.getPropertyValue(column.getKey(), redis.pojo)));
            } catch (Exception e) {
                //fixme why 忽略属性不存在例外
            }
        }
    }


    private static void assemblyAttributeColumns(OTSTable table, Object pojo, RowUpdateChange rowChange) {
        for (OTSTable.OTSColumn column : table.getColumns()) {
            try {
//                rowChange.addAttributeColumn(column.getKey(), buildColumnValue(column, BeanUtil.getPropertyValue(column.getKey(), redis.pojo)));
            } catch (Exception e) {
                //fixme why 忽略属性不存在例外
            }
        }
    }

    /**
     * @param table
     * @param list
     * @return
     * @throws OTSException
     * @Deprecated 推荐使用 OTSUtil.putRowsFromPojo(OTSTable table, List<Object> list)
     */
    @Deprecated
    public static BatchWriteRowResult putRows(OTSTable table, List<Map<String, Object>> list)
            throws OTSException {
        List<RowPutChange> rows = new ArrayList<RowPutChange>();
        for (Map<String, Object> map : list) {
            RowPutChange rowChange = new RowPutChange(table.getTableName());
            RowPrimaryKey primaryKey = new RowPrimaryKey();

            for (OTSTable.OTSPrimaryKey otsPrimaryKey : table.getPrimaryKey()) {
                primaryKey.addPrimaryKeyColumn(otsPrimaryKey.getKey(), buildPrimaryKeyValue(otsPrimaryKey.getKeyType(), map.get(otsPrimaryKey.getKey())));
            }
            rowChange.setPrimaryKey(primaryKey);

            for (OTSTable.OTSColumn otsColumn : table.getColumns())
                rowChange.addAttributeColumn(otsColumn.getKey(), buildColumnValue(otsColumn, map.get(otsColumn.getKey())));

            rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_NOT_EXIST));
            rows.add(rowChange);
        }

        BatchWriteRowResult batchWriteRowResult = OTSUtil.batchRowRequest(rows);
        return batchWriteRowResult;
    }

    private static ColumnValue buildColumnValue(OTSTable.OTSColumn otsColumn, Object o) {
        ColumnValue value = null;
        ColumnType ct = otsColumn.getKeyType();
        if (ct == ColumnType.STRING) {
            value = ColumnValue.fromString((String) o);
        } else if (ct == ColumnType.INTEGER) {
            value = ColumnValue.fromLong((Long) o);
        } else if (ct == ColumnType.BOOLEAN) {
            value = ColumnValue.fromBoolean((Boolean) o);
        } else if (ct == ColumnType.BINARY) {
            value = ColumnValue.fromBinary((byte[]) o);
        } else if (ct == ColumnType.DOUBLE) {
            value = ColumnValue.fromDouble((Double) o);
        }
        return value;
    }


    private static PrimaryKeyValue buildPrimaryKeyValue(PrimaryKeyType primaryKeyType, Object o) {
        PrimaryKeyValue primaryKeyValue = null;
        if (primaryKeyType == PrimaryKeyType.INTEGER) {
            primaryKeyValue = PrimaryKeyValue.fromLong((Long) o);
        } else if (primaryKeyType == PrimaryKeyType.STRING) {
            primaryKeyValue = PrimaryKeyValue.fromString((String) o);
        }
        return primaryKeyValue;
    }

    public static boolean deleteRow(OTSTable table, Object pojo) {

        RowDeleteChange rowChange = new RowDeleteChange(table.getTableName());
        RowPrimaryKey primaryKeys = assemblyPrimaryKey(table, pojo);
        rowChange.setPrimaryKey(primaryKeys);
        DeleteRowRequest request = new DeleteRowRequest();
        request.setRowChange(rowChange);
        rowChange.setCondition(new Condition(RowExistenceExpectation.EXPECT_EXIST));

        try {
            OTSClient client = getClient();
            DeleteRowResult result = client.deleteRow(request);
            return true;
        } catch (OTSException e) {
            return false;
        }
    }

    public static boolean updateRow(OTSTable table, Object pojo) {

        RowUpdateChange rowChange = new RowUpdateChange(table.getTableName());
        RowPrimaryKey primaryKeys = assemblyPrimaryKey(table, pojo);
        rowChange.setPrimaryKey(primaryKeys);

        // 更新以下三列的值
        assemblyAttributeColumns(table, pojo, rowChange);
        // 删除mobile和age信息
//        rowChange.deleteAttributeColumn(COLUMN_MOBILE_NAME);
//        rowChange.deleteAttributeColumn(COLUMN_AGE_NAME);

        Condition cond = new Condition(RowExistenceExpectation.EXPECT_EXIST);
        rowChange.setCondition(cond);

        UpdateRowRequest request = new UpdateRowRequest();
        request.setRowChange(rowChange);

        try {
            OTSClient client = getClient();
            UpdateRowResult result = client.updateRow(request);
            return true;
        } catch (OTSException e) {
            return false;
        }
    }

    private static RowPrimaryKey assemblyPrimaryKey(OTSTable table, Object pojo) {
        RowPrimaryKey primaryKeys = new RowPrimaryKey();
        for (OTSTable.OTSPrimaryKey otsPrimaryKey : table.getPrimaryKey()) {
//            primaryKeys.addPrimaryKeyColumn(otsPrimaryKey.getKey(), buildPrimaryKeyValue(otsPrimaryKey.getKeyType(), BeanUtil.getPropertyValue(otsPrimaryKey.getKey(), redis.pojo)));
        }
        return primaryKeys;
    }
}
