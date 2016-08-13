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
* This class is the panel that contains the five main components that make up the
* bulk of the user interface of the HeapOfFish applet, the swimming fish canvas,
* and the allocate fish, assign references, garbage collect, and compact heap
* panels, all of which are placed via a CardLayout.
*
* @author  Bill Venners
*/
class HeapOfFishCanvases extends Panel {

    private CardLayout cl = new CardLayout();
    private SwimmingFishCanvas swimmers = new SwimmingFishCanvas();
    private String currentMode = HeapOfFishStrings.swim;
    private AssignReferencesPanel assignRefsPanel;
    private GarbageCollectPanel garbageCollectPanel;

    HeapOfFishCanvases(GCHeap gcHeap, LocalVariables localVars, HeapOfFishTextArea ta) {

        assignRefsPanel = new AssignReferencesPanel(gcHeap, localVars, ta);
        garbageCollectPanel = new GarbageCollectPanel(gcHeap, localVars, ta);
        setLayout(cl);
        add(HeapOfFishStrings.allocateFish, new AllocateFishPanel(gcHeap, ta));
        add(HeapOfFishStrings.assignReferences, assignRefsPanel);
        add(HeapOfFishStrings.garbageCollect, garbageCollectPanel);
        add(HeapOfFishStrings.compactHeap, new CompactHeapPanel(gcHeap, ta));
        add(HeapOfFishStrings.swim, swimmers);
        swimmers.start();
        cl.show(this, HeapOfFishStrings.swim);
    }

    public void setMode(String mode) {
        if (mode.equals(HeapOfFishStrings.garbageCollect) && !currentMode.equals(HeapOfFishStrings.garbageCollect)) {
            garbageCollectPanel.resetGCState();
        }
        cl.show(this, mode);
        if (mode.equals(HeapOfFishStrings.swim) && !currentMode.equals(HeapOfFishStrings.swim)) {
            swimmers.start();
        }
        if (!mode.equals(HeapOfFishStrings.swim) && currentMode.equals(HeapOfFishStrings.swim)) {
            swimmers.stop();
        }
        if (mode.equals(HeapOfFishStrings.assignReferences) && !currentMode.equals(HeapOfFishStrings.assignReferences)) {
            assignRefsPanel.refreshInstructions();
        }
        currentMode = mode;
    }

    public void start() {
        swimmers.start();
    }

    public void stop() {
        swimmers.stop();
    }
}