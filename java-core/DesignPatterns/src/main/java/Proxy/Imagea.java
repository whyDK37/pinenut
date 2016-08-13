package Proxy;
/**
 *  A Image
 */

import java.awt.*;

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