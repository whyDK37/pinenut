package zk.le.le2;


public interface LeaderSelector {

    void takeLeaderShip();

    void requeue(boolean reCreate);

    boolean isLeader();

}
