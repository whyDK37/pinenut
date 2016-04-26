/**
 * If the number of clients is large, it causes a spike on the number of operations that ZooKeeper servers have to process. To avoid the herd effect, it is sufficient to watch for the next znode down on the sequence of znodes. If a client receives a notification that the znode it is watching is gone, then it becomes the new leader in the case that there is no smaller znode. Note that this avoids the herd effect by not having all clients watching the same znode.
 * <p/>
 * Here's the pseudo code:
 * <p/>
 * Let ELECTION be a path of choice of the application. To volunteer to be a leader:
 * <p/>
 * 1,Create znode z with path "ELECTION/n_" with both SEQUENCE and EPHEMERAL flags;
 * 2,Let C be the children of "ELECTION", and i be the sequence number of z;
 * 3,Watch for changes on "ELECTION/n_j", where j is the largest sequence number such that j < i and n_j is a znode in C;
 * Upon receiving a notification of znode deletion:
 * <p/>
 * 1,Let C be the new set of children of ELECTION;
 * 2,If z is the smallest node in C, then execute leader procedure;
 * 3,Otherwise, watch for changes on "ELECTION/n_j", where j is the largest sequence number such that j < i and n_j is a znode in C;
 * Created by drug on 2016/4/26.
 */
package zk.le.enhance;