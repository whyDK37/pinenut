package Singleton;

/**
 * A test for SingletonA
 */
public class Testa {
  public static void main(String[] args) {
    // Can not create a instance !
    //SingletonA instance1 = new SingletonA();
    Singletona instance2 = Singletona.getInstance();
    if (instance2 != null) {
      instance2.setNum();
      System.out.println("i is:" + instance2.getNum());
    }
    Singletona instance3 = Singletona.getInstance();
    if (instance3 == null) {
      System.out.println("Can not get instance twice !");
    }
  }
}