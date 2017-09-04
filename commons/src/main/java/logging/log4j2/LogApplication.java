package logging.log4j2;

public class LogApplication {

  public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(LogApplication.class, args);
    LogEntryGenerator logEntryGenerator = new LogEntryGenerator();
    logEntryGenerator.startLogging();
  }
}