/*
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
package jvm.sim;

/**
 * This class represents the stack frame of the executing
 * method.
 *
 * @author Bill Venners
 */
class StackFrame {

  // optop is pointer to top of operand stack. Always points
  // to next location.
  private int optop = 0;
  private Object[] operandStack;

  private Object[] localVars;

  StackFrame(int maxStack, int maxLocals) {
    if (maxStack > 65535 || maxLocals > 65535) {
      throw new JVMSimError("maxStack = " + maxStack
              + "maxLocals = " + maxLocals);
    }
    operandStack = new Object[maxStack];
    localVars = new Object[maxLocals];
  }

  void resetState() {
    optop = 0;
    for (int i = 0; i < operandStack.length; ++i) {
      operandStack[i] = null;
    }
    for (int i = 0; i < localVars.length; ++i) {
      localVars[i] = null;
    }
  }

  int getOptop() {
    return optop;
  }

  Object[] getOperandStack() {
    return operandStack;
  }

  Object[] getLocalVars() {
    return localVars;
  }

  void setLocalDouble(int i, double theDouble) {
    if (i + 1 >= localVars.length) { // use i + 1 because doubles take two
      throw new JVMSimError();
    }
    localVars[i] = new Double(theDouble);
    localVars[i + 1] = null;
  }

  void setLocalFloat(int i, float val) {
    if (i >= localVars.length) {
      throw new JVMSimError();
    }
    localVars[i] = new Float(val);
  }

  void setLocalInt(int i, int val) {
    if (i >= localVars.length) {
      throw new JVMSimError();
    }
    localVars[i] = new Integer(val);
  }

  void setLocalLong(int i, long theLong) {
    if (i + 1 >= localVars.length) { // use i + 1 because longs take two
      throw new JVMSimError();
    }
    localVars[i] = new Long(theLong);
    localVars[i + 1] = null;
  }

  void setLocalObject(int i, Object val) {
    if (i >= localVars.length) {
      throw new JVMSimError();
    }
    localVars[i] = val;
  }

  double getLocalDouble(int i) { // untested
    if (i >= localVars.length) {
      throw new JVMSimError();
    }
    return ((Double) localVars[i]).doubleValue();
  }

  float getLocalFloat(int i) {
    if (i >= localVars.length) {
      throw new JVMSimError();
    }
    return ((Float) localVars[i]).floatValue();
  }

  int getLocalInt(int i) {
    if (i >= localVars.length) {
      throw new JVMSimError();
    }
    return ((Integer) localVars[i]).intValue();
  }

  long getLocalLong(int i) { // untested
    if (i >= localVars.length) {
      throw new JVMSimError();
    }
    return ((Long) localVars[i]).longValue();
  }

  Object getLocalObject(int i) {
    if (i >= localVars.length) {
      throw new JVMSimError();
    }
    return localVars[i];
  }

  void pushDouble(double theDouble) { // untested
    if (optop >= operandStack.length) {
      throw new JVMSimError("optop = " + optop);
    }
    operandStack[optop] = new Double(theDouble);
    operandStack[optop + 1] = null;
    optop += 2; // A double occupies two words on the stack
  }

  void pushFloat(float f) {
    if (optop >= operandStack.length) {
      throw new JVMSimError("optop = " + optop);
    }
    operandStack[optop] = new Float(f);
    ++optop;
  }

  void pushInt(int val) {
    if (optop >= operandStack.length) {
      throw new JVMSimError("optop = " + optop);
    }
    operandStack[optop] = new Integer(val);
    ++optop;
  }

  void pushLong(long theLong) { // untested
    if (optop >= operandStack.length) {
      throw new JVMSimError("optop = " + optop);
    }
    operandStack[optop] = new Long(theLong);
    operandStack[optop + 1] = null;
    optop += 2; // A long occupies two words on the stack
  }

  void pushObject(Object theObject) {
    if (optop >= operandStack.length) {
      throw new JVMSimError("optop = " + optop);
    }
    operandStack[optop] = theObject;
    ++optop;
  }

  void pushReturnAddress(ReturnAddress retAddr) {
    if (optop >= operandStack.length) {
      throw new JVMSimError("optop = " + optop);
    }
    operandStack[optop] = retAddr;
    ++optop;
  }

  double popDouble() {
    if (optop <= 1) { // Use 1 because double occupies two slots
      throw new JVMSimError();
    }
    optop -= 2;
    double theDouble = ((Double) operandStack[optop]).doubleValue();
    operandStack[optop] = null; // make available for gc
    return theDouble;
  }

  float popFloat() {
    if (optop <= 0) {
      throw new JVMSimError();
    }
    --optop;
    float f = ((Float) operandStack[optop]).floatValue();
    operandStack[optop] = null; // make available for gc
    return f;
  }

  int popInt() {
    if (optop <= 0) {
      throw new JVMSimError();
    }
    --optop;
    int val = ((Integer) operandStack[optop]).intValue();
    operandStack[optop] = null; // make available for gc
    return val;
  }

  long popLong() {
    if (optop <= 1) { // Use 1 because long occupies two slots
      throw new JVMSimError();
    }
    optop -= 2;
    long theLong = ((Long) operandStack[optop]).longValue();
    operandStack[optop] = null; // make available for gc
    return theLong;
  }

  Object popObject() {
    if (optop <= 0) {
      throw new JVMSimError();
    }
    --optop;
    Object theObject = operandStack[optop];
    operandStack[optop] = null; // make available for gc
    return theObject;
  }

  ReturnAddress popReturnAddress() {
    if (optop <= 0) {
      throw new JVMSimError();
    }
    --optop;
    ReturnAddress retAddr = (ReturnAddress) operandStack[optop];
    operandStack[optop] = null; // make available for gc
    return retAddr;
  }

  void pop() {
    if (optop <= 0) {
      throw new JVMSimError();
    }
    --optop;
    operandStack[optop] = null; // make available for gc
  }
}
