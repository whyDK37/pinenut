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

/**
 * This class contains the user interface strings used by
 * the HeapOfFish applet.
 *
 * @author Bill Venners
 */
class HeapOfFishStrings {

  // The modes of the heap of fish applet
  public final static String allocateFish = "Allocate Fish";
  public final static String assignReferences = "Assign References";
  public final static String garbageCollect = "Garbage Collect";
  public final static String compactHeap = "Compact Heap";
  public final static String swim = "Swim";

  public final static String objectPool = "Object Pool";
  public final static String handlePool = "Handle Pool";
  public final static String newRedFish = "new RedFish()";
  public final static String newBlueFish = "new BlueFish()";
  public final static String newYellowFish = "new YellowFish()";
  public final static String allocateFishInstructions = "Click on a button to allocate a new fish object.";
  public final static String newRedFishAllocated = "A new RedFish was allocated successfully.";
  public final static String newRedFishNotAllocated = "Unable to allocate a new RedFish.\nEither there were no free handles left in the handle pool, or there\nwas not enough contiguous free space left in the object pool.";
  public final static String newBlueFishAllocated = "A new BlueFish was allocated successfully.";
  public final static String newBlueFishNotAllocated = "Unable to allocate a new BlueFish.\nEither there were no free handles left in the handle pool, or there\nwas not enough contiguous free space left in the object pool.";
  public final static String newYellowFishAllocated = "A new YellowFish was allocated successfully.";
  public final static String newYellowFishNotAllocated = "Unable to allocate a new YellowFish.\nEither there were no free handles left in the handle pool, or there\nwas not enough contiguous free space left in the object pool.";
  public final static String moveFish = "Move Fish";
  public final static String linkFish = "Link Fish";
  public final static String unlinkFish = "Unlink Fish";

  public final static String localVariables = "Local Variables";
  public final static String redFishLocalVar = "RedFish rf;";
  public final static String blueFishLocalVar = "BlueFish bf;";
  public final static String yellowFishLocalVar = "YellowFish yf;";

  public final static String moveFishInstructions = "To move a fish, click on the fish you wish to move and drag it\nto a new position.";
  public final static String linkFishInstructions = "To link two fish, click on a fish and drag the mouse to another\nfish. A line will appear between the linked fish. You can also\nlink a local variable to a fish by clicking on the local variable and\ndragging to the fish.";
  public final static String unlinkFishInstructions = "To unlink two fish, click on the line connecting them and the\nlink will be broken. You can also break the link between a local\nvariable and a fish by clicking on the line connecting them.";

  public final static String step = "Step";
  public final static String reset = "Reset";

  public final static String traversingYellowRoot = "Traversing references starting from YellowFish local variable root.";
  public final static String traversingBlueRoot = "Traversing references starting from BlueFish local variable root.";
  public final static String traversingRedRoot = "Traversing references starting from RedFish local variable root.";
  public final static String garbageCollectionDone = "Garbage Collection is done.";

  public final static String garbageCollectInstructions = "Click on the Step button to watch each step in the mark and sweep\ngarbage collection process. Click on Reset to do restart the garbage\ncollecting process.";

  public final static String doneWithYellowRoot = "Done traversing references starting from YellowFish local variable root.";
  public final static String doneWithBlueRoot = "Done traversing references starting from BlueFish local variable root.";
  public final static String doneWithRedRoot = "Done traversing references starting from RedFish local variable root.";

  public final static String readyToSweepUnmarkedFish = "Ready to sweep all unmarked fish. These fish are shown in white.\nThey are unreachable by the program and therefore can be freed.";
  public final static String sweptFish0 = "Freed memory occupied by ";
  public final static String sweptFish1 = " unreachable fish object(s).";

  public final static String slide = "Slide";
  public final static String compactHeapInstructions = "Click the Slide button to move one object so that it is\ncontiguous to its neighbor.";
  public final static String slidSuccessfully = "Successfully slid one object.";
  public final static String cantSlideAnymore = "No more objects to slide. Heap is already compacted.";
}