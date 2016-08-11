/**
 *  A concrete colleague 
 */
import java.io.*;

public class ColleagueA implements Colleague {
    private final String type = "A";
    private Mediator med;
    
    public ColleagueA(Mediator m) {
        med = m;
        med.Register(this, type);
    }
    public void Change() {
        System.out.println("-----  A changed now !  -----");
        med.Changed(type);
    }
    public void Action() {
        System.out.println("  A is changed by mediator ");
    }
}