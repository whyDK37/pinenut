package net.jcip.examples;

public class Widget {
  public static void main(String[] args) {
    new LoggingWidget().doSomething();
  }

  public synchronized void doSomething() {
    System.out.println("supper do something");
  }
}

class LoggingWidget extends Widget {
  public synchronized void doSomething() {
    System.out.println(toString() + ": calling doSomething");
    super.doSomething();
  }
}