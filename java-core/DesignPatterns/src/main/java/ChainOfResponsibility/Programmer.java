/**
 *  A member of the chain
 *  The resposibility of Programmer is coding 
 */
import java.io.*;

public class Programmer implements Chain {
    private Chain nextChain = null;
    private String responsibility = "Coding";
    
    public Programmer() {
    }
    public void addChain(Chain c) {
        nextChain = c;
    }
    
    public Chain getChain() {
        return nextChain;
    }
    
    public void sendToChain(String mesg) {
        if(mesg.equals(responsibility)) {
            System.out.println("A Programmer  -->  Coding");
        } else {
            if(nextChain != null) {
                nextChain.sendToChain(mesg);
            }
        }
    }
    
}
