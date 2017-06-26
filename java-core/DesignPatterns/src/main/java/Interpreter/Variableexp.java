package Interpreter;
/**
 *  A variable expression implements BooleanExp
 *  A terminal expression
 */

public class Variableexp implements Booleanexp {
    private String name;
    
    public Variableexp(String _name) {
        name = _name;
    }

    public boolean Evaluate(Context c) {
        return c.LookUp(name);
    }

    public Booleanexp Copy() {
        return new Variableexp(name);
    }

    public Booleanexp Replace(String var, Booleanexp exp) {
        if(var.equals(name)) {
            return exp.Copy();
        } else {
            return new Variableexp(name);
        }
    }
    
}