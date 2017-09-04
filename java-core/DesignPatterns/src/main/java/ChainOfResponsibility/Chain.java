package ChainOfResponsibility;

/**
 * The interface of the chain
 * You can use AddChain function to modify the chain dynamically
 */
public interface Chain {
  void addChain(Chain c);

  void sendToChain(String mesg);

  Chain getChain();
}