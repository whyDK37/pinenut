package Singleton;

/**
 * A Singleton , we can only create one instance
 */
public class Singletona {
  public static boolean instanceFlag = false; //true if 1 instance
  //private SingletonA instance;
  private int i = 0;

  // Set constructor private and do nothing
  // Can not new a instance outside class
  private Singletona() {
  }

  public static Singletona getInstance() {
    if (!instanceFlag) {
      instanceFlag = true;
      return new Singletona();
    }
    return null;
  }

  public int getNum() {
    return i;
  }

  public void setNum() {
    i++;
  }

  public void finalize() {
    instanceFlag = false;
  }
}