package Decorator;
/**
 *  A simple test
 */
public class Test1  {
    public static void main(String[] args) {
        Component myComponent = new Decorator.Concretecomponent();
        myComponent.PrintString("A test String");
        Decorator myDecorator = new Decorator.Concretedecoratora(myComponent);
        myDecorator.PrintString("A test String");
    }
}