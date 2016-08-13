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
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Polygon;
import java.awt.Color;

// Fish Icon
//
// topTail   frontTail
//      |\       |       /\
//      |  \     |     /    \
//       \   \   |   /        \
//         \   \   /        o   \
// backTail  >--><               >
//         /   /   \            /
//       /   /       \        /
//       | /           \    /
//       |/              \/
// bottomTail
//
/**
* This class represents each fish icon that appears on
* the user interface. It is an abstract class that is
* subclass by BigRedFishIcon, MediumBlueFishIcon, and
* LittleYellowFishIcon, which initialize many of the
* protected instance variables and define the abstract
* method getFishColor(). This class takes care of drawing
* the fish. For each fish on the heap, the object handle
* data contains a reference to an instance of this class,
* which serves as the "class data" for the objects on
* this simulated heap.
*
* @author  Bill Venners
*/
abstract class FishIcon {

    protected Point topTail;
    protected Point backTail;
    protected Point bottomTail;
    protected Point frontTail;
    protected Polygon staticTail = new Polygon();

    protected final int swimmingTailPositionCount = 10;
    protected Polygon[] swimmingTail = new Polygon[swimmingTailPositionCount];
    protected int currentSwimmingTailPos = 0;
    protected int holdTailPositionCounter = 0;
    protected int maxPaintsToHoldTailInOnePosition;

    protected Point ovalPos;
    protected Dimension ovalDim;

    protected Point eyePos;
    protected Dimension eyeDim;

    protected Point fishPosition;
    protected boolean fishHasBeenInitiallyPositioned;

    protected boolean fishIsSwimming;

    protected Color fishColor;

    public void moveFish(int x, int y) {

        fishHasBeenInitiallyPositioned = true;
        fishPosition.x = x;
        fishPosition.y = y;
    }

    public int getFishHeight() {
        return bottomTail.y;
    }

    public int getFishWidth() {
        return frontTail.x + ovalDim.width;
    }

    public Point getFishPosition() {
        return new Point(fishPosition.x, fishPosition.y);
    }

    public boolean getFishHasBeenInitiallyPositioned() {
        return fishHasBeenInitiallyPositioned;
    }

    public Point getFishNosePosition() {
        return new Point(fishPosition.x + frontTail.x + ovalDim.width,
            fishPosition.y + (ovalDim.height / 2));
    }

    public abstract Color getFishColor();

    public void paint(Graphics g) {
        paintWithSpecialColors(g, fishColor, Color.black);
    }

    public void paintWithSpecialColors(Graphics g, Color bodyColor, Color eyeColor) {

        g.translate(fishPosition.x, fishPosition.y);

        g.setColor(bodyColor);
        if (fishIsSwimming) {
            if (holdTailPositionCounter < maxPaintsToHoldTailInOnePosition) {
                ++holdTailPositionCounter;
            }
            else {
                holdTailPositionCounter = 0;
                ++currentSwimmingTailPos;
                if (currentSwimmingTailPos == swimmingTailPositionCount) {
                    currentSwimmingTailPos = 0;
                }
            }
            g.fillPolygon(swimmingTail[currentSwimmingTailPos]);
        }
        else {
            g.fillPolygon(staticTail);
        }

        g.fillOval(ovalPos.x, ovalPos.y, ovalDim.width, ovalDim.height);

        g.setColor(eyeColor);
        g.fillOval(eyePos.x, eyePos.y, eyeDim.width, eyeDim.height);

        g.translate(0 - fishPosition.x, 0 - fishPosition.y);
    }

    public void drawFishOutline(Graphics g, int x, int y) {

        g.translate(x, y);

        g.drawPolygon(staticTail);

        g.drawOval(ovalPos.x, ovalPos.y, ovalDim.width, ovalDim.height);

        g.drawOval(eyePos.x, eyePos.y, eyeDim.width, eyeDim.height);

        g.translate(0 - x, 0 - y);
    }
}