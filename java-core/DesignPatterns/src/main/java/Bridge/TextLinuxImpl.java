package Bridge;
/**
 *  The ConcreteImplementor
 */
public class TextLinuxImpl implements TextImp {
    public TextLinuxImpl() {
    }
    public void DrawTextImp() {
        System.out.println("The text has a Linux style !");
    }
}