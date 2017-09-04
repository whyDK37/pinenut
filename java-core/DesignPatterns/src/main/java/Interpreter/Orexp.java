package Interpreter;

/**
 * A NonterminalExpression
 */
public class Orexp implements Booleanexp {
  private Booleanexp operor1;
  private Booleanexp operor2;

  public Orexp(Booleanexp oper1, Booleanexp oper2) {
    operor1 = oper1;
    operor2 = oper2;
  }

  public boolean Evaluate(Context c) {
    return operor1.Evaluate(c) ||
            operor2.Evaluate(c);
  }

  public Booleanexp Copy() {
    return new Orexp(operor1.Copy(), operor2.Copy());
  }

  public Booleanexp Replace(String var, Booleanexp exp) {
    return new Orexp(
            operor1.Replace(var, exp),
            operor2.Replace(var, exp)
    );
  }
}