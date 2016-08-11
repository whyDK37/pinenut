/**
 *  A concrete state for customer shopping
 */
import java.io.*;

public class Pay extends ShopState {
    public static boolean instanceFlag = false; //true if have 1 instance
    private Pay() {
    }
    public static Pay getInstance() {
        if(! instanceFlag) {
            instanceFlag = true;
            return new Pay();
        }
        return null;
    }
    public void pay() {
        System.out.println("The state is pay now !");
    }
}