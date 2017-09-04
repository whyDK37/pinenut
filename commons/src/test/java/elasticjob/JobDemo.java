package elasticjob;

import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

public class JobDemo {

  public static void main(String[] args) {
    new JobScheduler(createRegistryCenter(), createJobConfiguration()).init();
  }

  private static CoordinatorRegistryCenter createRegistryCenter() {
    CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("zk_host:2181", "elastic-job-demo"));
    regCenter.init();
    return regCenter;
  }

  private static LiteJobConfiguration createJobConfiguration() {
    // 创建作业配置
    System.out.println("创建作业配置");
    return null;
  }
}