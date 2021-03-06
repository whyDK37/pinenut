package Interpreter;
/**
 * A Context to record variable value
 */

import java.util.Hashtable;

public class Context {
  private Hashtable context = new Hashtable();

  public Context() {
  }

  public void Assign(String name, boolean val) {
    context.put(name, new Boolean(val));
  }

  public boolean LookUp(String name) {
    return ((Boolean) context.get(name)).booleanValue();
  }
}