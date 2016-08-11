/**
 *  A concrete colleague 
 */
import java.io.*;

public class ColleagueC implements Colleague {
    private final String type = "C";
    private Mediator med;
    public ColleagueC(Mediator m) {
        med = m;
        med.Register(this, type);
    }
    public void Change() {
        System.out.println("-----  C changed now !  -----");
        med.Changed(type);
    }
    public void Action() {
        System.out.println("  C is changed by mediator ");
    }
}