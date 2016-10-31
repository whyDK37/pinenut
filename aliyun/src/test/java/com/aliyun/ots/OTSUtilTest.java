package com.aliyun.ots;

import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.model.*;
import org.junit.Test;

import java.util.Map;

/**
 * Created by whydk on 2016/6/24.
 */
public class OTSUtilTest {

    @Test
    public void client() {
        OTSClient client = OTSUtil.getClient();

        String uri = client.getEndpoint();
        System.out.println(uri);

        ListTableResult listTableResult = client.listTable();
        System.out.println("tables:");
        for (String tableName : listTableResult.getTableNames()) {
            System.out.println("\t" + tableName);

            descTable(client, tableName);
        }

    }

    private void descTable(OTSClient otsClient, String tableName) {
        DescribeTableRequest describeTableRequest = new DescribeTableRequest();
        describeTableRequest.setTableName(tableName);
        DescribeTableResult describeTableResult = otsClient.describeTable(describeTableRequest);

        Map<String,PrimaryKeyType> keyTypeMap = describeTableResult.getTableMeta().getPrimaryKey();
        for (Map.Entry<String, PrimaryKeyType> entry: keyTypeMap.entrySet()){
            System.out.println("\t\t" + entry.getKey() + "-" + entry.getValue().toString());
        }
        ReservedThroughputDetails details = describeTableResult.getReservedThroughputDetails();
        System.out.println(details.getNumberOfDecreasesToday());
        System.out.println(details.getNumberOfDecreasesToday());
    }

    @Test
    public void getRowTest(){
        String tableName = "cn_loginlog";
        OTSClient otsClient = OTSUtil.getClient();
        GetRangeRequest getRangeRequest = new GetRangeRequest();

        otsClient.getRange(getRangeRequest);
    }
}
