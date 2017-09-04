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
 * This class stores the user interface strings used by the
 * simulation applets.
 *
 * @author Bill Venners
 */
class StringTable {

  public final static String step = "Step";
  public final static String reset = "Reset";
  public final static String run = "Run";
  public final static String stop = "Stop";
  public final static String operand = "operand";
  public final static String execEnv = "exec env";
  public final static String localVars = "local vars";
  public final static String pcPointer = "pc >";
  public final static String varsPointer = "vars >";
  public final static String framePointer = "frame >";
  public final static String optopPointer = "optop >";
  public final static String address = "address";
  public final static String offset = "offset";
  public final static String index = "index";
  public final static String bytecodes = "bytecode";
  public final static String mnemonics = "mnemonic";
  public final static String theMethod = "The Method";
  public final static String operandStack = "Operand Stack";
  public final static String localVariables = "Local Variables";
  public final static String hexValue = "hex value";
  public final static String value = "value";
  public final static String Registers = "Registers";
  public final static String pc = "pc ";
  public final static String optop = "optop ";
  public final static String frame = "frame";
  public final static String vars = "vars";
  public final static String objectReference = "object";
  public final static String returnAddress = "retAddr";
  public final static String objectRefHexRepresentation = "OBJ REF";
  public final static String aaloadText = "aaload will pop an index and array reference and push the object ref at that index of the array.";
  public final static String aload_0Text = "aload_0 will push the object ref at local variable 0 onto the stack.";
  public final static String aload_1Text = "aload_1 will push the object ref at local variable 1 onto the stack.";
  public final static String aload_2Text = "aload_2 will push the object ref at local variable 2 onto the stack.";
  public final static String aload_3Text = "aload_3 will push the object ref at local variable 3 onto the stack.";
  public final static String astoreText = "astore will pop object ref off top of stack and store it in local var specified by operand.";
  public final static String astore_0Text = "astore_0 will pop the object ref off the top of the stack and store it in local variable 0.";
  public final static String astore_1Text = "astore_1 will pop the object ref off the top of the stack and store it in local variable 1.";
  public final static String astore_2Text = "astore_2 will pop the object ref off the top of the stack and store it in local variable 2.";
  public final static String astore_3Text = "astore_3 will pop the object ref off the top of the stack and store it in local variable 3.";
  public final static String athrowText = "athrow will pop the object ref off the top of the stack, and look for a catch clause.";
  public final static String bipushText = "bipush will expand the next byte to an int and push it onto the stack.";
  public final static String breakpointText = "breakpoint will stop the simulation.";
  public final static String dcmpgText = "dcmpg will pop two doubles, compare them, push int result of comparison.";
  public final static String dconst_0Text = "dconst_0 will push double 0.0 onto the stack.";
  public final static String dconst_1Text = "dconst_1 will push double 1.0 onto the stack.";
  public final static String dloadText = "dload will push onto the stack the double at the local variables specified by the operand.";
  public final static String dload_0Text = "dload_0 will push the double at local variables 0 and 1 onto the stack.";
  public final static String dload_2Text = "dload_2 will push the double at local variables 2 and 3 onto the stack.";
  public final static String ddivText = "ddiv will pop two doubles, divide them, and push the double result.";
  public final static String dmulText = "dmul will pop two doubles, multiply them, and push the double result.";
  public final static String dstoreText = "dstore will pop the double off the top of the stack and store it in local variable indicated by operand.";
  public final static String dstore_0Text = "dstore_0 will pop the double off the top of the stack and store it in local variables 0 and 1.";
  public final static String dstore_2Text = "dstore_2 will pop the double off the top of the stack and store it in local variables 2 and 3.";
  public final static String dsubText = "dsub will pop two doubles, subtract them, and push the double result.";
  public final static String fconst_0Text = "fconst_0 will push float 0.0 onto the stack.";
  public final static String fconst_2Text = "fconst_2 will push float 2.0 onto the stack.";
  public final static String fload_0Text = "fload_0 will push the float at local variable 0 onto the stack.";
  public final static String fmulText = "fmul will pop two floats, multiply them, and push the result.";
  public final static String fstore_0Text = "fstore_0 will pop the float off the top of the stack and store it in local variable 0.";
  public final static String fsubText = "fsub will pop two floats, subtract them, and push the result.";
  public final static String getstaticText = "getstatic will push a static variable identified by a constant pool item.";
  public final static String gotoText = "goto will cause a jump to the specified offset.";
  public final static String iaddText = "iadd will pop the top two ints off the stack, add them, and push the result back onto the stack.";
  public final static String iandText = "iand will pop the top two ints off the stack, bitwise-and them, and push the result back onto the stack.";
  public final static String iastoreText = "iastore will pop an int value, an index, and an arrayref and assign arrayref[index] = value.";
  public final static String iconst_m1Text = "iconst_m1 will push int -1 onto the stack.";
  public final static String iconst_0Text = "iconst_0 will push int 0 onto the stack.";
  public final static String iconst_1Text = "iconst_1 will push int 1 onto the stack.";
  public final static String iconst_2Text = "iconst_2 will push int 2 onto the stack.";
  public final static String iconst_3Text = "iconst_3 will push int 3 onto the stack.";
  public final static String iconst_4Text = "iconst_4 will push int 4 onto the stack.";
  public final static String iconst_5Text = "iconst_5 will push int 5 onto the stack.";
  public final static String idivText = "idiv will pop the top two ints off the stack, divide them, and push the result back onto the stack.";
  public final static String if_icmpgtText = "if_icmpgt will branch if the next to topmost int is greater than the topmost int.";
  public final static String if_icmpltText = "if_icmplt will branch if the next to topmost int is less than the topmost int.";
  public final static String if_icmpneText = "if_icmpne will branch if the top two ints are not equal to each other.";
  public final static String ifeqText = "ifeq will branch if the topmost int is equal to zero.";
  public final static String ifltText = "iflt will branch if the topmost int is less than zero.";
  public final static String ifneText = "ifne will branch if the topmost int is not equal to zero.";
  public final static String iincText = "iinc will increment the specified local variable by the specified amount.";
  public final static String iload_0Text = "iload_0 will push the integer at local variable 0 onto the stack.";
  public final static String iload_1Text = "iload_1 will push the integer at local variable 1 onto the stack.";
  public final static String iload_2Text = "iload_2 will push the integer at local variable 2 onto the stack.";
  public final static String iload_3Text = "iload_3 will push the integer at local variable 3 onto the stack.";
  public final static String imulText = "imul will pop two integers, multiply them, and push the result.";
  public final static String i2bText = "i2b will convert the topmost int on the stack to a value valid for the byte type.";
  public final static String invokestaticText = "invokestatic will invoke the static method indicated by the specified constant pool entry.";
  public final static String iorText = "ior will pop the top two ints off the stack, bitwise-or them, and push the result back onto the stack.";
  public final static String iremText = "irem will pop two integers, remainder them, and push the result.";
  public final static String ishlText = "ishl will shift the next to topmost int to the left by amount indicated by topmost int.";
  public final static String istore_0Text = "istore_0 will pop the integer off the top of the stack and store it in local variable 0.";
  public final static String istore_1Text = "istore_1 will pop the integer off the top of the stack and store it in local variable 1.";
  public final static String istore_2Text = "istore_2 will pop the integer off the top of the stack and store it in local variable 2.";
  public final static String istore_3Text = "istore_3 will pop the integer off the top of the stack and store it in local variable 3.";
  public final static String istoreText = "istore will pop the int off the top of the stack and store it in local variable indicated by operand.";
  public final static String ixorText = "ixor will pop the top two ints off the stack, biwise-xor them, and push the result back onto the stack.";
  public final static String jsrText = "jsr push a return address and jump to the specified offset.";
  public final static String laddText = "ladd will pop the top two longs off the stack, add them, and push the result back onto the stack.";
  public final static String lconst_1Text = "lconst_1 will push long 1 onto the stack.";
  public final static String ldc2_wText = "ldc2_w will push the dual-byte value from constant pool entry specifed by the operand.";
  public final static String lloadText = "lload will push onto the stack the long at the local variables specified by the operand.";
  public final static String lload_0Text = "lload_0 will push the long at local variables 0 and 1 onto the stack.";
  public final static String lload_2Text = "lload_2 will push the long at local variables 2 and 3 onto the stack.";
  public final static String lstoreText = "lstore will pop the long off the top of the stack and store it in local variable indicated by operand.";
  public final static String lstore_0Text = "lstore_0 will pop the long off the top of the stack and store it in local variables 0 and 1.";
  public final static String lstore_2Text = "lstore_2 will pop the long off the top of the stack and store it in local variables 2 and 3.";
  public final static String multianewarrayText = "multianewarray will allocate memory for a new multi-dim array and push reference.";
  public final static String popText = "pop will pop a word off the top of the stack.";
  public final static String retText = "ret will jump to a return address in local var specified by operand .";
  public final static String tableswitchText = "tableswitch will pop key and jump to a branch offset based on key.";
}

