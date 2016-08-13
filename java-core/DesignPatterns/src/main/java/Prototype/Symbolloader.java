package Prototype;
/*
 *  A Symbol Loader to register all prototype instance
 */

import java.util.Hashtable;

public class SymbolLoader  {
    private Hashtable symbols = new Hashtable();
    public SymbolLoader() {
           symbols.put("Line", new LineSymbol());
           symbols.put("Note", new NoteSymbol());
    }
    public Hashtable getSymbols() {
        return symbols;
    }
}