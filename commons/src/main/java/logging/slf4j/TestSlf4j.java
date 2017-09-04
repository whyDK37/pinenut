package logging.slf4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.concurrent.TimeUnit;

public class TestSlf4j {

  public static void main(String[] args) throws InterruptedException {
//        PropertyConfigurator.configure(TestSlf4j.class.getClassLoader().getResourceAsStream("log4j.properties"));
    MDC.put("first", "Dorothy");
    Logger logger = LoggerFactory.getLogger(TestSlf4j.class);
    System.out.println("logger : " + logger.getClass());
    logger.info("yes");

    Logger logger2 = LoggerFactory.getLogger(TestSlf4j.class);

    logger2.info("yes");

    logger2.debug("hello {}", "debe");
    logger2.warn("hello");
    logger2.trace("hello");

    logger.debug("Processing trade with id: {}  and symbol : {} ", 123, "symbol");

    MDC.put("last", "Parker");
    Exception e = new IndexOutOfBoundsException(" index out of bound ");
    logger.error(e.getMessage(), e);
    TimeUnit.SECONDS.sleep(5);

  }

}
