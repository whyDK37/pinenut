/**
 *  The Concrete Decorator
 */
import java.io.*;

public class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component c) {
        super(c);
    }
    public void PrintString(String s) {
        super.PrintString(s);
        PrintStringLen(s);
    }
    public void PrintStringLen(String s) {
        System.out.println("The length of string is:" + s.length());
    }
}