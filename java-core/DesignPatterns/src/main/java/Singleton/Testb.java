package Singleton;

/**
 * Test for our Singleton registry
 */
public class Testb {
  public static void main(String[] args) {
    // First we get a instance from SingletonB
    Singletonb instance1 = Singletonb.GetInstance("Sub1");
    if (instance1 == null) {
      System.out.println("There is no such instance in registry !");
    } else {
      System.out.println(instance1.getClass());
    }

    // Then we register a new instance
    try {
      Singletonb instance2 = new Singletonb();
      System.out.println("We had created a new instance named \"Sub1\" now");
    } catch (Singletonexception e) {
      System.out.println(e.getMessage());
    }

    // To get instance again
    instance1 = Singletonb.GetInstance("Sub1");
    if (instance1 == null) {
      System.out.println("There is no such instance in registry !");
    } else {
      System.out.println(instance1.getClass());
    }

    // Finally we create a new instance again
    try {
      Singletonb instance3 = new Singletonb();
      System.out.println("We had created a new instance named \"Sub1\" now");
    } catch (Singletonexception e) {
      System.out.println(e.getMessage());
    }

  }
}