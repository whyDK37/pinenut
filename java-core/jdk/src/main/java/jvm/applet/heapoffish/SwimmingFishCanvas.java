package jvm.applet.heapoffish;/*
* Copyright (c) 1996-1999 Bill Venners. All Rights Reserved.
*
* This Java source file is part of the Interactive Illustrations Web
* Site, which is delivered in the applets directory of the CD-ROM
* that accompanies the book "Inside the Java 2 Virtual Machine" by Bill
* Venners, published by McGraw-Hill, 1999, ISBN: 0-07-135093-4. This
* source file is provided for evaluation purposes only, but you can
* redistribute it under certain conditions, described in the full
* copyright notice below.
*
* Full Copyright Notice:
*
* All the web pages and Java applets delivered in the applets
* directory of the CD-ROM, consisting of ".html," ".gif," ".class,"
* and ".java" files, are copyrighted (c) 1996-1999 by Bill
* Venners, and all rights are reserved.  This material may be copied
* and placed on any commercial or non-commercial web server on any
* network (including the internet) provided that the following
* guidelines are followed:
*
* a. All the web pages and Java Applets (".html," ".gif," ".class,"
* and ".java" files), including the source code, that are delivered
* in the applets directory of the CD-ROM that
* accompanies the book must be published together on the same web
* site.
*
* b. All the web pages and Java Applets (".html," ".gif," ".class,"
* and ".java" files) must be published "as is" and may not be altered
* in any way.
*
* c. All use and access to this web site must be free, and no fees
* can be charged to view these materials, unless express written
* permission is obtained from Bill Venners.
*
* d. The web pages and Java Applets may not be distributed on any
* media, other than a web server on a network, and may not accompany
* any book or publication.
*
* BILL VENNERS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
* SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
* BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR PARTICULAR PURPOSE, OR NON-INFRINGEMENT.  BILL VENNERS
* SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY A LICENSEE AS A
* RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
* DERIVATIVES.
*/
import java.awt.*;

/**
* This class upon which the red, blue, and yellow fish are
* painted swimming. This class contains the code that
* does the animation.
*
* @author  Bill Venners
*/
public class SwimmingFishCanvas extends Canvas implements Runnable {

    private Thread runner;

    private Image offscreenImage;
    private Graphics og;

    private final int swimmingFishGroupWidth = 150;
    private int xSwimmingFishGroupPos = 0 - swimmingFishGroupWidth;
    private int ySwimmingFishGroupPos = 20;
    private BigRedFishIcon bigSwimmingRedFish = new BigRedFishIcon(true);
    private MediumBlueFishIcon mediumSwimmingBlueFish = new MediumBlueFishIcon(true);
    private LittleYellowFishIcon littleSwimmingYellowFish = new LittleYellowFishIcon(true);

    SwimmingFishCanvas() {

        setBackground(Color.blue);
    }

    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void stop() {
        if (runner != null) {
            runner.stop();
            runner = null;
        }
    }

    public void run() {
        while (true) {

            repaint();
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException e) {
            }
        }
    }

    public Dimension minimumSize() {
        return new Dimension(500, 240);
    }

    public Dimension preferredSize() {
        return new Dimension(500, 240);
    }

    public void update(Graphics g) {

        g.clipRect(xSwimmingFishGroupPos, ySwimmingFishGroupPos, swimmingFishGroupWidth + 1, bigSwimmingRedFish.getFishHeight());
        paint(g);
    }

    public void paint(Graphics g) {

        // First calculate the positions of the goodies on the canvas based on the width
        // and height of the canvas.
        Dimension dim = size();

        if (offscreenImage == null) {

            offscreenImage = createImage(dim.width, dim.height);
            og = offscreenImage.getGraphics();
        }

        // Divide height into three equal portions. The top portion will hold the object pool.
        // The middle portion will hold the handle pool. The bottom portion will hold
        // something that depends on the current mode.

        int yHandlePoolPortion = dim.height / 3;
        int yModeDependentPortion = 2 * yHandlePoolPortion;

        xSwimmingFishGroupPos += 1;

       if (xSwimmingFishGroupPos > size().width) {

            // Leave 5 pixels on top or bottom of Y range that fish may swim in.
            int yRange = dim.height - bigSwimmingRedFish.getFishHeight() - 10;
            ySwimmingFishGroupPos = (int) (((double) yRange) * Math.random());
            ySwimmingFishGroupPos += 5;

            xSwimmingFishGroupPos = 0 - swimmingFishGroupWidth;
        }

        bigSwimmingRedFish.moveFish(xSwimmingFishGroupPos, ySwimmingFishGroupPos);
        mediumSwimmingBlueFish.moveFish(xSwimmingFishGroupPos + 67, ySwimmingFishGroupPos + 4);
        littleSwimmingYellowFish.moveFish(xSwimmingFishGroupPos + 120, ySwimmingFishGroupPos + 8);

        // Because update doesn't clear the screen in the case of swimming fish to
        // reduce flicker, must clear the rectangle here. May as well color the entire
        // mode dependent portion blue so it looks like water.
        og.setColor(Color.blue);
        og.fillRect(0, 0, dim.width, dim.height);

        bigSwimmingRedFish.paint(og);
        mediumSwimmingBlueFish.paint(og);
        littleSwimmingYellowFish.paint(og);

        g.drawImage(offscreenImage, 0, 0, this);
    }
}