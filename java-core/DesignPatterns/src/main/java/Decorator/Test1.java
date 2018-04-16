package Decorator;

/**
 * A simple test
 */
public class Test1 {
  public static void main(String[] args) {
    Component myComponent = new Concretecomponent();
    myComponent.PrintString("A test String");
    Decorator myDecorator = new Concretedecoratora(myComponent);
    myDecorator.PrintString("A test String");
  }
}