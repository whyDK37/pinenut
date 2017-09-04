package logging.commonslogging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by root on 15-7-1.
 * -Dorg.apache.commons.logging.diagnostics.dest=STDOUT
 */
public class LoggingApp {

  public static void main(String[] args) throws InterruptedException {
    Log logger = LogFactory.getFactory().getInstance("#LoggingApp");

    logger.info("init..................");

    logger.trace("trace..........");
    TimeUnit.SECONDS.sleep(5);
  }
}
