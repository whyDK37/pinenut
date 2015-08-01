package commonslogging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by root on 15-7-1.
 * -Dorg.apache.commons.logging.diagnostics.dest=STDOUT
 */
public class LoggingApp {

    public static void main(String[] args) {
        Log logger = LogFactory.getFactory().getInstance("#LoggingApp");

        logger.info("init..................");
    }
}
