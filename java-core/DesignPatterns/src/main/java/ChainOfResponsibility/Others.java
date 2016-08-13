package ChainOfResponsibility;
/**
 *  The end of the chain
 *  The resposibility of Others is handle exeception 
 */

public class Others implements Chain {
    private Chain nextChain = null;
    private String responsibility = "";
    
    public Others() {
    }
    public void addChain(Chain c) {
        nextChain = c;
    }
    
    public Chain getChain() {
        return nextChain;
    }
    
    public void sendToChain(String mesg) {
            System.out.println("No one can handle -->  " + mesg);
    }
    
}