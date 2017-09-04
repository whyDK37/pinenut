package logging.slf4j;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.MDC;

public abstract class TraceUtils {
  public static final String TRACE_KEY = "traceId";
  public static final int TRACE_LENGTH = 8;

  public static void beginTrace() {
    String traceId = RandomStringUtils.randomAlphanumeric(8);
    MDC.put("traceId", traceId);
  }

  public static void beginTrace(String traceId) {
    MDC.put("traceId", traceId);
  }

  public static void endTrace() {
    MDC.remove("traceId");
  }

  public static String getTraceIdKey() {
    return ((String) MDC.get("traceId"));
  }
}  