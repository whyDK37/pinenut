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
 * This class represents the garbage collected heap that is
 * being exercised by this simulation. It contains all the
 * logic for interacting with the heap.
 *
 * @author Bill Venners
 */
public class GCHeap {

  private int handlePoolSize;
  private int objectPoolSize;
  private ObjectHandle[] handlePool;
  private int[] objectPool;

  GCHeap(int hndlPoolSize, int objPoolSize) {

    handlePoolSize = hndlPoolSize;
    objectPoolSize = objPoolSize;

    objectPool = new int[objectPoolSize];
    handlePool = new ObjectHandle[handlePoolSize];

    // Initialize the object pool by putting a header at the zeroeth int location
    // that indicates that the entire remainder of the object pool array is one
    // big contiguous available memory block.
    objectPool[0] = formMemBlockHeader(true, objectPoolSize);

    for (int i = 0; i < handlePoolSize; ++i) {
      handlePool[i] = new ObjectHandle();
      handlePool[i].free = true;
    }
  }

  // Returns number of handles in handlePool (2 ints each)
  public int getHandlePoolSize() {
    return handlePoolSize;
  }

  // Returns number of ints in object pool
  public int getObjectPoolSize() {
    return objectPoolSize;
  }

  public ObjectHandle getObjectHandle(int i) {
    return handlePool[i - 1];   // Subtract one because zero is used to indicate null,
    // so allocateHandle() adds 1 to the index.
  }

  public int getObjectPool(int i) {
    return objectPool[i];
  }

  public void setObjectPool(int i, int value) {
    objectPool[i] = value;
  }

  // formMemBlockHeader() makes an int that contains two pieces of information,
  // the length of the memory block and whether the memory block is free. The int
  // is the header for the block and is stored immediately before the block in
  // the objectPool array. These headers form a kind of linked list of memory blocks
  // because you can always jump to the next header by adding length to the index
  // of the current blocks header to get index to the next block's header. The length
  // variable is in units of ints; it tells how many ints long the memory block is,
  // including the header int.
  //
  // The zeroeth bit of a memory block header is used to indicate freeness. If the bit
  // is one, the memory block is free. Bits 1 through 31 are the memory block's length
  // in number of ints.
  private int formMemBlockHeader(boolean free, int length) {
    int retVal = length << 1;
    if (free) {
      retVal |= 1;
    }
    return retVal;
  }

  // The length of a memory block includes the header int.
  public int getMemBlockLength(int header) {
    return header >> 1;
  }

  public boolean getMemBlockFree(int header) {
    boolean retVal = false;
    if ((header & 0x1) == 0x1) {
      retVal = true;
    }
    return retVal;
  }

  // allocateObject() returns an int which is an index into the handlePool array. This is,
  // in effect, a handle. Because a handle of zero indicates a null handle, the zeroeth int
  // of the handlePool array is unused. The index returned by this function is a handle to
  // the object.
  //
  //      bytesNeeded -- number of bytes needed by the object for which memory is being
  //          allocated.
  public int allocateObject(int bytesNeeded, FishIcon fish) {

    // Initialize the return value to zero, which indicates not enough memory was
    // available for the new object.
    int retVal = 0;

    // Because the objectPool is an array of ints, all objects are int aligned. Calculate
    // the number of ints required by the new object based on the passed number of bytes
    // required. This is done by adding 3 (sizeof(int) - 1) to the bytesNeeded and
    // dividing by 4 (sizeof(int)). So one, two, three, and four byte objects will require
    // one int. Five, six, seven, and eight byte objects will require two ints, etc...
    int intsNeeded = (bytesNeeded + 3) / 4;

    int i = 0;
    while (i < objectPoolSize) {
      int header = objectPool[i];
      boolean free = false;
      if (getMemBlockFree(header)) {
        free = true;
      }
      int length = getMemBlockLength(header);

      if (!free) {

        // This memory block is not free, so continue by looking at the next memory
        // block. Add length to the current index into the constant pool to get the
        // index of the next memory block header.
        i += length;
        continue;
      }

      // We have found a free block. First, concatenate any other free blocks that may
      // be contiguous to this one.
      while ((i + length) < objectPoolSize && getMemBlockFree(objectPool[i + length])) {

        // The next memory block is also free, so concatenate with the previous
        // one. This is done by extending the size of the previous memory block
        // to include the next block.
        length += getMemBlockLength(objectPool[i + length]);
        objectPool[i] = formMemBlockHeader(true, length);
      }

      // Now that we have a free block, check to see if it is big enough to hold
      // the object for which the memory was requested.
      if (length - 1 < intsNeeded) {
        // This memory block is free, but not big enough for the fat object for
        // which memory has been requested. So continue by looking at the next
        // memory block. Add length to the current index into the constant pool
        // to get the index of the next memory block header.
        i += length;
        continue;
      }

      // The current free block is big enough for our new object. First, check to see
      // if there is more than enough memory in this block than that actually required
      // by the new object.
      int extraMem = length - intsNeeded - 1;
      if (extraMem > 0) {

        // The free block has more than enough memory, so we must split it into
        // two blocks. The first block will be exactly the right size for our
        // new object. The second will contain whatever is leftover. This splitting
        // is accomplished by changing the length of the original header to exactly
        // equal whatever is required by the new object, then adding a second
        // header for the leftover memory.
        objectPool[i] = formMemBlockHeader(true, intsNeeded + 1);
        objectPool[i + intsNeeded + 1] = formMemBlockHeader(true, extraMem);
      }

      // At this point objectPool[i] is exactly the right size for the new object.
      // All that we need to do now is allocate the handle to the memory. Add one
      // to i before passing to allocHandle, because i currently points to the header.
      // The object handle should point past the header at the start of the object
      // itself.
      retVal = allocateHandle(i + 1, fish);
      if (retVal > 0) {
        objectPool[i] = formMemBlockHeader(false, intsNeeded + 1);
      }
      break;
    }
    return retVal;
  }

  // allocateHandle() returns an int which is an index into the handlePool array. This is,
  // in effect, a handle. Because a handle of zero indicates a null handle, the zeroeth int
  // of the handlePool array is unused.
  public int allocateHandle(int objectHandle, FishIcon fishHandle) {
    int retVal = 0;
    for (int i = 0; i < handlePoolSize; ++i) {
      if (handlePool[i].free) {
        handlePool[i].free = false;
        handlePool[i].objectPos = objectHandle;
        handlePool[i].fish = fishHandle;
        retVal = i + 1; // Add one because zero means failure.
        break;
      }
    }
    return retVal;
  }

  public void freeObject(int handle) {
    if (!handlePool[handle - 1].free) {
      handlePool[handle - 1].free = true;
      handlePool[handle - 1].fish = null;
      int objectTableIndex = handlePool[handle - 1].objectPos;
      int header = getObjectPool(objectTableIndex - 1);
      int length = getMemBlockLength(header);
      header = formMemBlockHeader(true, length);
      setObjectPool(objectTableIndex - 1, header);
    }
  }

  // Returns true if an object was slid
  public boolean slideNextNonContiguousObjectDown() {

    boolean retVal = false;

    int i = 0;
    while (i < objectPoolSize) {
      int header = objectPool[i];
      boolean free = false;
      if (getMemBlockFree(header)) {
        free = true;
      }
      int length = getMemBlockLength(header);

      if (!free) {

        // This memory block is not free, so continue by looking at the next memory
        // block. Add length to the current index into the constant pool to get the
        // index of the next memory block header.
        i += length;
        continue;
      }

      // We have found a free block. First, concatenate any other free blocks that may
      // be contiguous to this one.
      while ((i + length) < objectPoolSize && getMemBlockFree(objectPool[i + length])) {

        // The next memory block is also free, so concatenate with the previous
        // one. This is done by extending the size of the previous memory block
        // to include the next block.
        length += getMemBlockLength(objectPool[i + length]);
        objectPool[i] = formMemBlockHeader(true, length);
      }

      // See if an object exists at the next location, if not break out of the loop
      // and return false. There are no other objects that can be slid.
      if (i + length >= objectPoolSize) {
        break;
      }

      int sliderLength = getMemBlockLength(objectPool[i + length]);
      for (int j = 1; j < sliderLength; ++j) {
        objectPool[i + j] = objectPool[i + length + j];
      }

      objectPool[i] = formMemBlockHeader(false, sliderLength);
      objectPool[i + sliderLength] = formMemBlockHeader(true, length);

      for (int j = 0; j < handlePoolSize; ++j) {
        if (!handlePool[j].free && handlePool[j].objectPos == i + length + 1) {
          handlePool[j].objectPos = i + 1;
          break;
        }
      }

      retVal = true;
      break;
    }
    return retVal;
  }
}