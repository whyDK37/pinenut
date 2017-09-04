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
 * This class is the canvas upon which is drawn the graphical
 * depiction of the garbage collection process shown in
 * the garbage collect mode. All this drawing is done by
 * the paint() method of this class. This class also implements
 * the functionality of the garbage collection process.
 *
 * @author Bill Venners
 */
public class GarbageCollectCanvas extends Canvas {

  private final int poolImageInsets = 5;
  private final int localVarStringMargin = 5;
  // State variables for the garbage collector
  private final int garbageCollectorHasNotStarted = 0;
  private final int startingAtYellowLocalVariableRoot = 1;
  private final int traversingFromYellowLocalVariableRoot = 2;
  private final int doneWithYellowLocalVariableRoot = 3;
  private final int startingAtBlueLocalVariableRoot = 4;
  private final int traversingFromBlueLocalVariableRoot = 5;
  private final int doneWithBlueLocalVariableRoot = 6;
  private final int startingAtRedLocalVariableRoot = 7;
  private final int traversingFromRedLocalVariableRoot = 8;
  private final int doneWithRedLocalVariableRoot = 9;
  private final int readyToSweepUnmarkedFish = 10;
  private final int doneSweepingUnmarkedFish = 11;
  private final int garbageCollectorIsDone = 12;
  private GCHeap gcHeap;
  private LocalVariables localVars;
  private HeapOfFishTextArea controlPanelTextArea;
  private Color currentGCMarkNodeColor = Color.magenta;
  private int localVarRectWidth;
  private int localVarRectHeight;
  private int xLocalVarRectStart;
  private int yYellowFishLocalVarStart;
  private int yBlueFishLocalVarStart;
  private int yRedFishLocalVarStart;
  // Fish area is just to the left of the local variables.
  private int xFishAreaStart;
  private int currentGCState = garbageCollectorHasNotStarted;

  private boolean fishAreBeingMarked;
  private int currentFishBeingMarked;

  private boolean yellowFishLocalVarIsCurrentGCMarkNode;
  private boolean blueFishLocalVarIsCurrentGCMarkNode;
  private boolean redFishLocalVarIsCurrentGCMarkNode;

  private Color yellowFishLocalVarLineColor;
  private Color blueFishLocalVarLineColor;
  private Color redFishLocalVarLineColor;

  GarbageCollectCanvas(GCHeap heap, LocalVariables locVars, HeapOfFishTextArea ta) {
    setBackground(Color.blue);
    gcHeap = heap;
    localVars = locVars;
    controlPanelTextArea = ta;
  }

  public void nextGCStep() {

    switch (currentGCState) {

      case garbageCollectorHasNotStarted:
        yellowFishLocalVarIsCurrentGCMarkNode = true;
        currentGCState = startingAtYellowLocalVariableRoot;
        controlPanelTextArea.setText(HeapOfFishStrings.traversingYellowRoot);
        break;

      case startingAtYellowLocalVariableRoot:

        yellowFishLocalVarIsCurrentGCMarkNode = false;
        if (localVars.yellowFish != 0) {

          ObjectHandle oh = gcHeap.getObjectHandle(localVars.yellowFish);
          yellowFishLocalVarIsCurrentGCMarkNode = false;
          oh.myColor = Color.gray;
          yellowFishLocalVarLineColor = Color.gray;
          currentFishBeingMarked = localVars.yellowFish;
          fishAreBeingMarked = true;
          currentGCState = traversingFromYellowLocalVariableRoot;
        } else {
          blueFishLocalVarIsCurrentGCMarkNode = true;
          currentGCState = startingAtBlueLocalVariableRoot;
          controlPanelTextArea.setText(HeapOfFishStrings.traversingBlueRoot);
        }
        break;

      case traversingFromYellowLocalVariableRoot:

        boolean doneWithThisTree = traverseNextFishNode();
        if (doneWithThisTree) {
          ObjectHandle oh = gcHeap.getObjectHandle(localVars.yellowFish);
          yellowFishLocalVarLineColor = Color.black;
          oh.myColor = Color.black;
          fishAreBeingMarked = false;
          yellowFishLocalVarIsCurrentGCMarkNode = true;
          currentGCState = doneWithYellowLocalVariableRoot;
          controlPanelTextArea.setText(HeapOfFishStrings.doneWithYellowRoot);
        }
        break;

      case doneWithYellowLocalVariableRoot:

        yellowFishLocalVarIsCurrentGCMarkNode = false;
        blueFishLocalVarIsCurrentGCMarkNode = true;
        currentGCState = startingAtBlueLocalVariableRoot;
        controlPanelTextArea.setText(HeapOfFishStrings.traversingBlueRoot);
        break;

      case startingAtBlueLocalVariableRoot:

        blueFishLocalVarIsCurrentGCMarkNode = false;
        if (localVars.blueFish != 0) {

          ObjectHandle oh = gcHeap.getObjectHandle(localVars.blueFish);
          blueFishLocalVarIsCurrentGCMarkNode = false;
          oh.myColor = Color.gray;
          blueFishLocalVarLineColor = Color.gray;
          currentFishBeingMarked = localVars.blueFish;
          fishAreBeingMarked = true;
          currentGCState = traversingFromBlueLocalVariableRoot;
        } else {
          redFishLocalVarIsCurrentGCMarkNode = true;
          currentGCState = startingAtRedLocalVariableRoot;
          controlPanelTextArea.setText(HeapOfFishStrings.traversingRedRoot);
        }
        break;

      case traversingFromBlueLocalVariableRoot:

        doneWithThisTree = traverseNextFishNode();
        if (doneWithThisTree) {
          ObjectHandle oh = gcHeap.getObjectHandle(localVars.blueFish);
          blueFishLocalVarLineColor = Color.black;
          oh.myColor = Color.black;
          fishAreBeingMarked = false;
          blueFishLocalVarIsCurrentGCMarkNode = true;
          currentGCState = doneWithBlueLocalVariableRoot;
          controlPanelTextArea.setText(HeapOfFishStrings.doneWithBlueRoot);
        }
        break;

      case doneWithBlueLocalVariableRoot:

        blueFishLocalVarIsCurrentGCMarkNode = false;
        redFishLocalVarIsCurrentGCMarkNode = true;
        currentGCState = startingAtRedLocalVariableRoot;
        controlPanelTextArea.setText(HeapOfFishStrings.traversingRedRoot);
        break;

      case startingAtRedLocalVariableRoot:

        redFishLocalVarIsCurrentGCMarkNode = false;
        if (localVars.redFish != 0) {

          ObjectHandle oh = gcHeap.getObjectHandle(localVars.redFish);
          redFishLocalVarIsCurrentGCMarkNode = false;
          oh.myColor = Color.gray;
          redFishLocalVarLineColor = Color.gray;
          currentFishBeingMarked = localVars.redFish;
          fishAreBeingMarked = true;
          currentGCState = traversingFromRedLocalVariableRoot;
        } else {
          currentGCState = readyToSweepUnmarkedFish;
          controlPanelTextArea.setText(HeapOfFishStrings.readyToSweepUnmarkedFish);
        }
        break;

      case traversingFromRedLocalVariableRoot:

        doneWithThisTree = traverseNextFishNode();
        if (doneWithThisTree) {
          ObjectHandle oh = gcHeap.getObjectHandle(localVars.redFish);
          redFishLocalVarLineColor = Color.black;
          oh.myColor = Color.black;
          fishAreBeingMarked = false;
          redFishLocalVarIsCurrentGCMarkNode = true;
          currentGCState = doneWithRedLocalVariableRoot;
          controlPanelTextArea.setText(HeapOfFishStrings.doneWithRedRoot);
        }
        break;

      case doneWithRedLocalVariableRoot:

        redFishLocalVarIsCurrentGCMarkNode = false;
        currentGCState = readyToSweepUnmarkedFish;
        controlPanelTextArea.setText(HeapOfFishStrings.readyToSweepUnmarkedFish);
        break;

      case readyToSweepUnmarkedFish:

        int objectsFreedCount = 0;
        for (int i = 0; i < gcHeap.getHandlePoolSize(); ++i) {
          ObjectHandle oh = gcHeap.getObjectHandle(i + 1);
          if (!oh.free && oh.myColor == Color.white) {
            gcHeap.freeObject(i + 1);
            ++objectsFreedCount;
          }
        }
        currentGCState = doneSweepingUnmarkedFish;
        String doneSweepingText = HeapOfFishStrings.sweptFish0 + objectsFreedCount
                + HeapOfFishStrings.sweptFish1;
        controlPanelTextArea.setText(doneSweepingText);
        break;

      case doneSweepingUnmarkedFish:
        currentGCState = garbageCollectorIsDone;
        controlPanelTextArea.setText(HeapOfFishStrings.garbageCollectionDone);
        break;

      case garbageCollectorIsDone:
      default:
        break;
    }
  }

  // Returns true if done with this tree.
  private boolean traverseNextFishNode() {
    ObjectHandle oh = gcHeap.getObjectHandle(currentFishBeingMarked);

    int myFriendIndex = gcHeap.getObjectPool(oh.objectPos);

    if ((myFriendIndex != 0) && (oh.myFriendLineColor == Color.white)) {
      oh.myFriendLineColor = Color.gray;
      ObjectHandle myFriend = gcHeap.getObjectHandle(myFriendIndex);
      myFriend.previousNodeInGCTraversalIsAFish = true;
      myFriend.previousFishInGCTraversal = currentFishBeingMarked;
      if (myFriend.myColor == Color.white) {
        myFriend.myColor = Color.gray;
      }
      currentFishBeingMarked = myFriendIndex;
      return false;
    } else if (oh.fish.getFishColor() == Color.yellow) {
      if (oh.previousNodeInGCTraversalIsAFish) {
        traverseBackFromGrayLine(oh.previousFishInGCTraversal);
        return false;
      }
      return true;
    }

    int myLunchIndex = gcHeap.getObjectPool(oh.objectPos + 1);

    if ((myLunchIndex != 0) && (oh.myLunchLineColor == Color.white)) {
      oh.myLunchLineColor = Color.gray;
      ObjectHandle myLunch = gcHeap.getObjectHandle(myLunchIndex);
      myLunch.previousNodeInGCTraversalIsAFish = true;
      myLunch.previousFishInGCTraversal = currentFishBeingMarked;
      if (myLunch.myColor == Color.white) {
        myLunch.myColor = Color.gray;
      }
      currentFishBeingMarked = myLunchIndex;
      return false;
    } else if (oh.fish.getFishColor() == Color.cyan) {
      if (oh.previousNodeInGCTraversalIsAFish) {
        traverseBackFromGrayLine(oh.previousFishInGCTraversal);
        return false;
      }
      return true;
    }

    int mySnackIndex = gcHeap.getObjectPool(oh.objectPos + 2);

    if ((mySnackIndex != 0) && (oh.mySnackLineColor == Color.white)) {
      oh.mySnackLineColor = Color.gray;
      ObjectHandle mySnack = gcHeap.getObjectHandle(mySnackIndex);
      mySnack.previousNodeInGCTraversalIsAFish = true;
      mySnack.previousFishInGCTraversal = currentFishBeingMarked;
      if (mySnack.myColor == Color.white) {
        mySnack.myColor = Color.gray;
      }
      currentFishBeingMarked = mySnackIndex;
      return false;
    } else if (oh.previousNodeInGCTraversalIsAFish) {
      traverseBackFromGrayLine(oh.previousFishInGCTraversal);
      return false;
    }

    return true;
  }

  private void traverseBackFromGrayLine(int fishObjectHandle) {

    ObjectHandle oh = gcHeap.getObjectHandle(fishObjectHandle);

    int myFriendIndex = gcHeap.getObjectPool(oh.objectPos);

    if ((myFriendIndex != 0) && (oh.myFriendLineColor == Color.gray)) {
      ObjectHandle myFriend = gcHeap.getObjectHandle(myFriendIndex);
      myFriend.previousNodeInGCTraversalIsAFish = false;
      myFriend.myColor = Color.black;
      oh.myFriendLineColor = Color.black;
      currentFishBeingMarked = fishObjectHandle;
      return;
    }

    if (oh.fish.getFishColor() == Color.yellow) {
      return; // exception condition
    }

    int myLunchIndex = gcHeap.getObjectPool(oh.objectPos + 1);

    if ((myLunchIndex != 0) && (oh.myLunchLineColor == Color.gray)) {
      ObjectHandle myLunch = gcHeap.getObjectHandle(myLunchIndex);
      myLunch.previousNodeInGCTraversalIsAFish = false;
      myLunch.myColor = Color.black;
      oh.myLunchLineColor = Color.black;
      currentFishBeingMarked = fishObjectHandle;
      return;
    }

    if (oh.fish.getFishColor() == Color.cyan) {
      return; // exception condition
    }

    int mySnackIndex = gcHeap.getObjectPool(oh.objectPos + 2);

    if ((mySnackIndex != 0) && (oh.mySnackLineColor == Color.gray)) {
      ObjectHandle mySnack = gcHeap.getObjectHandle(mySnackIndex);
      mySnack.previousNodeInGCTraversalIsAFish = false;
      mySnack.myColor = Color.black;
      oh.mySnackLineColor = Color.black;
      currentFishBeingMarked = fishObjectHandle;
      return;
    }
  }

  public void resetGCState() {

    for (int i = 0; i < gcHeap.getHandlePoolSize(); ++i) {
      ObjectHandle oh = gcHeap.getObjectHandle(i + 1);
      if (!oh.free) {
        oh.myColor = Color.white;
        oh.previousNodeInGCTraversalIsAFish = false;
        oh.previousFishInGCTraversal = 0;
        oh.myFriendLineColor = Color.white;
        oh.myLunchLineColor = Color.white;
        oh.mySnackLineColor = Color.white;
      }
    }
    currentGCState = garbageCollectorHasNotStarted;
    fishAreBeingMarked = false;
    yellowFishLocalVarIsCurrentGCMarkNode = false;
    blueFishLocalVarIsCurrentGCMarkNode = false;
    redFishLocalVarIsCurrentGCMarkNode = false;
    yellowFishLocalVarLineColor = Color.white;
    blueFishLocalVarLineColor = Color.white;
    redFishLocalVarLineColor = Color.white;
    controlPanelTextArea.setText(HeapOfFishStrings.garbageCollectInstructions);
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
    if (currentGCState == garbageCollectorHasNotStarted || currentGCState == garbageCollectorIsDone) {
      g.setColor(Color.yellow);
    } else if (yellowFishLocalVarIsCurrentGCMarkNode) {
      g.setColor(currentGCMarkNodeColor);
    } else {
      g.setColor(Color.white);
    }
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
    if (currentGCState == garbageCollectorHasNotStarted || currentGCState == garbageCollectorIsDone) {
      g.setColor(Color.cyan);
    } else if (blueFishLocalVarIsCurrentGCMarkNode) {
      g.setColor(currentGCMarkNodeColor);
    } else {
      g.setColor(Color.white);
    }
    g.fillRect(xStart, yStart, localVarRectWidth, localVarRectHeight);
    yBlueFishLocalVarStart = yStart;

    xStart = ((localVarRectWidth - blueFishStringWidth) / 2) + localVarStringMargin;
    yStart += localVarStringMargin + fm.getAscent();
    g.setColor(Color.black);
    g.drawString(HeapOfFishStrings.blueFishLocalVar, xStart, yStart);

    // draw "RedFish rf" on a red rectangle
    xStart = localVarStringMargin;
    yStart = yLocalVarsAreaStart + (3 * localVarRectHeight);
    if (currentGCState == garbageCollectorHasNotStarted || currentGCState == garbageCollectorIsDone) {
      g.setColor(Color.red);
    } else if (redFishLocalVarIsCurrentGCMarkNode) {
      g.setColor(currentGCMarkNodeColor);
    } else {
      g.setColor(Color.white);
    }
    g.fillRect(xStart, yStart, localVarRectWidth, localVarRectHeight);
    yRedFishLocalVarStart = yStart;

    xStart = ((localVarRectWidth - redFishStringWidth) / 2) + localVarStringMargin;
    yStart += localVarStringMargin + fm.getAscent();
    g.setColor(Color.black);
    g.drawString(HeapOfFishStrings.redFishLocalVar, xStart, yStart);

    if (localVars.yellowFish != 0) {
      g.setColor(Color.blue);
      if (currentGCState == garbageCollectorHasNotStarted || currentGCState == garbageCollectorIsDone) {
        g.setXORMode(Color.yellow);
      } else {
        g.setXORMode(yellowFishLocalVarLineColor);
      }
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
      if (currentGCState == garbageCollectorHasNotStarted || currentGCState == garbageCollectorIsDone) {
        g.setXORMode(Color.cyan);
      } else {
        g.setXORMode(blueFishLocalVarLineColor);
      }
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
      if (currentGCState == garbageCollectorHasNotStarted || currentGCState == garbageCollectorIsDone) {
        g.setXORMode(Color.red);
      } else {
        g.setXORMode(redFishLocalVarLineColor);
      }
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
        if (currentGCState == garbageCollectorHasNotStarted || currentGCState == garbageCollectorIsDone) {
          fishIcon.paint(g);
        } else {
          if (fishAreBeingMarked && currentFishBeingMarked == i + 1) {
            fishIcon.paintWithSpecialColors(g, currentGCMarkNodeColor, Color.black);
          } else {
            Color eyeColor = Color.black;
            if (oh.myColor == Color.black) {
              eyeColor = Color.white;
            }
            fishIcon.paintWithSpecialColors(g, oh.myColor, eyeColor);
          }
        }

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
          if (currentGCState != garbageCollectorHasNotStarted && currentGCState != garbageCollectorIsDone) {
            g.setPaintMode();
            g.setColor(Color.blue);
            g.setXORMode(oh.myFriendLineColor);
          }
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
          if (currentGCState != garbageCollectorHasNotStarted && currentGCState != garbageCollectorIsDone) {
            g.setPaintMode();
            g.setColor(Color.blue);
            g.setXORMode(oh.myLunchLineColor);
          }
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
          if (currentGCState != garbageCollectorHasNotStarted && currentGCState != garbageCollectorIsDone) {
            g.setPaintMode();
            g.setColor(Color.blue);
            g.setXORMode(oh.mySnackLineColor);
          }
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