package Singleton;

/**
 * A real Singleton we want to have
 */
class SubSingletonb extends Singletonb {
  public static boolean instanceFlag = false; //true if 1 instance
  //private int i = 0;

  public SubSingletonb() throws Singletonexception {
    if (instanceFlag) {
      throw new Singletonexception("Only can create a instance !");
    } else {
      instanceFlag = true;
      super.Register("Sub1", this);
    }
  }

  public void finalize() {
    instanceFlag = false;
  }
}