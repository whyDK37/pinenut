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
 * The object pool of the garbage-collected heap simulated
 * by the HeapOfFish applet is made up of references to
 * instances of this class.
 * <p>
 * ObjectHandle contains:
 * <p>
 * free -- indicates whether this handle is currently being used.
 * objectPos -- the index into the objectPool array of bytes at which the object
 * data is located
 * fish -- an object which contains information about this object. Normally this
 * object would hold constant pool info, methods, etc....
 *
 * @author Bill Venners
 */
public class ObjectHandle {

  public boolean free;
  public int objectPos;
  public FishIcon fish;

  //public boolean currentGCMarkNode;

  // The previous node in GC traversal was either a fish or a local variable root node
  public boolean previousNodeInGCTraversalIsAFish;
  public int previousFishInGCTraversal;

  // If a node has not yet been traversed its color is white. A node that has been
  // traversed in an upward direction (where the root is at the bottom) is gray. When
  // the node is again traversed back in a downward direction, its color is changed
  // to black.
  public Color myColor;
  public Color myFriendLineColor;
  public Color myLunchLineColor;
  public Color mySnackLineColor;

  public boolean gotFriend;
  public Point myFriendLineStart;
  public Point myFriendLineEnd;

  public boolean gotLunch;
  public Point myLunchLineStart;
  public Point myLunchLineEnd;

  public boolean gotSnack;
  public Point mySnackLineStart;
  public Point mySnackLineEnd;
}