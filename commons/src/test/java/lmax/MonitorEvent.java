package lmax;

import org.joda.time.DateTime;


public class MonitorEvent {
  private String message;
  private DateTime timestamp;

  public MonitorEvent(String message) {
    this.timestamp = new DateTime();
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public DateTime getTimestamp() {
    return timestamp;
  }

}