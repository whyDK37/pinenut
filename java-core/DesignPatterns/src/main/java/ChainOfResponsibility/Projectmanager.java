/**
 *  A member of the chain
 *  The resposibility of PM is to design the project 
 */
import java.io.*;

public class ProjectManager implements Chain {
    private Chain nextChain = null;
    private String responsibility = "Design";
    
    public ProjectManager() {
    }
    public void addChain(Chain c) {
        nextChain = c;
    }
    
    public Chain getChain() {
        return nextChain;
    }
    
    public void sendToChain(String mesg) {
        if(mesg.equals(responsibility)) {
            System.out.println("A PM  -->  Design");
        } else {
            if(nextChain != null) {
                nextChain.sendToChain(mesg);
            }
        }
    }
    
}