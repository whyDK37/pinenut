/**
 *  A Flyweight Factory
 */
import java.util.*;

public class FontFactory  {
    private Hashtable charHashTable = new Hashtable();
    
    public FontFactory() {
    }

    public Font GetFlyWeight(String s) {
        if(charHashTable.get(s) != null) {
            return (Font)charHashTable.get(s);
        } else {
            Font tmp = new ConcreteFont(s);
            charHashTable.put(s, tmp);
            return tmp;
        }
    }
    public Hashtable GetFactory() {
        return charHashTable;
    }
}