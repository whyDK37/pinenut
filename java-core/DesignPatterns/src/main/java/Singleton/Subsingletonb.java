/**
 *  A real Singleton we want to have
 */
public class SubSingletonB extends SingletonB {
    public static boolean instanceFlag = false; //true if 1 instance
    //private int i = 0;
    
    public SubSingletonB() throws SingletonExecption {
        if(instanceFlag) {
            throw new SingletonExecption("Only can create a instance !");
        } else {
            instanceFlag = true;
            super.Register("Sub1", this);
        }
    }
    
    public void finalize() {
        instanceFlag = false;
    }
}