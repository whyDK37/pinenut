package Decorator;
/**
 *  The Concrete Decorator
 */

public class Concretedecoratora extends Decorator {
    public Concretedecoratora(Component c) {
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