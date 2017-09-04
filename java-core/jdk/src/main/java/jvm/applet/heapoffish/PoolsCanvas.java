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
 * This class is the canvas upon which the user interface for the
 * allocate fish mode is drawn: The object pool, handle pool, and
 * the lines that connect them.
 *
 * @author Bill Venners
 */
public class PoolsCanvas extends Canvas {

  private final int poolImageInsets = 5;
  private GCHeap gcHeap;

  PoolsCanvas(GCHeap heap) {
    gcHeap = heap;
  }

  public Dimension minimumSize() {
    return new Dimension(500, 240);
  }

  public Dimension preferredSize() {
    return new Dimension(500, 240);
  }

  public void paint(Graphics g) {

    // First calculate the positions of the goodies on the canvas based on the width
    // and height of the canvas.
    Dimension dim = size();

    // Divide width into three equal portions. The left portion will hold the handle pool.
    // The right portion will hold the object pool. The middle portion will have arrows
    // that go from valid handles to their respective objects.
    int xHandlePoolPortion = 0;
    int xArrowPortion = dim.width / 3;
    int xObjectPoolPortion = 2 * xArrowPortion;

    Font font = getFont();
    FontMetrics fm = getFontMetrics(font);

    int labelHeight = fm.getAscent() + fm.getDescent() + (2 * poolImageInsets);

    int heightAvailableForPools = dim.height - labelHeight - poolImageInsets;
    int objectPoolIntsCount = gcHeap.getObjectPoolSize();
    int handlePoolIntsCount = gcHeap.getHandlePoolSize() * 2;

    int maxIntsCount = objectPoolIntsCount;
    if (maxIntsCount < handlePoolIntsCount) {
      maxIntsCount = handlePoolIntsCount;
    }

    int yPixelsPerInt = heightAvailableForPools / maxIntsCount;

    int handlePoolHeight = handlePoolIntsCount * yPixelsPerInt;
    int objectPoolHeight = objectPoolIntsCount * yPixelsPerInt;

    int poolsWidth = xArrowPortion - poolImageInsets;

    int xTextStart = poolsWidth - fm.stringWidth(HeapOfFishStrings.handlePool);
    if (xTextStart < 0) {
      xTextStart = 0;
    }
    xTextStart /= 2;

    int yStart = (dim.height - handlePoolHeight - labelHeight - (2 * poolImageInsets)) / 2;
    if (yStart < 0) {
      yStart = 0;
    }
    g.setColor(Color.white);
    g.drawString(HeapOfFishStrings.handlePool, poolImageInsets + xTextStart, poolImageInsets + yStart + fm.getAscent());
    int yHandlePoolRect = yStart + labelHeight;
    g.fillRect(xHandlePoolPortion + poolImageInsets, yHandlePoolRect, poolsWidth, handlePoolHeight);

    xTextStart = poolsWidth - fm.stringWidth(HeapOfFishStrings.objectPool);
    if (xTextStart < 0) {
      xTextStart = 0;
    }
    xTextStart /= 2;

    yStart = (dim.height - objectPoolHeight - labelHeight - (2 * poolImageInsets)) / 2;
    if (yStart < 0) {
      yStart = 0;
    }

    //g.setColor(Color.white);
    g.drawString(HeapOfFishStrings.objectPool, xObjectPoolPortion + xTextStart, poolImageInsets + yStart + fm.getAscent());
    int yObjectPoolRect = yStart + labelHeight;
    //g.setColor(Color.white);
    g.fillRect(xObjectPoolPortion, yObjectPoolRect, poolsWidth, objectPoolHeight);

    // Draw the headers in the object pool
    g.setColor(Color.black);
    int i = 0;
    while (i < objectPoolIntsCount) {

      for (int j = 0; j < yPixelsPerInt; ++j) {
        int yLinePos = yObjectPoolRect + (i * yPixelsPerInt) + j;
        g.drawLine(xObjectPoolPortion, yLinePos, xObjectPoolPortion + poolsWidth - 1, yLinePos);
      }
      int header = gcHeap.getObjectPool(i);
      int length = gcHeap.getMemBlockLength(header);
      if (length <= 0) { // In case object pool gets corrupted, don't hang up.
        break;
      }
      i += length;
    }

    for (i = 0; i < gcHeap.getHandlePoolSize(); ++i) {
      ObjectHandle oh = gcHeap.getObjectHandle(i + 1);
      if (!oh.free) {

        Color color = Color.red;
        int objectSizeInInts = 3;
        if (oh.fish.getFishColor() == Color.cyan) {
          color = Color.cyan;
          objectSizeInInts = 2;
        } else if (oh.fish.getFishColor() == Color.yellow) {
          color = Color.yellow;
          objectSizeInInts = 1;
        }
        g.setColor(color);

        // Draw bar across handle pool
        for (int j = 0; j < yPixelsPerInt * 2; ++j) {
          int yLinePos = yHandlePoolRect + (i * yPixelsPerInt * 2) + j;
          g.drawLine(xHandlePoolPortion + poolImageInsets, yLinePos, xHandlePoolPortion + poolImageInsets + poolsWidth - 1, yLinePos);
        }

        // Draw colored bars to represent object in the object pool
        for (int j = 0; j < yPixelsPerInt * objectSizeInInts; ++j) {
          int yLinePos = yObjectPoolRect + (oh.objectPos * yPixelsPerInt) + j;
          g.drawLine(xObjectPoolPortion, yLinePos, xObjectPoolPortion + poolsWidth - 1, yLinePos);
        }

        // Draw a line from the handle to the object to show that the handle
        // points to the object.
        int yArrowStart = yHandlePoolRect + (i * yPixelsPerInt * 2) + yPixelsPerInt;
        int yArrowEnd = yObjectPoolRect + (oh.objectPos * yPixelsPerInt) + ((yPixelsPerInt * objectSizeInInts) / 2);
        g.drawLine(xHandlePoolPortion + poolImageInsets + poolsWidth + 2, yArrowStart,
                xObjectPoolPortion - 3, yArrowEnd);
      }
    }
  }
}