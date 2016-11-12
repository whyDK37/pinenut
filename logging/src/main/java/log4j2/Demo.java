package log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by why on 11/5/2016.
 */
public class Demo {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

        logger.info("demo test.");
        System.out.println(logger);

        Logger demo = LogManager.getLogger(Demo.class);
        System.out.println(demo);
        demo.info("new demo test.");
    }
}
