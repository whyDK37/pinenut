package ChainOfResponsibility;

/**
 * A client to test
 */

public class Test {
  public static void main(String[] args) {
    Manager aManager = new Manager();
    Projectmanager aPM = new Projectmanager();
    Programmer aProgrammer = new Programmer();
    Qa aQA = new Qa();
    Others others = new Others();

    aManager.addChain(aPM);
    aPM.addChain(aProgrammer);
    aProgrammer.addChain(aQA);
    aQA.addChain(others);

    aManager.sendToChain("Get Project");
    aManager.sendToChain("Design");
    aManager.sendToChain("Coding");
    aManager.sendToChain("Test");
    aManager.sendToChain("Kill La Deng !");
  }
}