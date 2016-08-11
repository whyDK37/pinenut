/**
 *  A concrete colleague 
 */
import java.io.*;

public class ColleagueB implements Colleague {
    private final String type = "B";
    private Mediator med;
    public ColleagueB(Mediator m) {
        med = m;
        med.Register(this, type);
    }
    public void Change() {
        System.out.println("-----  B changed now !  -----");
        med.Changed(type);
    }
    public void Action() {
        System.out.println("  B is changed by mediator ");
    }
}