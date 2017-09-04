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

import java.applet.Applet;
import java.awt.*;

/**
 * This applet simulates a compacting, mark-and-sweep
 * garbage-collected heap inside a Java Virtual Machine.
 *
 * @author Bill Venners
 */
public class HeapOfFish extends Applet {

  private GCHeap gcHeap = new GCHeap(15, 50);
  private LocalVariables localVars = new LocalVariables();

  private HeapOfFishControlPanel controlPanel = new HeapOfFishControlPanel();
  private HeapOfFishCanvases canvases = new HeapOfFishCanvases(gcHeap, localVars, controlPanel.getTextArea());
  private String currentHeapOfFishMode = HeapOfFishStrings.swim;

  public void init() {

    super.init();
    setBackground(Color.cyan);

    setLayout(new BorderLayout(5, 5));
    add("North", new ColoredLabel("HEAP OF FISH", Label.CENTER, Color.white));
    add("South", controlPanel);
    add("Center", canvases);
  }

  public void start() {
    canvases.start();
  }

  public void stop() {
    canvases.stop();
  }

  public Insets insets() {
    return new Insets(5, 5, 5, 5);
  }

  public boolean action(Event evt, Object arg) {
    if (evt.target instanceof Checkbox) {
      Checkbox cb = (Checkbox) evt.target;
      String cbname = cb.getLabel();
      if (cbname.equals(HeapOfFishStrings.allocateFish)) {
        if (!currentHeapOfFishMode.equals(HeapOfFishStrings.allocateFish)) {
          controlPanel.getTextArea().setText(HeapOfFishStrings.allocateFishInstructions);
          canvases.setMode(HeapOfFishStrings.allocateFish);
        }
      } else if (cbname.equals(HeapOfFishStrings.assignReferences)) {
        if (!currentHeapOfFishMode.equals(HeapOfFishStrings.assignReferences)) {
          canvases.setMode(HeapOfFishStrings.assignReferences);
        }
      } else if (cbname.equals(HeapOfFishStrings.garbageCollect)) {
        if (!currentHeapOfFishMode.equals(HeapOfFishStrings.garbageCollect)) {
          canvases.setMode(HeapOfFishStrings.garbageCollect);
        }
      } else if (cbname.equals(HeapOfFishStrings.compactHeap)) {
        if (!currentHeapOfFishMode.equals(HeapOfFishStrings.compactHeap)) {
          controlPanel.getTextArea().setText(HeapOfFishStrings.compactHeapInstructions);
          canvases.setMode(HeapOfFishStrings.compactHeap);
        }
      } else if (cbname.equals(HeapOfFishStrings.swim)) {
        if (!currentHeapOfFishMode.equals(HeapOfFishStrings.swim)) {
          controlPanel.getTextArea().setText("");
          canvases.setMode(HeapOfFishStrings.swim);
        }
      }
      currentHeapOfFishMode = cbname;
      canvases.repaint();
    }
    return true;
  }
}