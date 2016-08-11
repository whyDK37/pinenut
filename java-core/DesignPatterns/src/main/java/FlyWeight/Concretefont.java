/**
 *  A shared ConcreteFlyweight
 */
import java.io.*;

public class ConcreteFont implements Font {
    private String color;
    private int size;
    private String str;
    
    public ConcreteFont(String s) {
        str = s;
        //id = "The char is: " + s;
    }
    public void SetFont(String _color, int _size) {
        color = _color;
        size = _size;
    }
    public void GetFont() {
        System.out.println("String :" + str + "--- color is:" + color + "--- size is:" + size);
    }
}