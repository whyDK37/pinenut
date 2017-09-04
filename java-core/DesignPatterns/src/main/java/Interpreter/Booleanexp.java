package Interpreter;

/**
 * The interface of our BooleanExp Interpreter
 * BooleanExp definition is:
 * BooleanExp ::= VariableExp | Constant | OrExp | AndExp
 * | NotExp | '(' BooleanExp ')'
 * AndExp ::= BooleanExp 'and' BooleanExp
 * OrExp ::= BooleanExp 'or' BooleanExp
 * NotExp ::= BooleanExp 'not' BooleanExp
 * Constant ::= 'true' | 'false'
 * VariableExp ::= 'A' | 'B' | ... | 'Z'
 */

public interface Booleanexp {
  boolean Evaluate(Context c);

  Booleanexp Replace(String var, Booleanexp exp);

  Booleanexp Copy();
}