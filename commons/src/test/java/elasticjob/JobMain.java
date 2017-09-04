package elasticjob;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * Created by whydk on 10/21/2016.
 */
public class JobMain {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/elasticjob/job.xml");

    TimeUnit.SECONDS.sleep(20);

  }
}
