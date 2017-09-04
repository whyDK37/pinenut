package Decorator;

/**
 * A Concrete Component
 */

public class Concretecomponent implements Component {
  public Concretecomponent() {
  }

  public void PrintString(String s) {
    System.out.println("Input String is:" + s);
  }
}