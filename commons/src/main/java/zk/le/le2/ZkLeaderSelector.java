package zk.le.le2;

import org.I0Itec.zkclient.IZkDataListener;
import zk.ZkConfig;
import zk.ZkHandler;
import zk.ZkUtil;

import java.util.List;


public class ZkLeaderSelector extends BaseLeaderSelector implements LeaderSelector {

    public ZkLeaderSelector(ZkHandler zkh, String path, String data) {
        super(zkh, path, data);
    }

    @Override
    public void takeLeaderShip() {
        List<String> list = this.getChildren(false);
        setLeader(list);
        String leftNode = getPreNode(list);
        System.out.println("preNode:" + leftNode);
        if (!isLeader() && !leftNode.equals("")) {
            registeWatcher(path + "/" + leftNode);
        }
    }

    private String getPreNode(final List<String> list) {
        int idx = 0;
        idx = list.indexOf(this.leadernode);
        if (idx > 0)
            return (String) list.get(idx - 1);
        else
            return (String) list.get(0);
    }

    @Override
    public void requeue(boolean reCreate) {
        try {
            zkh.deletePath(leadernode);
            if (reCreate) {
                leadernode = zkh.createEphemeralSequential(path + LEADER_PATH, data);
                System.out.println("requene node:" + leadernode + ",data:" + data);
            }
            leader.set(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registeWatcher(String nodepath) {
        final IZkDataListener zkDataListener = new IZkDataListener() {

            @Override
            public void handleDataChange(String s, Object obj) throws Exception {
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
//				if(!zkh.createPathExists(s)){
                System.out.println(s + " is in" + "path:" + path + data);
                List<String> list = getChildren(false);
                setLeader(list);
//				}
            }
        };
        zkh.subscribeDataChanges(nodepath, zkDataListener);
    }

    public static void main(String[] args) {
        ZkConfig zkconfig = ZkUtil.loadZkConfig();
        ZkHandler zkh = new ZkHandler(zkconfig);
        String path = "/root/job";
        zkh.createPersistentPath("/root/job", "leadership");
        LeaderSelector zls = new ZkLeaderSelector(zkh, path, "10.1.200.80");
        LeaderSelector zls1 = new ZkLeaderSelector(zkh, path, "10.1.200.81");
        LeaderSelector zls2 = new ZkLeaderSelector(zkh, path, "10.1.200.82");
        zls.takeLeaderShip();
        zls1.takeLeaderShip();
        zls2.takeLeaderShip();
        System.out.println("80 is " + zls.isLeader());
        System.out.println("81 is " + zls1.isLeader());
        System.out.println("82 is " + zls2.isLeader());
        if (zls2.isLeader())
            zls2.requeue(true);
        if (zls.isLeader())
            zls.requeue(true);
        if (zls1.isLeader())
            zls1.requeue(true);
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("80 is " + zls.isLeader());
        System.out.println("81 is " + zls1.isLeader());
        System.out.println("82 is " + zls2.isLeader());
//		zls.requeue(false);
//		zls1.requeue(false);
//		zls2.requeue(false);
    }
}
