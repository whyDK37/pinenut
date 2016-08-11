import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
 
import java.awt.*; 
public class ProxyDisplay extends JxFrame
{
   public ProxyDisplay()
   {
      super("Display proxied image");
      JPanel p = new JPanel();
      getContentPane().add(p);
      p.setLayout(new BorderLayout());
      ImageProxy image = new ImageProxy("d:\\JavaProject\\Proxy\\src\\elliott.jpg", 321, 271);
      //ImageProxy image = new ImageProxy("c:\\winnt\\web\\Wallpaper\\Windows 2000.jpg", 321, 271);
      p.add("Center", image);
      p.add("North", new Label("    "));
      p.add("West", new Label("  "));
      setSize(370, 350);
      setVisible(true);
   }
//------------------------------------
   static public void main(String[] argv)
   {
      new ProxyDisplay();
   }
}
//==================================
class ImageProxy extends JPanel
   implements Runnable
{
   int height, width;
   MediaTracker tracker;
   Image img;
   JFrame frame;
   Thread imageCheck;        //to monitor loading
//------------------------------------
   public ImageProxy(String filename, int w, int h)
   {
   height = h;
   width = w;
   
   tracker = new MediaTracker(this);
   img = Toolkit.getDefaultToolkit().getImage(filename);
   tracker.addImage(img, 0);     //watch for image loading

   //imageCheck = new Thread(this);
   //imageCheck.start();           //start 2nd thread monitor
   
   //this begins actual image loading
   try{
      tracker.waitForID(0,1);
   }
   catch(InterruptedException e){}
   imageCheck = new Thread(this);
   imageCheck.start();           //start 2nd thread monitor
   
   }
//------------------------------------
   public void paint(Graphics g)
   {
    if (tracker.checkID(0)) 
      {
      height = img.getHeight(frame);   //get height
      width = img.getWidth(frame);     //and width
      
      g.setColor(Color.lightGray);     //erase box
      g.fillRect(0,0, width+5, height+5);
      g.drawImage(img, 0, 0, this);   //draw loaded image
      }
   else
      {
      //draw box outlining image if not loaded yet
      g.setColor(Color.red);
      g.drawRect(1, 1, width+10, height+10);
      }
   }
   //------------------------------------
   public Dimension getPreferredSize()
   {
      return new Dimension(width, height);
   }
   //public int getWidth() {return width;}
   //public int getHeight(){return height;}
   //------------------------------------
   public void run()
   {
   //this thread monitors image loading
   //and repaints when done
   //the 1000 msec is artifically long
   //to allow demo to display with delay
   try{
   Thread.sleep(1000);
   while(! tracker.checkID(0))
      Thread.sleep(1000);
   }
   catch(Exception e){}
   repaint();
   }
}
