package slf4j;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSlf4j {

    public static void main(String[] args) {
//        PropertyConfigurator.configure(TestSlf4j.class.getClassLoader().getResourceAsStream("log4j.properties"));

        Logger logger = LoggerFactory.getLogger(TestSlf4j.class);
        System.out.println("logger : " + logger.getClass());
        logger.info("yes");

        org.slf4j.Logger logger2 = LoggerFactory.getLogger(TestSlf4j.class);

        logger2.info("yes");

        logger2.debug("hello {}", "debe");
        logger2.warn("hello");
        logger2.trace("hello");

        logger.debug("Processing trade with id: {}  and symbol : {} ", 123, "symbol");

        logger.info("","");


    }

}
