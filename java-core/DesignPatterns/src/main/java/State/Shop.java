/**
 *  A concrete state for customer shopping
 */
import java.io.*;

public class Shop extends ShopState {
    public static boolean instanceFlag = false; //true if have 1 instance
    private Shop() {
    }
    public static Shop getInstance() {
        if(! instanceFlag) {
            instanceFlag = true;
            return new Shop();
        }
        return null;
    }
    public void shop() {
        System.out.println("The state is shopping now !");
    }
}