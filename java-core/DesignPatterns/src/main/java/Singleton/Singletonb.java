package Singleton;
/**
 * A new Singleton use registry
 */

import java.util.Hashtable;

public class Singletonb {
  static private Hashtable registry = new Hashtable();
  //static private SingletonB instance;

  public static void Register(String name, Singletonb aInstance) {
    registry.put(name, aInstance);
  }

  public static Singletonb GetInstance(String name) {
    return LookUp(name);
  }

  protected static Singletonb LookUp(String name) {
    return (Singletonb) registry.get(name);
  }
}