/**
 *  The Decorator
 */
public class Decorator implements Component {
    private Component component;
    public Decorator(Component c) {
        component = c;
    }
    public void PrintString(String s) {
        component.PrintString(s);
    }
}