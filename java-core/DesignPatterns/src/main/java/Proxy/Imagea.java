/**
 *  A Image
 */
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;

public class ImageA implements Graphic {
    public ImageA(String _fileName) {
        fileName = _fileName;
        
    }
    public void Draw() {
        

    }
    public MediaTracker tracker;
    private int height, width;
    private String fileName;
    
}