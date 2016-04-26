package zk.example;

import org.I0Itec.zkclient.DataUpdater;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;
import zk.ZkConfig;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by drug on 2016/4/26.
 */
public class SimpleTest {
    public static void main(String[] args) {
        ZkClient zkClient = buildZkClient();
        //
        subscribe(zkClient);
        //
        if (zkClient.exists("/root/data")) {
            zkClient.delete("/root/data");
        }
        zkClient.createPersistent("/root/data", "my data");
        zkClient.updateDataSerialized("/root/data", new DataUpdater<String>() {
            @Override
            public String update(String currentData) {
                return "my data updated";
            }
        });
        Stat stat = zkClient.writeDataReturnStat("/root/data", "return with stat", 1);
        System.out.println("stat : " + stat);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ZkClient buildZkClient() {
        ZkConfig zkc = new ZkConfig();
        zkc.setZkConnect("127.0.0.1:2181,127.0.0.1:2182");
        zkc.zkSessionTimeoutMs = 30000;
        zkc.zkConnectionTimeoutMs = 40000;
        zkc.zkSyncTimeMs = 5000;

        ZkClient zkClient = new ZkClient(zkc.getZkConnect(), zkc.getZkSessionTimeoutMs(),
                zkc.getZkConnectionTimeoutMs());

        if (!zkClient.exists("/root")) {
            zkClient.createPersistent("/root");
        }
        return zkClient;
    }

    private static void subscribe(ZkClient zkClient) {
        zkClient.subscribeChildChanges("/root", new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                System.out.println("child changes : " + parentPath + " " + currentChilds.toString());
            }
        });

        zkClient.subscribeDataChanges("/root/data", new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("data change " + dataPath + " " + data);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("handleDataDeleted " + dataPath + " ");
            }
        });
    }
}
