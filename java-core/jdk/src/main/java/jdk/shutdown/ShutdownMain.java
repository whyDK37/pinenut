package jdk.shutdown;

/**
 * Created by whydk on 2016/9/14.
 */
public class ShutdownMain {
  public static void main(String[] args) {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        System.out.println("shutdown.........");
//                for (Container container : containers) {
//                    try {
//                        container.stop();
//                        logger.info("Dubbo " + container.getClass().getSimpleName() + " stopped!");
//                    } catch (Throwable t) {
//                        logger.error(t.getMessage(), t);
//                    }
//                    synchronized (Main.class) {
//                        running = false;
//                        Main.class.notify();
//                    }
//                }
      }
    });
  }
}
