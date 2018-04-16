package Prototype;
/*
 *  As  a Test Client to test our pattern  
 */

import java.util.Hashtable;

public class GraphicTool {
  public GraphicTool() {
  }

  public static void main(String[] args) {
    //-----  Initial our prototype instance  ----------
    SymbolLoader myLoader = new SymbolLoader();
    Hashtable mySymbols = myLoader.getSymbols();

    //-----  Draw a Line  -------------------------------
    Graphic myLine = (Graphic) ((Graphic) mySymbols.get("Line")).clone();
    myLine.DoSomething();
  }
}