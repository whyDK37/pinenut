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
* This class is the canvas that displays the user interface
* during the link fish sub-mode of the assign references mode.
* This class contains the code that handles the clicking and
* dragging that links one fish to another.
*
* @author  Bill Venners
*/
public class LinkFishCanvas extends AssignReferencesCanvas {

    private boolean iconClicked = false;
    private boolean yellowLocalVarClicked = false;
    private boolean blueLocalVarClicked = false;
    private boolean redLocalVarClicked = false;

    private Point posOfMouseInsideIconWhenFirstPressed = new Point(0, 0);
    private int objectIndexOfFishIconThatWasClicked;

    private boolean dragging = false;
    private Point currentMouseDragPosition = new Point(0, 0);
    private boolean mouseIsOverAnIconThatCanBeDroppedUpon = false;
    private int objectIndexOfIconThatCanBeDroppedUpon;

    LinkFishCanvas(GCHeap heap, LocalVariables locVars, HeapOfFishTextArea ta) {
        gcHeap = heap;
        localVars = locVars;
        controlPanelTextArea = ta;
    }

    public Dimension minimumSize() {
        return new Dimension(500, 240);
    }

    public Dimension preferredSize() {
        return new Dimension(500, 240);
    }

    public boolean mouseDown(Event evt, int x, int y) {

        // First check to see if the mouse went down inside one of the local variable
        // rectangles.
        if (x >= xLocalVarRectStart && x < xLocalVarRectStart + localVarRectWidth
            && y >= yYellowFishLocalVarStart && y < yYellowFishLocalVarStart + localVarRectHeight) {

            yellowLocalVarClicked = true;
            return true;
        }
        if (x >= xLocalVarRectStart && x < xLocalVarRectStart + localVarRectWidth
            && y >= yBlueFishLocalVarStart && y < yBlueFishLocalVarStart + localVarRectHeight) {

            blueLocalVarClicked = true;
            return true;
        }
        if (x >= xLocalVarRectStart && x < xLocalVarRectStart + localVarRectWidth
            && y >= yRedFishLocalVarStart && y < yRedFishLocalVarStart + localVarRectHeight) {

            redLocalVarClicked = true;
            return true;
        }

        // Find out if the mouse went down inside an icon's hot area. Count down from the
        // top of the heap list so that fish that are drawn later will be found first. This
        // is because if two fish are overlapping, the one later in the array will be
        // drawn second and appear to be on top. The top fish will be found first by this
        // for loop.
        for (int i = gcHeap.getHandlePoolSize() - 1; i >= 0; --i) {
            ObjectHandle oh = gcHeap.getObjectHandle(i + 1);
            if (!oh.free) {
                Point o = oh.fish.getFishPosition();
                if (x >= o.x && x < o.x + oh.fish.getFishWidth() && y >= o.y
                    && y < o.y + oh.fish.getFishHeight()) {

                    iconClicked = true;
                    objectIndexOfFishIconThatWasClicked = i + 1;
                    posOfMouseInsideIconWhenFirstPressed.x = x - o.x;
                    posOfMouseInsideIconWhenFirstPressed.y = y - o.y;
                    break;
                }
            }
        }

        return true;
    }

    public boolean mouseUp(Event evt, int x, int y) {

        if (!iconClicked && !yellowLocalVarClicked && !blueLocalVarClicked
            && !redLocalVarClicked) {
            return true;
        }

        if (!iconClicked) {

            Color colorOfClickedLocalVar = Color.yellow;
            if (blueLocalVarClicked) {
                colorOfClickedLocalVar = Color.cyan;
            }
            else if (redLocalVarClicked) {
                colorOfClickedLocalVar = Color.red;
            }

            if (dragging) {
                dragging = false;
                // Clear old line.
                Graphics g = getGraphics();
                g.setColor(Color.blue);
                g.setXORMode(colorOfClickedLocalVar);

                int xLineStart = xLocalVarRectStart + localVarRectWidth;
                int yLineStart = yYellowFishLocalVarStart + (localVarRectHeight / 2);
                if (blueLocalVarClicked) {
                    yLineStart = yBlueFishLocalVarStart + (localVarRectHeight / 2);
                }
                else if (redLocalVarClicked) {
                    yLineStart = yRedFishLocalVarStart + (localVarRectHeight / 2);
                }

                if (!mouseIsOverAnIconThatCanBeDroppedUpon) {

                    // Clear old line
                    g.drawLine(xLineStart, yLineStart, currentMouseDragPosition.x,
                        currentMouseDragPosition.y);
                }
                else {
                    ObjectHandle oh = gcHeap.getObjectHandle(objectIndexOfIconThatCanBeDroppedUpon);

                    // Clear the rectangle
                    g.drawRect(oh.fish.getFishPosition().x, oh.fish.getFishPosition().y, oh.fish.getFishWidth(),
                        oh.fish.getFishHeight());

                    // Clear the line between the nose of the from fish and the top of the tail of
                    // of the to fish.
                    g.drawLine(xLineStart, yLineStart, oh.fish.getFishPosition().x,
                        oh.fish.getFishPosition().y);

                    mouseIsOverAnIconThatCanBeDroppedUpon = false;

                    Point o = oh.fish.getFishPosition();
                    if (x >= o.x && x < o.x + oh.fish.getFishWidth() && y >= o.y
                        && y < o.y + oh.fish.getFishHeight()) {

                        if (oh.fish.getFishColor() == colorOfClickedLocalVar) {

                            // Set the local variable to equal the dropped upon
                            // fish object.
                            if (yellowLocalVarClicked) {
                                localVars.yellowFish = objectIndexOfIconThatCanBeDroppedUpon;
                            }
                            else if (blueLocalVarClicked) {
                                localVars.blueFish = objectIndexOfIconThatCanBeDroppedUpon;
                            }
                            else if (redLocalVarClicked) {
                                localVars.redFish = objectIndexOfIconThatCanBeDroppedUpon;
                            }
                            repaint();
                        }
                    }
                }
            }

            yellowLocalVarClicked = false;
            blueLocalVarClicked = false;
            redLocalVarClicked = false;

            return true;
        }

        ObjectHandle fishObjectThatWasClicked = gcHeap.getObjectHandle(objectIndexOfFishIconThatWasClicked);
        FishIcon fishIconThatWasClicked = fishObjectThatWasClicked.fish;
        Color colorOfClickedFish = fishObjectThatWasClicked.fish.getFishColor();

        if (dragging) {
            dragging = false;
            // Clear old line.
            Graphics g = getGraphics();
            g.setColor(Color.blue);
            g.setXORMode(colorOfClickedFish);

            Point lineStart = fishIconThatWasClicked.getFishNosePosition();

            if (!mouseIsOverAnIconThatCanBeDroppedUpon) {

                // Clear old line
                g.drawLine(lineStart.x, lineStart.y, currentMouseDragPosition.x,
                    currentMouseDragPosition.y);
            }
            else {
                ObjectHandle oh = gcHeap.getObjectHandle(objectIndexOfIconThatCanBeDroppedUpon);

                // Clear the rectangle
                g.drawRect(oh.fish.getFishPosition().x, oh.fish.getFishPosition().y, oh.fish.getFishWidth(),
                    oh.fish.getFishHeight());

                // Clear the line between the nose of the from fish and the top of the tail of
                // of the to fish.
                g.drawLine(lineStart.x, lineStart.y, oh.fish.getFishPosition().x,
                    oh.fish.getFishPosition().y);

                mouseIsOverAnIconThatCanBeDroppedUpon = false;

                Point o = oh.fish.getFishPosition();
                if (x >= o.x && x < o.x + oh.fish.getFishWidth() && y >= o.y
                    && y < o.y + oh.fish.getFishHeight()) {

                    // If they dropped on the same fish they started from, don't link.
                    if ((objectIndexOfIconThatCanBeDroppedUpon != objectIndexOfFishIconThatWasClicked)
                        && fishCanLink(fishIconThatWasClicked, oh.fish)) {
                        int offset = getInstanceVariableOffset(fishIconThatWasClicked, oh.fish);

                        // Set the clicked upon fish variable to equal the dropped upon
                        // fish object.
                        gcHeap.setObjectPool(fishObjectThatWasClicked.objectPos + offset, objectIndexOfIconThatCanBeDroppedUpon);
                        repaint();
                    }
                }
            }
        }

        yellowLocalVarClicked = false;
        blueLocalVarClicked = false;
        redLocalVarClicked = false;
        iconClicked = false;

        return true;
    }

    public boolean mouseDrag(Event evt, int x, int y) {

        if (!iconClicked && !yellowLocalVarClicked && !blueLocalVarClicked
            && !redLocalVarClicked) {
            return true;
        }

        if (yellowLocalVarClicked || blueLocalVarClicked || redLocalVarClicked) {

            Graphics g = getGraphics();
            g.setColor(Color.blue);
            Color colorOfClickedLocalVar = Color.yellow;
            int xLineStart = xLocalVarRectStart + localVarRectWidth;
            int yLineStart = yYellowFishLocalVarStart + (localVarRectHeight / 2);
            if (blueLocalVarClicked) {
                colorOfClickedLocalVar = Color.cyan;
                yLineStart = yBlueFishLocalVarStart + (localVarRectHeight / 2);
            }
            else if (redLocalVarClicked) {
                colorOfClickedLocalVar = Color.red;
                yLineStart = yRedFishLocalVarStart + (localVarRectHeight / 2);
            }
            g.setXORMode(colorOfClickedLocalVar);

            if (!dragging) {
                dragging = true;
            }
            else {
                // Check to see if the mouse has left an icon that can be dropped upon. If
                // so, need to erase the outline rectangle and erase the line from the nose of
                // the "from" fish to top left corner of outline rectangle of "to" fish.
                if (mouseIsOverAnIconThatCanBeDroppedUpon) {
                    ObjectHandle oh = gcHeap.getObjectHandle(objectIndexOfIconThatCanBeDroppedUpon);
                    Point o = oh.fish.getFishPosition();
                    if (x < o.x || x >= o.x + oh.fish.getFishWidth() || y < o.y
                        || y >= o.y + oh.fish.getFishHeight()) {

                        g.drawRect(oh.fish.getFishPosition().x, oh.fish.getFishPosition().y, oh.fish.getFishWidth(),
                            oh.fish.getFishHeight());

                        // Draw a line between the nose of the from fish and the top of the tail of
                        // of the to fish.
                        g.drawLine(xLineStart, yLineStart, oh.fish.getFishPosition().x,
                            oh.fish.getFishPosition().y);

                        mouseIsOverAnIconThatCanBeDroppedUpon = false;
                    }
                }
                else {
                    // No drop-on-able fish is beneath the mouse, so erase the new line between
                    // the nose of the fromFish and the previous mouse position.
                    g.drawLine(xLineStart, yLineStart, currentMouseDragPosition.x, currentMouseDragPosition.y);
                }
            }

            // Check to see if a fish is beneath the mouse that can be dropped upon.
            for (int i = gcHeap.getHandlePoolSize() - 1; i >= 0; --i) {
                ObjectHandle oh = gcHeap.getObjectHandle(i + 1);
                if (!oh.free) {

                    Point o = oh.fish.getFishPosition();
                    if (x >= o.x && x < o.x + oh.fish.getFishWidth() && y >= o.y
                        && y < o.y + oh.fish.getFishHeight()) {

                        if (!mouseIsOverAnIconThatCanBeDroppedUpon) {

                            if (oh.fish.getFishColor() == colorOfClickedLocalVar) {
                                mouseIsOverAnIconThatCanBeDroppedUpon = true;
                            }

                            if (mouseIsOverAnIconThatCanBeDroppedUpon) {
                                objectIndexOfIconThatCanBeDroppedUpon = i + 1;
                                g.drawRect(oh.fish.getFishPosition().x, oh.fish.getFishPosition().y, oh.fish.getFishWidth(),
                                    oh.fish.getFishHeight());

                                // Draw a line between the nose of the from fish and the top of the tail of
                                // of the to fish.
                                g.drawLine(xLineStart, yLineStart, oh.fish.getFishPosition().x,
                                    oh.fish.getFishPosition().y);
                            }
                        }
                        break;
                    }
                }
            }

            if (!mouseIsOverAnIconThatCanBeDroppedUpon) {
                // No drop-on-able fish is beneath the mouse, so just draw the new line between
                // the nose of the fromFish and the current mouse position.
                g.drawLine(xLineStart, yLineStart, x, y);
            }
            currentMouseDragPosition.x = x;
            currentMouseDragPosition.y = y;

            g.setPaintMode();
            return true;
        }

        FishIcon fishIconThatWasClicked = gcHeap.getObjectHandle(objectIndexOfFishIconThatWasClicked).fish;
        Color colorOfClickedFish = gcHeap.getObjectHandle(objectIndexOfFishIconThatWasClicked).fish.getFishColor();

        // Don't start dragging unless the mouse has moved a threshold number of
        // pixels in x or y.
        if (!dragging) {
            int thresholdPixels = 5;
            Point iconOrigin = fishIconThatWasClicked.getFishPosition();
            int xOriginalClick = iconOrigin.x + posOfMouseInsideIconWhenFirstPressed.x;
            int yOriginalClick = iconOrigin.y + posOfMouseInsideIconWhenFirstPressed.y;
            int xDifference = x - xOriginalClick;
            if (xDifference < 0) {
                xDifference = 0 - xDifference;
            }
            int yDifference = y - yOriginalClick;
            if (yDifference < 0) {
                yDifference = 0 - yDifference;
            }
            if (xDifference < thresholdPixels && yDifference < thresholdPixels) {
                return true;
            }
        }

        Graphics g = getGraphics();
        g.setColor(Color.blue);
        g.setXORMode(colorOfClickedFish);

        Point fishNose = fishIconThatWasClicked.getFishNosePosition();

        if (!dragging) {
            dragging = true;
        }
        else {
            // Check to see if the mouse has left an icon that can be dropped upon. If
            // so, need to erase the outline rectangle and erase the line from the nose of
            // the "from" fish to top left corner of outline rectangle of "to" fish.
            if (mouseIsOverAnIconThatCanBeDroppedUpon) {
                ObjectHandle oh = gcHeap.getObjectHandle(objectIndexOfIconThatCanBeDroppedUpon);
                Point o = oh.fish.getFishPosition();
                if (x < o.x || x >= o.x + oh.fish.getFishWidth() || y < o.y
                    || y >= o.y + oh.fish.getFishHeight()) {

                    g.drawRect(oh.fish.getFishPosition().x, oh.fish.getFishPosition().y, oh.fish.getFishWidth(),
                        oh.fish.getFishHeight());

                    // Draw a line between the nose of the from fish and the top of the tail of
                    // of the to fish.
                    g.drawLine(fishNose.x, fishNose.y, oh.fish.getFishPosition().x,
                        oh.fish.getFishPosition().y);

                    mouseIsOverAnIconThatCanBeDroppedUpon = false;
                }
            }
            else {
                // No drop-on-able fish is beneath the mouse, so erase the new line between
                // the nose of the fromFish and the previous mouse position.
                g.drawLine(fishNose.x, fishNose.y, currentMouseDragPosition.x, currentMouseDragPosition.y);
            }
        }

        // Check to see if a fish is beneath the mouse that can be dropped upon.
        for (int i = gcHeap.getHandlePoolSize() - 1; i >= 0; --i) {
            ObjectHandle oh = gcHeap.getObjectHandle(i + 1);
            if (!oh.free) {

                Point o = oh.fish.getFishPosition();
                if (x >= o.x && x < o.x + oh.fish.getFishWidth() && y >= o.y
                    && y < o.y + oh.fish.getFishHeight()) {

                    if (!mouseIsOverAnIconThatCanBeDroppedUpon) {
                        if (i + 1 == objectIndexOfFishIconThatWasClicked) {
                            // If they are over the same fish they started from, don't
                            // draw a rectangle.
                            break;
                        }

                        mouseIsOverAnIconThatCanBeDroppedUpon = fishCanLink(fishIconThatWasClicked, oh.fish);
                        if (mouseIsOverAnIconThatCanBeDroppedUpon) {
                            objectIndexOfIconThatCanBeDroppedUpon = i + 1;
                            g.drawRect(oh.fish.getFishPosition().x, oh.fish.getFishPosition().y, oh.fish.getFishWidth(),
                                oh.fish.getFishHeight());

                            // Draw a line between the nose of the from fish and the top of the tail of
                            // of the to fish.
                            g.drawLine(fishNose.x, fishNose.y, oh.fish.getFishPosition().x,
                                oh.fish.getFishPosition().y);
                        }
                    }
                    break;
                }
            }
        }

        if (!mouseIsOverAnIconThatCanBeDroppedUpon) {
            // No drop-on-able fish is beneath the mouse, so just draw the new line between
            // the nose of the fromFish and the current mouse position.
            g.drawLine(fishNose.x, fishNose.y, x, y);
        }
        currentMouseDragPosition.x = x;
        currentMouseDragPosition.y = y;

        g.setPaintMode();
        return true;
    }

    private boolean fishCanLink(FishIcon fromFish, FishIcon toFish) {

        // Red fish can link with anything.
        if (fromFish.getFishColor() == Color.red) {
            return true;
        }

        // Blue fish can link with anything except red.
        if (fromFish.getFishColor() == Color.cyan) {
            if (toFish.getFishColor() != Color.red) {
                return true;
            }
            else {
                return false;
            }
        }

        // Yellow fish can only link with yellow fish.
        if (toFish.getFishColor() == Color.yellow) {
            return true;
        }

        return false;
    }

    // getInstanceVariableOffset returns the offset from the beginning of the "from" fish
    // object in the objectPool of the variable to which the "to" fish reference should
    // be assigned. The offset is in number of ints. This assumes that the toFish is
    // a valid fish color for the from fish, e.g., yellow fromFish will only have
    // yellow toFish. The fish objects are defined as:
    //
    // class RedFish {
    //      RedFish myFriend;
    //      BlueFish myLunch;
    //      YellowFish mySnack;
    // }
    //
    // class BlueFish {
    //      BlueFish myFriend;
    //      YellowFish myLunch;
    // }
    //
    // class YellowFish {
    //      YellowFish myFriend;
    // }
    //
    private int getInstanceVariableOffset(FishIcon fromFish, FishIcon toFish) {

        // Red fish can link with anything.
        if (fromFish.getFishColor() == Color.red) {
            if (toFish.getFishColor() == Color.red) {
                return 0;
            }
            else if (toFish.getFishColor() == Color.cyan) {
                return 1;
            }
            else {
                return 2; // yellow fish
            }
        }

        // Blue fish can link with anything except red.
        if (fromFish.getFishColor() == Color.cyan) {
            if (toFish.getFishColor() == Color.cyan) {
                return 0;
            }
            else {
                return 1; // yellow fish
            }
        }

        // Yellow fish can only link with yellow fish.
        return 0;
    }
}