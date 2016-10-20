package slf4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TestSlf4jThread {

    public static void main(String[] args) throws InterruptedException {
//        PropertyConfigurator.configure(TestSlf4j.class.getClassLoader().getResourceAsStream("log4j.properties"));


        final Logger logger = LoggerFactory.getLogger(TestSlf4jThread.class);
        final Logger logger2 = LoggerFactory.getLogger(TestSlf4jThread.class);
        Thread[] threads = new Thread[10];
        int i = 0;
        for (Thread thread:threads){
            threads[i++] = new Thread(new Runnable() {
                @Override
                public void run() {
                    MDC.put("first", "Dorothy-"+ Thread.currentThread().getName());

                    System.out.println("logger : " + logger.getClass());
                    logger.info("yes");

                    logger2.info("yes");

                    logger2.debug("hello {}", "debe");
                    logger2.warn("hello");
                    logger2.trace("hello");

                    logger.debug("Processing trade with id: {}  and symbol : {} ", 123, "symbol");

                    MDC.put("last", "Parker-");
                    Exception e = new IndexOutOfBoundsException(" index out of bound ");
                    logger.error("",e);

                }
            });
        }

        for (Thread thread:threads) {
            thread.start();
        }


        TimeUnit.SECONDS.sleep(5);

    }

}
