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
* This class serves as the outermost panel upon which all
* the user interface goodies for the assign references
* mode is placed.
*
* @author  Bill Venners
*/
class AssignReferencesPanel extends Panel {

    HeapOfFishTextArea controlPanelTextArea;
    String currentAssignRefsMode = HeapOfFishStrings.linkFish;

    AssignReferencesCanvases assignRefsCanvases;

    AssignReferencesPanel(GCHeap gcHeap, LocalVariables localVars, HeapOfFishTextArea ta) {

        controlPanelTextArea = ta;

        setBackground(Color.blue);

        setLayout(new BorderLayout());

        assignRefsCanvases = new AssignReferencesCanvases(gcHeap, localVars, ta);

        add("South", new AssignReferencesCheckboxPanel());
        add("Center", assignRefsCanvases);
    }

    public void refreshInstructions() {
        if (currentAssignRefsMode.equals(HeapOfFishStrings.moveFish)) {
            controlPanelTextArea.setText(HeapOfFishStrings.moveFishInstructions);
        }
        else if (currentAssignRefsMode.equals(HeapOfFishStrings.linkFish)) {
            controlPanelTextArea.setText(HeapOfFishStrings.linkFishInstructions);
        }
        else if (currentAssignRefsMode.equals(HeapOfFishStrings.unlinkFish)) {
            controlPanelTextArea.setText(HeapOfFishStrings.unlinkFishInstructions);
        }
    }

    public boolean action(Event evt, Object arg) {
        if (evt.target instanceof Checkbox) {
            Checkbox cb = (Checkbox) evt.target;
            String cbname = cb.getLabel();
            if (cbname.equals(HeapOfFishStrings.moveFish)) {
                if (!currentAssignRefsMode.equals(HeapOfFishStrings.moveFish)) {
                    controlPanelTextArea.setText(HeapOfFishStrings.moveFish);
                    assignRefsCanvases.setMode(HeapOfFishStrings.moveFish);
                    controlPanelTextArea.setText(HeapOfFishStrings.moveFishInstructions);
                }
            }
            else if (cbname.equals(HeapOfFishStrings.linkFish)) {
                if (!currentAssignRefsMode.equals(HeapOfFishStrings.linkFish)) {
                    controlPanelTextArea.setText(HeapOfFishStrings.linkFish);
                    assignRefsCanvases.setMode(HeapOfFishStrings.linkFish);
                    controlPanelTextArea.setText(HeapOfFishStrings.linkFishInstructions);
                }
            }
            else if (cbname.equals(HeapOfFishStrings.unlinkFish)) {
                if (!currentAssignRefsMode.equals(HeapOfFishStrings.unlinkFish)) {
                    controlPanelTextArea.setText(HeapOfFishStrings.unlinkFish);
                    assignRefsCanvases.setMode(HeapOfFishStrings.unlinkFish);
                    controlPanelTextArea.setText(HeapOfFishStrings.unlinkFishInstructions);
                }
            }
            currentAssignRefsMode = cbname;
        }
        return true;
    }
}