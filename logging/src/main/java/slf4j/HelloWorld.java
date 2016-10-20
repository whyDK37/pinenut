package slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class HelloWorld {
  public static void main(String[] args) throws InterruptedException {
    Logger logger = LoggerFactory.getLogger(HelloWorld.class);
    logger.info("Hello World");

    TimeUnit.SECONDS.sleep(5);
  }
}