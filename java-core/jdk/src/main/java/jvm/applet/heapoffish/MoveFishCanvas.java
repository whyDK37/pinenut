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
 * during the move fish sub-mode of the assign references mode.
 * This class contains the code that handles the clicking and
 * dragging that moves fish from one place to another.
 *
 * @author Bill Venners
 */
public class MoveFishCanvas extends AssignReferencesCanvas {

  private boolean iconClicked = false;
  private Point posOfMouseInsideIconWhenFirstPressed = new Point(0, 0);
  private int objectIndexOfFishIconThatWasClicked;

  private boolean dragging = false;
  private Point currentMouseDragPosition = new Point(0, 0);

  MoveFishCanvas(GCHeap heap, LocalVariables locVars, HeapOfFishTextArea ta) {
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
          //fishObjectThatWasClicked = oh;
          break;
        }
      }
    }

    return true;
  }

  public boolean mouseUp(Event evt, int x, int y) {

    if (iconClicked == false) {
      return true;
    }

    iconClicked = false;
    FishIcon fishIconThatWasClicked = gcHeap.getObjectHandle(objectIndexOfFishIconThatWasClicked).fish;
    Color colorOfClickedFish = gcHeap.getObjectHandle(objectIndexOfFishIconThatWasClicked).fish.getFishColor();

    if (dragging) {
      dragging = false;
      // Clear old drag icon.
      Graphics g = getGraphics();
      g.setColor(Color.blue);
      g.setXORMode(colorOfClickedFish);
      fishIconThatWasClicked.drawFishOutline(g, currentMouseDragPosition.x,
              currentMouseDragPosition.y);
      fishIconThatWasClicked.moveFish(currentMouseDragPosition.x,
              currentMouseDragPosition.y);
      repaint();
    }

    return true;
  }

  public boolean mouseDrag(Event evt, int x, int y) {

    if (!iconClicked) {
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

    if (!dragging) {
      dragging = true;
    } else {
      // Clear old drag icon.
      fishIconThatWasClicked.drawFishOutline(g, currentMouseDragPosition.x,
              currentMouseDragPosition.y);
    }

    int xNew = x - posOfMouseInsideIconWhenFirstPressed.x;
    int yNew = y - posOfMouseInsideIconWhenFirstPressed.y;

    // Don't let any of the icon go off of either the left
    // or the right side of the Component.
    if (xNew < xFishAreaStart) {
      xNew = xFishAreaStart;
    } else if (xNew + fishIconThatWasClicked.getFishWidth() - 1 > size().width) {
      xNew = size().width - fishIconThatWasClicked.getFishWidth() - 1;
    }

    // Don't let any of the icon go off of either the top
    // or the bottom side of the Component.
    if (yNew < 0) {
      yNew = 0;
    } else if (yNew + fishIconThatWasClicked.getFishHeight() - 1 > size().height) {

      yNew = size().height - fishIconThatWasClicked.getFishHeight() - 1;
    }

    // Then draw the new outline around the icon
    fishIconThatWasClicked.drawFishOutline(g, xNew, yNew);
    currentMouseDragPosition.x = xNew;
    currentMouseDragPosition.y = yNew;

    g.setPaintMode();
    return true;
  }
}