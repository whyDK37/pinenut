package ChainOfResponsibility;
/**
 *  A member of the chain
 *  The resposibility of PM is to design the project 
 */

public class Projectmanager implements Chain {
    private Chain nextChain = null;
    private String responsibility = "Design";
    
    public Projectmanager() {
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