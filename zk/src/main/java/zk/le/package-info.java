/**
 * Leader Election
 * 每个server监控同一目录，如 root = "/ELECTION"。并且分别在root下创建一个带有进程标示的子节点，
 * 类型为CreateMode.EPHEMERAL_SEQUENTIAL 瞬时序列，当有server增加或减少的时候，所有server都会收到监听事件。
 *
 * 选举的算法是：通过获取 root 下所有的 children，计算出 sequential最小的节点，把这个节点对应的server当作leader。
 *
 * Created by why on 2016/4/20.
 */
package zk.le;