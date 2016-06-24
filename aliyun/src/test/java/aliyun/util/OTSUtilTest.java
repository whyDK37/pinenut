package aliyun.util;

import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.model.DescribeTableRequest;
import com.aliyun.openservices.ots.model.DescribeTableResult;
import com.aliyun.openservices.ots.model.ListTableResult;
import com.aliyun.openservices.ots.model.PrimaryKeyType;
import org.junit.Test;

import java.net.URI;
import java.util.Map;

/**
 * Created by whydk on 2016/6/24.
 */
public class OTSUtilTest {

    @Test
    public void client() {
//        String intance = PropertyUtils.getInstance().getProp(
//                "xuexin.ots.instance");
//        String endpoint = "http://"
//                + intance
//                + "."
//                + PropertyUtils.getInstance()
//                .getProp("xuexin.ots.endpoint");
//        System.out.println(endpoint);
//        clientIns = new OTSClient(endpoint,
//                XuexinConstants.ALIYUN_ACCESS_ID,
//                XuexinConstants.ALIYUN_ACCESS_KEY, intance);

        OTSClient otsClient = OTSUtil.getClient();

        URI uri = otsClient.getEndpoint();
        System.out.println(uri.toString());

        ListTableResult listTableResult = otsClient.listTable();
        System.out.println("tables:");
        for (String tableName : listTableResult.getTableNames()) {
            System.out.println("\t" + tableName);

            descTable(otsClient, tableName);
        }

    }

    private void descTable(OTSClient otsClient, String tableName) {
        DescribeTableRequest describeTableRequest = new DescribeTableRequest();
        describeTableRequest.setTableName(tableName);
        DescribeTableResult describeTableResult = otsClient.describeTable(describeTableRequest);

        Map<String,PrimaryKeyType> keyTypeMap = describeTableResult.getTableMeta().getPrimaryKey();
        for (Map.Entry<String, PrimaryKeyType> entry: keyTypeMap.entrySet()){
            System.out.println("\t\t"+entry.getKey() +"-"+entry.getValue().toString());
        }
    }
}
