/**
 *  A simple test
 */
public class Test1  {
    public static void main(String[] args) {
        Component myComponent = new ConcreteComponent();
        myComponent.PrintString("A test String");
        Decorator myDecorator = new ConcreteDecoratorA(myComponent);
        myDecorator.PrintString("A test String");
    }
}