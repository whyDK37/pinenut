package net.jcip.examples;

public class SafePointMain {
  public static void main(String[] args) throws Exception {
    final SafePoint originalSafePoint = new SafePoint(1, 1);

    //One Thread is trying to change this UnSafePoint
    new Thread(() -> {
      originalSafePoint.setXY(2, 2);
      System.out.println("Original : " + originalSafePoint.toString());
    }).start();

    //The other Thread is trying to create a copy. The copy, depending on the JVM, MUST be either (1,1) or (2,2)
    //depending on which Thread starts first, but it can not be (1,2) or (2,1) for example.
    new Thread(() -> {
      // todo right
      SafePoint copySafePoint = new SafePoint(originalSafePoint);
      System.out.println("Copy : " + copySafePoint.toString());
    }).start();
  }
}