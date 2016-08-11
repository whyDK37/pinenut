/**
 *  A Singleton , we can only create one instance
 */
public class SingletonA  {
    public static boolean instanceFlag = false; //true if 1 instance
    //private SingletonA instance;
    private int i = 0;
    
    // Set constructor private and do nothing
    // Can not new a instance outside class
    private SingletonA() {
    }

    public int getNum() {
        return i;
    }
    
    public void setNum() {
        i++;
    }
    
    public static SingletonA getInstance() {
        if(! instanceFlag) {
            instanceFlag = true;
            return new SingletonA();
        }
        return null;
    }

    public void finalize() {
        instanceFlag = false;
    }
}