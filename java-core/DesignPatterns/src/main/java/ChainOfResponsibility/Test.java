/**
 *  A client to test
 */
import java.io.*;

public class Test  {
    public static void main(String[] args) {
        Manager aManager = new Manager();
        ProjectManager aPM = new ProjectManager();
        Programmer aProgrammer = new Programmer();
        QA aQA = new QA();
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