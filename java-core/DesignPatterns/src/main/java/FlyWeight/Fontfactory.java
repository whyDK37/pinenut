package FlyWeight;
/**
 *  A Flyweight Factory
 */

import java.util.Hashtable;

public class Fontfactory {
    private Hashtable charHashTable = new Hashtable();
    
    public Fontfactory() {
    }

    public Font GetFlyWeight(String s) {
        if(charHashTable.get(s) != null) {
            return (Font)charHashTable.get(s);
        } else {
            Font tmp = new Concretefont(s);
            charHashTable.put(s, tmp);
            return tmp;
        }
    }
    public Hashtable GetFactory() {
        return charHashTable;
    }
}