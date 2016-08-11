/**
 *  A Context to record variable value 
 */
import java.util.*;

public class Context  {
    private Hashtable context = new Hashtable();
    public void Assign(String name, boolean val) {
        context.put(name, new Boolean(val));
    }
    public boolean LookUp(String name) {
        return ((Boolean)context.get(name)).booleanValue();
    }
    public Context() {
    }
}