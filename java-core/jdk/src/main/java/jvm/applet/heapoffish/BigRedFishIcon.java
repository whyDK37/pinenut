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
* This class serves as a personality module for the red
* fish icon. It assigns proper values to the protected
* instance variables of its superclass, FishIcon.
*
* @author  Bill Venners
*/
class BigRedFishIcon extends FishIcon {

    BigRedFishIcon(boolean swim) {

        fishPosition = new Point(0, 0);
        fishIsSwimming = swim;
        fishHasBeenInitiallyPositioned = false;

        fishColor = Color.red;

        topTail = new Point(0, 0);
        backTail = new Point(12, 16);
        bottomTail = new Point(0, 32);
        frontTail = new Point(20, 16);

        maxPaintsToHoldTailInOnePosition = 4;
        ovalPos = new Point(20,4);
        ovalDim = new Dimension(40, 24);

        eyePos = new Point(52, 10);
        eyeDim = new Dimension(4, 4);

        staticTail.addPoint(frontTail.x, frontTail.y);
        staticTail.addPoint(topTail.x, topTail.y);
        staticTail.addPoint(backTail.x, backTail.y);
        staticTail.addPoint(bottomTail.x, bottomTail.y);
        staticTail.addPoint(frontTail.x, frontTail.y);

        if (fishIsSwimming) {
            swimmingTail[0] = new Polygon();
            swimmingTail[0].addPoint(frontTail.x, frontTail.y);
            swimmingTail[0].addPoint(topTail.x, topTail.y);
            swimmingTail[0].addPoint(backTail.x, backTail.y);
            swimmingTail[0].addPoint(bottomTail.x, bottomTail.y - 6);
            swimmingTail[0].addPoint(frontTail.x, frontTail.y);

            swimmingTail[1] = swimmingTail[0];

            swimmingTail[2] = new Polygon();
            swimmingTail[2].addPoint(frontTail.x, frontTail.y);
            swimmingTail[2].addPoint(topTail.x + 3, topTail.y + 1);
            swimmingTail[2].addPoint(backTail.x, backTail.y);
            swimmingTail[2].addPoint(bottomTail.x + 3, bottomTail.y - 5);
            swimmingTail[2].addPoint(frontTail.x, frontTail.y);

            swimmingTail[3] = new Polygon();
            swimmingTail[3].addPoint(frontTail.x, frontTail.y);
            swimmingTail[3].addPoint(topTail.x + 7, topTail.y + 3);
            swimmingTail[3].addPoint(backTail.x - 1, backTail.y);
            swimmingTail[3].addPoint(bottomTail.x + 7, bottomTail.y - 3);
            swimmingTail[3].addPoint(frontTail.x, frontTail.y);

            swimmingTail[4] = new Polygon();
            swimmingTail[4].addPoint(frontTail.x, frontTail.y);
            swimmingTail[4].addPoint(topTail.x + 3, topTail.y + 5);
            swimmingTail[4].addPoint(backTail.x, backTail.y);
            swimmingTail[4].addPoint(bottomTail.x + 3, bottomTail.y - 1);
            swimmingTail[4].addPoint(frontTail.x, frontTail.y);

            swimmingTail[5] = new Polygon();
            swimmingTail[5].addPoint(frontTail.x, frontTail.y);
            swimmingTail[5].addPoint(topTail.x, topTail.y + 6);
            swimmingTail[5].addPoint(backTail.x, backTail.y);
            swimmingTail[5].addPoint(bottomTail.x, bottomTail.y);
            swimmingTail[5].addPoint(frontTail.x, frontTail.y);

            swimmingTail[6] = swimmingTail[5];

            swimmingTail[7] = swimmingTail[5];
            swimmingTail[8] = swimmingTail[4];
            swimmingTail[9] = swimmingTail[3];
        }
    }

    public Color getFishColor() {
        return Color.red;
    }
}