package zk.le.le2;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import zk.ZkConfig;
import zk.ZkHandler;
import zk.ZkUtil;

import java.util.List;


public class SimpleLeaderSelector extends BaseLeaderSelector implements LeaderSelector {

  public SimpleLeaderSelector(final ZkHandler zkh, final String path, final String data) {
    super(zkh, path, data);
  }

  public static void main(String[] args) {
    ZkConfig zkconfig = ZkUtil.loadZkConfig();
    ZkHandler zkh = new ZkHandler(zkconfig);
    ZkClient zkClient = zkh.getZkClient();
    String path = "/root/job";
    zkh.createEphemeralPath(path, "leadership");
    LeaderSelector zls = new SimpleLeaderSelector(zkh, path, "80");
    LeaderSelector zls2 = new SimpleLeaderSelector(zkh, path, "82");
    zls.takeLeaderShip();
    zls2.takeLeaderShip();
    System.out.println("80 is " + zls.isLeader());
    System.out.println("82 is " + zls2.isLeader());
    if (zls2.isLeader())
      zls2.requeue(true);
    if (zls.isLeader())
      zls.requeue(true);
    try {
      Thread.sleep(5 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("80 is " + zls.isLeader());
    System.out.println("82 is " + zls2.isLeader());
//        zls2.requeue(false);
//        zls.requeue(false);

  }

  @Override
  public void requeue(boolean reCreate) {
    try {
      zkh.deletePath(leadernode);
      leader.set(false);
      if (reCreate) {
        leadernode = zkh.createEphemeralSequential(path + LEADER_PATH, data);
        System.out.println("requene node:" + leadernode + ",data:" + data);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * take simple leadership
   *
   * @return
   */
  @Override
  public void takeLeaderShip() {
    List<String> list = getChildren(true);
    setLeader(list);
  }

  @Override
  public void registeWatcher(String path) {
    final IZkChildListener zkChildListener = new IZkChildListener() {
      @Override
      public void handleChildChange(String s, List<String> list)
              throws Exception {
        setLeader(list);
      }
    };
    zkh.subscribeChildChanges(path, zkChildListener);
  }
}
