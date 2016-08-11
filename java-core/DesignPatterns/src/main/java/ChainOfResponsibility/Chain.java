/**
 *  The interface of the chain
 *  You can use AddChain function to modify the chain dynamically
 */
public interface Chain  {
    public abstract void addChain(Chain c);
    public abstract void sendToChain(String mesg);
    public abstract Chain getChain();
}