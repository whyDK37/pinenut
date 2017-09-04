package zk.le.le2;

import zk.ZkHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public abstract class BaseLeaderSelector {
  static final String LEADER_PATH = "/leader-";
  final ZkHandler zkh;
  String path;
  String data;
  String leadernode;
  AtomicBoolean leader = new AtomicBoolean(false);

  public BaseLeaderSelector(final ZkHandler zkh, final String path, final String data) {
    this.zkh = zkh;
    this.path = path;
    this.data = data;
    leadernode = zkh.createEphemeralSequential(path + LEADER_PATH, data);
    System.out.println("init node:" + leadernode + ",data:" + data);
  }

  public boolean isLeader() {
    return leader.get();
  }

  public void setLeader(List<String> list) {
    sortChild(list);
    System.out.println("childnode size:" + list.toString());
    if (list == null || list.size() == 0) {
      leader.set(false);
    } else {
      String node2 = zkh.readData(path + "/" + list.get(0));
      if (node2.endsWith(data)) {//
        leader.set(true);
      } else {
        leader.set(false);
      }
    }
  }

  public List<String> getChildren(boolean toWatcher) {
    List<String> list = zkh.getChildrenMaybeNull(path);
    if (toWatcher)
      registeWatcher(path);
    return list;
  }

  public abstract void registeWatcher(String path);

  private void sortChild(List<String> list) {
    Collections.sort(list, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        int start1 = Integer.valueOf(o1.substring(7));
        int start2 = Integer.valueOf(o2.substring(7));
        if (start1 == start2) {
          return 0;
        } else if (start1 > start1) {
          return 1;
        } else {
          return -1;
        }
      }
    });
    Collections.sort(list);
  }
}
