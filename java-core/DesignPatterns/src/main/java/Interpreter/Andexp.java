package Interpreter;

/**
 * A NonterminalExpression
 */
public class Andexp implements Booleanexp {
  private Booleanexp operand1;
  private Booleanexp operand2;

  public Andexp(Booleanexp oper1, Booleanexp oper2) {
    operand1 = oper1;
    operand2 = oper2;
  }

  public boolean Evaluate(Context c) {
    return operand1.Evaluate(c) &&
            operand2.Evaluate(c);
  }

  public Booleanexp Copy() {
    return new Andexp(operand1.Copy(), operand2.Copy());
  }

  public Booleanexp Replace(String var, Booleanexp exp) {
    return new Andexp(
            operand1.Replace(var, exp),
            operand2.Replace(var, exp)
    );
  }
}