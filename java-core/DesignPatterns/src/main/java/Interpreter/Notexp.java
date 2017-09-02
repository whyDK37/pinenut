package Interpreter;
/**
 *  A NonterminalExpression
 */
public class Notexp implements Booleanexp {
    private Booleanexp opernot1;
    //private BooleanExp operor2;
    
    public Notexp(Booleanexp oper1) {
        opernot1 = oper1;
    }

    public boolean Evaluate(Context c) {
        return !(opernot1.Evaluate(c));
    }

    public Booleanexp Copy() {
        return new Notexp(opernot1.Copy());
    }

    public Booleanexp Replace(String var, Booleanexp exp) {
        return new Notexp(opernot1.Replace(var, exp));
    }
}