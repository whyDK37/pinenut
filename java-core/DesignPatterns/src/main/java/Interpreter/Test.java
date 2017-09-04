package Interpreter;

/**
 *
 */

public class Test {
  public static void main(String[] args) {
    // Test :
    //         (true and x) and (y and (not x))
    Context context = new Context();

    Variableexp x = new Variableexp("X");
    Variableexp y = new Variableexp("Y");
    Variableexp bTure = new Variableexp("true");
    Variableexp bFalse = new Variableexp("false");

    context.Assign("true", true);
    context.Assign("false", false);
    context.Assign("X", false);
    context.Assign("Y", true);

    Booleanexp expression = new Andexp(
            new Andexp(bTure, x),
            new Andexp(y, new Notexp(x))
    );
    boolean result = expression.Evaluate(context);
    System.out.println("The result is:" + result);
  }
}