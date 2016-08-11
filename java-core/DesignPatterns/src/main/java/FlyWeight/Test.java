/**
 *  A test client
 */
import java.io.*;
import java.util.*;

public class Test  {
    public static void main(String[] args) {
        int[] size = {8, 9, 10, 11, 12};
        String[] color = {"FFFFFF", "000000", "FF00FF", "CCCCCC", "111111"};
        FontFactory myFontFactory = new FontFactory();
        String aString = "A test string";
        for(int i = 0; i < aString.length(); i++) {
            int j = 0;
            j = (int)Math.floor(Math.random()*5);
            //System.out.println("j is:" + j + "---" +aString.substring(i, i+1));
            myFontFactory.GetFlyWeight(aString.substring(i, i+1)).SetFont(color[j], size[j]);
        }
        
        Hashtable myHashTable = myFontFactory.GetFactory();
        System.out.println("Hash table size is:" + myHashTable.size());
        for(int i = 0; i < aString.length(); i++) {
            ((Font)myHashTable.get(aString.substring(i, i+1))).GetFont();
        }
    }
}