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
package aliyun.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.model.ColumnValue;
import com.aliyun.openservices.ots.model.GetRowRequest;
import com.aliyun.openservices.ots.model.GetRowResult;
import com.aliyun.openservices.ots.model.PrimaryKeyValue;
import com.aliyun.openservices.ots.model.PutRowRequest;
import com.aliyun.openservices.ots.model.Row;
import com.aliyun.openservices.ots.model.RowPrimaryKey;
import com.aliyun.openservices.ots.model.RowPutChange;
import com.aliyun.openservices.ots.model.SingleRowQueryCriteria;

public class OTSUtil {
    private static final Log logger = LogFactory.getLog(OTSUtil.class);
    //    private static final Logger Log = LoggerFactory.getLogger(OTSUtil.class);
    private static OTSClient clientIns;
    public static final String endpoint = "http://xuexin-dev.cn-hangzhou.ots.aliyuncs.com";
    public static final String ALIYUN_ACCESS_ID = "8pO2VD2xEYk0vpGF";
    public static final String ALIYUN_ACCESS_KEY = "2pDJZvDZN05TBeeLZZxGiIM2HOW1Yp";
    public static final String intance = "xuexin-dev";

    public static OTSClient getClient() {
        if (clientIns == null) {
//            String intance = PropertyUtils.getInstance().getProp(
//                    "xuexin.ots.instance");
//            String endpoint = "http://"
//                    + intance
//                    + "."
//                    + PropertyUtils.getInstance()
//                            .getProp("xuexin.ots.endpoint");

            clientIns = new OTSClient(endpoint,
                    ALIYUN_ACCESS_ID,
                    ALIYUN_ACCESS_KEY, intance);
        }
        return clientIns;
    }

    /**
     * 保存公众消息浏览记录
     *
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
     *
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

}
