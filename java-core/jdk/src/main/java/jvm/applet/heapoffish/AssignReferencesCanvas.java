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
* This class is the panel on which is drawn the local variable rectangles, all
* the allocated fish, and the lines showing their connections. All this drawing
* is done by the paint() method of this class.
*
* @author  Bill Venners
*/
public class AssignReferencesCanvas extends Canvas {

    protected GCHeap gcHeap;
    protected LocalVariables localVars;
    protected HeapOfFishTextArea controlPanelTextArea;

    protected final int poolImageInsets = 5;
    protected final int localVarStringMargin = 5;

    protected int localVarRectWidth;
    protected int localVarRectHeight;
    protected int xLocalVarRectStart;
    protected int yYellowFishLocalVarStart;
    protected int yBlueFishLocalVarStart;
    protected int yRedFishLocalVarStart;

    // Fish area is just to the left of the local variables.
    protected int xFishAreaStart;
    public Dimension minimumSize() {
        return new Dimension(500, 240);
    }

    public Dimension preferredSize() {
        return new Dimension(500, 240);
    }
    public void paint(Graphics g) {

        Font font = g.getFont();
        FontMetrics fm = getFontMetrics(font);

        int localVarsStringWidth = fm.stringWidth(HeapOfFishStrings.localVariables);
        int redFishStringWidth = fm.stringWidth(HeapOfFishStrings.redFishLocalVar);
        int blueFishStringWidth = fm.stringWidth(HeapOfFishStrings.blueFishLocalVar);
        int yellowFishStringWidth = fm.stringWidth(HeapOfFishStrings.yellowFishLocalVar);

        localVarRectWidth = localVarsStringWidth;
        if (redFishStringWidth > localVarRectWidth) {
            localVarRectWidth = redFishStringWidth;
        }
        if (blueFishStringWidth > localVarRectWidth) {
            localVarRectWidth = blueFishStringWidth;
        }
        if (yellowFishStringWidth > localVarRectWidth) {
            localVarRectWidth = yellowFishStringWidth;
        }

        localVarRectWidth += 2 * localVarStringMargin;
        xFishAreaStart = localVarRectWidth + (2 * localVarStringMargin);

        localVarRectHeight = fm.getAscent() + fm.getDescent() + 2 * localVarStringMargin;

        Dimension dim = size();
        int yLocalVarsAreaStart = (dim.height - (4 * localVarRectHeight)) / 2;
        if (yLocalVarsAreaStart < 0) {
            yLocalVarsAreaStart = 0;
        }

        // draw "Local Variables"
        int xStart = ((localVarRectWidth - localVarsStringWidth) / 2) + localVarStringMargin;
        int yStart = yLocalVarsAreaStart + localVarStringMargin + fm.getAscent();
        g.setColor(Color.white);
        g.drawString(HeapOfFishStrings.localVariables, xStart, yStart);

        // draw "YellowFish yf" on a yellow rectangle
        xStart = localVarStringMargin;
        yStart = yLocalVarsAreaStart + localVarRectHeight;
        g.setColor(Color.yellow);
        g.fillRect(xStart, yStart, localVarRectWidth, localVarRectHeight);
        xLocalVarRectStart = xStart;
        yYellowFishLocalVarStart = yStart;

        xStart = ((localVarRectWidth - yellowFishStringWidth) / 2) + localVarStringMargin;
        yStart += localVarStringMargin + fm.getAscent();
        g.setColor(Color.black);
        g.drawString(HeapOfFishStrings.yellowFishLocalVar, xStart, yStart);

        // draw "BlueFish bf" on a cyan rectangle
        xStart = localVarStringMargin;
        yStart = yLocalVarsAreaStart + (2 * localVarRectHeight);
        g.setColor(Color.cyan);
        g.fillRect(xStart, yStart, localVarRectWidth, localVarRectHeight);
        yBlueFishLocalVarStart = yStart;

        xStart = ((localVarRectWidth - blueFishStringWidth) / 2) + localVarStringMargin;
        yStart += localVarStringMargin + fm.getAscent();
        g.setColor(Color.black);
        g.drawString(HeapOfFishStrings.blueFishLocalVar, xStart, yStart);

        // draw "RedFish rf" on a red rectangle
        xStart = localVarStringMargin;
        yStart = yLocalVarsAreaStart + (3 * localVarRectHeight);
        g.setColor(Color.red);
        g.fillRect(xStart, yStart, localVarRectWidth, localVarRectHeight);
        yRedFishLocalVarStart = yStart;

        xStart = ((localVarRectWidth - redFishStringWidth) / 2) + localVarStringMargin;
        yStart += localVarStringMargin + fm.getAscent();
        g.setColor(Color.black);
        g.drawString(HeapOfFishStrings.redFishLocalVar, xStart, yStart);

        if (localVars.yellowFish != 0) {
            g.setColor(Color.blue);
            g.setXORMode(Color.yellow);
            ObjectHandle yf = gcHeap.getObjectHandle(localVars.yellowFish);
            int xLineStart = xLocalVarRectStart + localVarRectWidth;
            int yLineStart = yYellowFishLocalVarStart + (localVarRectHeight / 2);
            g.drawLine(xLineStart, yLineStart, yf.fish.getFishPosition().x, yf.fish.getFishPosition().y);
            if (localVars.yellowLineStart == null) {
                localVars.yellowLineStart = new Point(0, 0);
                localVars.yellowLineEnd = new Point(0, 0);
            }
            localVars.yellowLineStart.x = xLineStart;
            localVars.yellowLineStart.y = yLineStart;
            localVars.yellowLineEnd.x = yf.fish.getFishPosition().x;
            localVars.yellowLineEnd.y = yf.fish.getFishPosition().y;
            g.setPaintMode();
        }

        if (localVars.blueFish != 0) {
            g.setColor(Color.blue);
            g.setXORMode(Color.cyan);
            ObjectHandle bf = gcHeap.getObjectHandle(localVars.blueFish);
            int xLineStart = xLocalVarRectStart + localVarRectWidth;
            int yLineStart = yBlueFishLocalVarStart + (localVarRectHeight / 2);
            g.drawLine(xLineStart, yLineStart, bf.fish.getFishPosition().x, bf.fish.getFishPosition().y);
            if (localVars.blueLineStart == null) {
                localVars.blueLineStart = new Point(0, 0);
                localVars.blueLineEnd = new Point(0, 0);
            }
            localVars.blueLineStart.x = xLineStart;
            localVars.blueLineStart.y = yLineStart;
            localVars.blueLineEnd.x = bf.fish.getFishPosition().x;
            localVars.blueLineEnd.y = bf.fish.getFishPosition().y;
            g.setPaintMode();
        }

        if (localVars.redFish != 0) {
            g.setColor(Color.blue);
            g.setXORMode(Color.red);
            ObjectHandle rf = gcHeap.getObjectHandle(localVars.redFish);
            int xLineStart = xLocalVarRectStart + localVarRectWidth;
            int yLineStart = yRedFishLocalVarStart + (localVarRectHeight / 2);
            g.drawLine(xLineStart, yLineStart, rf.fish.getFishPosition().x, rf.fish.getFishPosition().y);
            if (localVars.redLineStart == null) {
                localVars.redLineStart = new Point(0, 0);
                localVars.redLineEnd = new Point(0, 0);
            }
            localVars.redLineStart.x = xLineStart;
            localVars.redLineStart.y = yLineStart;
            localVars.redLineEnd.x = rf.fish.getFishPosition().x;
            localVars.redLineEnd.y = rf.fish.getFishPosition().y;
            g.setPaintMode();
        }

        // Figure out how many slots will be in each of three columns of slots where
        // new fish are put.
        int columnCount = 3;
        int slotsPerColumn = gcHeap.getHandlePoolSize() / columnCount;
        if (gcHeap.getHandlePoolSize() % columnCount > 0) {
            ++slotsPerColumn;
        }
        int fishAreaWidth = dim.width - xFishAreaStart;
        int slotWidth = fishAreaWidth / columnCount;
        int slotHeight = dim.height / slotsPerColumn;

        for (int i = 0; i < gcHeap.getHandlePoolSize(); ++i) {
            ObjectHandle oh = gcHeap.getObjectHandle(i + 1);
            if (!oh.free) {

                FishIcon fishIcon = oh.fish;
                if (!fishIcon.getFishHasBeenInitiallyPositioned()) {
                    int column = i / slotsPerColumn;
                    int row = i % slotsPerColumn;
                    int xFishPosition = (int) ((double) (slotWidth - fishIcon.getFishWidth()) * Math.random());
                    if (xFishPosition < 0) {
                        xFishPosition = 0;
                    }
                    xFishPosition += xFishAreaStart + (column * slotWidth);
                    int yFishPosition = (slotHeight - fishIcon.getFishHeight()) / 2;
                    if (yFishPosition < 0) {
                        yFishPosition = 0;
                    }
                    yFishPosition += row * slotHeight;
                    fishIcon.moveFish(xFishPosition, yFishPosition);
                }
                fishIcon.paint(g);

                // Draw any lines connecting fish.
                g.setColor(Color.blue);
                g.setXORMode(fishIcon.getFishColor());
                Point fishNose = fishIcon.getFishNosePosition();

                oh.gotFriend = false;
                oh.myFriendLineStart = null;
                oh.myFriendLineEnd = null;

                oh.gotLunch = false;
                oh.myLunchLineStart = null;
                oh.myLunchLineEnd = null;

                oh.gotSnack = false;
                oh.mySnackLineStart = null;
                oh.mySnackLineEnd = null;

                int myFriendIndex = gcHeap.getObjectPool(oh.objectPos);

                if (myFriendIndex != 0) {
                    ObjectHandle myFriend = gcHeap.getObjectHandle(myFriendIndex);
                    g.drawLine(fishNose.x, fishNose.y, myFriend.fish.getFishPosition().x, myFriend.fish.getFishPosition().y);
                    oh.gotFriend = true;
                    oh.myFriendLineStart = new Point(fishNose.x, fishNose.y);
                    oh.myFriendLineEnd = new Point(myFriend.fish.getFishPosition().x, myFriend.fish.getFishPosition().y);
                }

                if (fishIcon.getFishColor() == Color.yellow) {
                    g.setPaintMode();
                    continue;
                }

                int myLunchIndex = gcHeap.getObjectPool(oh.objectPos + 1);

                if (myLunchIndex != 0) {
                    ObjectHandle myLunch = gcHeap.getObjectHandle(myLunchIndex);
                    g.drawLine(fishNose.x, fishNose.y, myLunch.fish.getFishPosition().x, myLunch.fish.getFishPosition().y);
                    oh.gotLunch = true;
                    oh.myLunchLineStart = new Point(fishNose.x, fishNose.y);
                    oh.myLunchLineEnd = new Point(myLunch.fish.getFishPosition().x, myLunch.fish.getFishPosition().y);
                }

                if (fishIcon.getFishColor() == Color.cyan) {
                    g.setPaintMode();
                    continue;
                }

                int mySnackIndex = gcHeap.getObjectPool(oh.objectPos + 2);

                if (mySnackIndex != 0) {
                    ObjectHandle mySnack = gcHeap.getObjectHandle(mySnackIndex);
                    g.drawLine(fishNose.x, fishNose.y, mySnack.fish.getFishPosition().x, mySnack.fish.getFishPosition().y);
                    oh.gotSnack = true;
                    oh.mySnackLineStart = new Point(fishNose.x, fishNose.y);
                    oh.mySnackLineEnd = new Point(mySnack.fish.getFishPosition().x, mySnack.fish.getFishPosition().y);
                }

                g.setPaintMode();
            }
        }
    }
}