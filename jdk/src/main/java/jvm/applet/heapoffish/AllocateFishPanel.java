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
* This class is the panel that holds the entire allocate
* fish user interface, including the buttons at the bottom
* and the canvas on which the handle and object pools are
* drawn.
*
* @author  Bill Venners
*/
class AllocateFishPanel extends Panel {

    GCHeap gcHeap;
    HeapOfFishTextArea controlPanelTextArea;

    PoolsCanvas poolsCanvas;

    AllocateFishPanel(GCHeap heap, HeapOfFishTextArea ta) {

        gcHeap = heap;
        controlPanelTextArea = ta;

        setBackground(Color.blue);

        setLayout(new BorderLayout());

        poolsCanvas = new PoolsCanvas(gcHeap);

        add("South", new AllocateFishButtonPanel());
        add("Center", poolsCanvas);
    }

    public boolean action(Event evt, Object arg) {
        if (evt.target instanceof Button) {
            String bname = (String) arg;
            Dimension canvasDim = poolsCanvas.size();
            if (bname.equals(HeapOfFishStrings.newRedFish)) {
                FishIcon fish = new BigRedFishIcon(false);
                int newFish = gcHeap.allocateObject(12, fish);
                if (newFish > 0) {
                    ObjectHandle oh = gcHeap.getObjectHandle(newFish);
                    gcHeap.setObjectPool(oh.objectPos, 0);
                    gcHeap.setObjectPool(oh.objectPos + 1, 0);
                    gcHeap.setObjectPool(oh.objectPos + 2, 0);
                    controlPanelTextArea.setText(HeapOfFishStrings.newRedFishAllocated);
                }
                else {
                    controlPanelTextArea.setText(HeapOfFishStrings.newRedFishNotAllocated);
                }
                poolsCanvas.repaint();
            }
            else if (bname.equals(HeapOfFishStrings.newBlueFish)) {
                FishIcon fish = new MediumBlueFishIcon(false);
                int newFish = gcHeap.allocateObject(8, fish);
                if (newFish > 0) {
                    ObjectHandle oh = gcHeap.getObjectHandle(newFish);
                    gcHeap.setObjectPool(oh.objectPos, 0);
                    gcHeap.setObjectPool(oh.objectPos + 1, 0);
                    controlPanelTextArea.setText(HeapOfFishStrings.newBlueFishAllocated);
                }
                else {
                    controlPanelTextArea.setText(HeapOfFishStrings.newBlueFishNotAllocated);
                }
                poolsCanvas.repaint();
            }
            else if (bname.equals(HeapOfFishStrings.newYellowFish)) {
                FishIcon fish = new LittleYellowFishIcon(false);
                int newFish = gcHeap.allocateObject(4, fish);
                if (newFish > 0) {
                    ObjectHandle oh = gcHeap.getObjectHandle(newFish);
                    gcHeap.setObjectPool(oh.objectPos, 0);
                    controlPanelTextArea.setText(HeapOfFishStrings.newYellowFishAllocated);
                }
                else {
                    controlPanelTextArea.setText(HeapOfFishStrings.newYellowFishNotAllocated);
                }
                poolsCanvas.repaint();
            }
        }
        return true;
    }
}