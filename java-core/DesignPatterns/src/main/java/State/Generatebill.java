/**
 *  A concrete state for generating bill
 */
import java.io.*;

public class GenerateBill extends ShopState {
    public static boolean instanceFlag = false; //true if have 1 instance
    private GenerateBill() {
    }
    public static GenerateBill getInstance() {
        if(! instanceFlag) {
            instanceFlag = true;
            return new GenerateBill();
        }
        return null;
    }
    public void generateBill() {
        System.out.println("The state is generating bill now !");
    }
}
