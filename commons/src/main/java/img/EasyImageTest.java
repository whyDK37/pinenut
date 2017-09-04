package img;

import java.awt.*;

/**
 * Created by whydk on 2016/9/22.
 */
public class EasyImageTest {
  public static void main(String[] args) {
        /*
        EasyImage image  = new EasyImage("c:/pics/p1.jpg");
        image.resize(10);
        image.multiply(5, 5, 11111);
        image.saveAs("c:/pics/multiply+color.jpg");
        */
        /*
        EasyImage image  = new EasyImage("c:/pics/israel_flag.gif");
        EasyImage image2  = new EasyImage("c:/pics/palestine_flag.gif");
        //image.resize(50);
        //image2.resize(50);
        //image.affineTransform(0, 0.3);
        //image2.affineTransform(0, -0.3);
        image.combineWithPicture(image2);
        image.saveAs("c:/pics/affineTransformAndCombine2.jpg");
       */
       /*
        EasyImage image  = new EasyImage("c:/pics/p1.jpg");
        image.resize(50);
        image.affineTransform(0.0, 0.5);
        image.saveAs("c:/pics/affineTransform.jpg");
        */
     /*
        EasyImage image  = new EasyImage("c:/pics/heart.gif");
        image.multiply(20, 20);
        EasyImage image2  = new EasyImage("c:/pics/p6.jpg");
        image2.crop(800, 0, -1, -1);
        image2.resize(50);
        image2.combineWithPicture(image,3,Color.white);
        image2.saveAs("c:/pics/combineWithPictureWithoutBackground.jpg");
        /*
        image.resize(5);
        image.multiply(20, 20);
        image.combineWithPicture("c:/p2.jpg");
        //image.addColorToImage(Color.yellow, 3);
        //image.addColorToImage(Color.red, 5);
        //image.combineWithPicture("c:/p2.jpg",3);

      */

    EasyImage image = new EasyImage("c:/pics/p1.jpg");
    int width = image.getWidth();
    int height = image.getHeight();
    for (int i = 0, c = 0; i < height; c++, i += 50) {
      int x = width / 2 - i;
      int y = height / 2 - i;

      image.emphasize(x, y, width - 1 - x, height - 1 - y, Color.BLACK, 12 - c / 4);
    }
    image.saveAs("c:/pics/emphesizeTrick.jpg");
    //  */
    // image.saveAs("c:/xxx.jpg");
        /*
        EasyImage image  = new EasyImage("c:/pics/p1.jpg");
        image.addColorToImage(Color.red, 5);
        image.saveAs("c:/pics/addColorToImage.jpg");
        */
  }
}
