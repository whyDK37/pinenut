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
 * This class represents the method being executed by the
 * JVM simulation and performs the actual interpreting of
 * the bytecodes.
 *
 * @author Bill Venners
 */
class Method {

  private final static int EQ = 0;
  private final static int NE = 1;
  private final static int LT = 2;
  private final static int LE = 3;
  private final static int GT = 4;
  private final static int GE = 5;

  private int pc; // the program counter register
  private byte[] code; // the method's bytecode stream
  private ExceptionTableEntry[] exceptionTable;
  private ConstantPoolEntry[] constantPool;

  private StackFrame sf;

  Method(StackFrame sf, byte[] code, ExceptionTableEntry[] exceptionTable,
         ConstantPoolEntry[] constantPool) {

    this.code = code;
    this.sf = sf;
    this.exceptionTable = exceptionTable;
    this.constantPool = constantPool;
  }

  void resetState() {
    pc = 0;
    sf.resetState();
  }

  // This method is synchronized because executing an instruction must be
  // an idivisible act. If this is being called from the jdk.thread that gets
  // fired off by pressing the Run button, it must not be interrupted by
  // the user pressing the Stop button or leaving the page in the browser.
  synchronized int executeNextInstruction() throws BreakpointException {
    switch (((int) code[pc]) & 0xff) {

      case OpCode.AALOAD:
        executeAALOAD();
        break;

      case OpCode.ACONST_NULL:
        executeACONST_NULL();
        break;

      case OpCode.ALOAD:
        executeALOAD();
        break;

      case OpCode.ALOAD_0:
        executeALOAD_N(0);
        break;

      case OpCode.ALOAD_1:
        executeALOAD_N(1);
        break;

      case OpCode.ALOAD_2:
        executeALOAD_N(2);
        break;

      case OpCode.ALOAD_3:
        executeALOAD_N(3);
        break;

      case OpCode.ASTORE:
        executeASTORE();
        break;

      case OpCode.ASTORE_0:
        executeASTORE_N(0);
        break;

      case OpCode.ASTORE_1:
        executeASTORE_N(1);
        break;

      case OpCode.ASTORE_2:
        executeASTORE_N(2);
        break;

      case OpCode.ASTORE_3:
        executeASTORE_N(3);
        break;

      case OpCode.ATHROW:
        executeATHROW();
        break;

      case OpCode.BIPUSH:
        executeBIPUSH();
        break;

      case OpCode.BREAKPOINT:
        throw new BreakpointException();

      case OpCode.D2F:
        executeD2F();
        break;

      case OpCode.D2I:
        executeD2I();
        break;

      case OpCode.D2L:
        executeD2L();
        break;

      case OpCode.DADD:
        executeDADD();
        break;

      case OpCode.DCMPG:
        executeDCMPG();
        break;

      case OpCode.DCONST_0:
        executeDCONST_N(0.0);
        break;

      case OpCode.DCONST_1:
        executeDCONST_N(1.0);
        break;

      case OpCode.DDIV:
        executeDDIV();
        break;

      case OpCode.DLOAD:
        executeDLOAD();
        break;

      case OpCode.DLOAD_0:
        executeDLOAD_N(0);
        break;

      case OpCode.DLOAD_1:
        executeDLOAD_N(1);
        break;

      case OpCode.DLOAD_2:
        executeDLOAD_N(2);
        break;

      case OpCode.DLOAD_3:
        executeDLOAD_N(3);
        break;

      case OpCode.DMUL:
        executeDMUL();
        break;

      case OpCode.DNEG:
        executeDNEG();
        break;

      case OpCode.DREM:
        executeDREM();
        break;

      case OpCode.DSTORE:
        executeDSTORE();
        break;

      case OpCode.DSTORE_0:
        executeDSTORE_N(0);
        break;

      case OpCode.DSTORE_1:
        executeDSTORE_N(1);
        break;

      case OpCode.DSTORE_2:
        executeDSTORE_N(2);
        break;

      case OpCode.DSTORE_3:
        executeDSTORE_N(3);
        break;

      case OpCode.DSUB:
        executeDSUB();
        break;

      case OpCode.F2D:
        executeF2D();
        break;

      case OpCode.F2I:
        executeF2I();
        break;

      case OpCode.F2L:
        executeF2L();
        break;

      case OpCode.FADD:
        executeFADD();
        break;

      case OpCode.FCONST_0:
        executeFCONST_N(0.0f);
        break;

      case OpCode.FCONST_1:
        executeFCONST_N(1.0f);
        break;

      case OpCode.FCONST_2:
        executeFCONST_N(2.0f);
        break;

      case OpCode.FDIV:
        executeFDIV();
        break;

      case OpCode.FLOAD:
        executeFLOAD();
        break;

      case OpCode.FLOAD_0:
        executeFLOAD_N(0);
        break;

      case OpCode.FLOAD_1:
        executeFLOAD_N(1);
        break;

      case OpCode.FLOAD_2:
        executeFLOAD_N(2);
        break;

      case OpCode.FLOAD_3:
        executeFLOAD_N(3);
        break;

      case OpCode.FMUL:
        executeFMUL();
        break;

      case OpCode.FNEG:
        executeFNEG();
        break;

      case OpCode.FREM:
        executeFREM();
        break;

      case OpCode.FSTORE:
        executeFSTORE();
        break;

      case OpCode.FSTORE_0:
        executeFSTORE_N(0);
        break;

      case OpCode.FSTORE_1:
        executeFSTORE_N(1);
        break;

      case OpCode.FSTORE_2:
        executeFSTORE_N(2);
        break;

      case OpCode.FSTORE_3:
        executeFSTORE_N(3);
        break;

      case OpCode.FSUB:
        executeFSUB();
        break;

      case OpCode.GETSTATIC:
        executeGETSTATIC();
        break;

      case OpCode.GOTO:
        executeGOTO();
        break;

      case OpCode.I2B:
        executeI2B();
        break;

      case OpCode.I2C:
        executeI2C();
        break;

      case OpCode.I2D:
        executeI2D();
        break;

      case OpCode.I2F:
        executeI2F();
        break;

      case OpCode.I2L:
        executeI2L();
        break;

      case OpCode.I2S:
        executeI2S();
        break;

      case OpCode.IADD:
        executeIADD();
        break;

      case OpCode.IAND:
        executeIAND();
        break;

      case OpCode.IASTORE:
        executeIASTORE();
        break;

      case OpCode.ICONST_M1:
        executeICONST_N(-1);
        break;

      case OpCode.ICONST_0:
        executeICONST_N(0);
        break;

      case OpCode.ICONST_1:
        executeICONST_N(1);
        break;

      case OpCode.ICONST_2:
        executeICONST_N(2);
        break;

      case OpCode.ICONST_3:
        executeICONST_N(3);
        break;

      case OpCode.ICONST_4:
        executeICONST_N(4);
        break;

      case OpCode.ICONST_5:
        executeICONST_N(5);
        break;

      case OpCode.IDIV:
        executeIDIV();
        break;

      case OpCode.IFEQ:
        executeIFCOND(Method.EQ);
        break;

      case OpCode.IFNE:
        executeIFCOND(Method.NE);
        break;

      case OpCode.IFLT:
        executeIFCOND(Method.LT);
        break;

      case OpCode.IFLE:
        executeIFCOND(Method.LE);
        break;

      case OpCode.IFGT:
        executeIFCOND(Method.GT);
        break;

      case OpCode.IFGE:
        executeIFCOND(Method.GE);
        break;

      case OpCode.IF_ICMPEQ:
        executeIF_ICMPCOND(Method.EQ);
        break;

      case OpCode.IF_ICMPNE:
        executeIF_ICMPCOND(Method.NE);
        break;

      case OpCode.IF_ICMPLT:
        executeIF_ICMPCOND(Method.LT);
        break;

      case OpCode.IF_ICMPLE:
        executeIF_ICMPCOND(Method.LE);
        break;

      case OpCode.IF_ICMPGT:
        executeIF_ICMPCOND(Method.GT);
        break;

      case OpCode.IF_ICMPGE:
        executeIF_ICMPCOND(Method.GE);
        break;

      case OpCode.IINC:
        executeIINC();
        break;

      case OpCode.ILOAD:
        executeILOAD();
        break;

      case OpCode.ILOAD_0:
        executeILOAD_N(0);
        break;

      case OpCode.ILOAD_1:
        executeILOAD_N(1);
        break;

      case OpCode.ILOAD_2:
        executeILOAD_N(2);
        break;

      case OpCode.ILOAD_3:
        executeILOAD_N(3);
        break;

      case OpCode.IMUL:
        executeIMUL();
        break;

      case OpCode.INEG:
        executeINEG();
        break;

      case OpCode.INVOKESTATIC:
        executeINVOKESTATIC();
        break;

      case OpCode.IOR:
        executeIOR();
        break;

      case OpCode.IREM:
        executeIREM();
        break;

      case OpCode.ISHL:
        executeISHL();
        break;

      case OpCode.ISHR:
        executeISHR();
        break;

      case OpCode.ISTORE:
        executeISTORE();
        break;

      case OpCode.ISTORE_0:
        executeISTORE_N(0);
        break;

      case OpCode.ISTORE_1:
        executeISTORE_N(1);
        break;

      case OpCode.ISTORE_2:
        executeISTORE_N(2);
        break;

      case OpCode.ISTORE_3:
        executeISTORE_N(3);
        break;

      case OpCode.ISUB:
        executeISUB();
        break;

      case OpCode.IUSHR:
        executeIUSHR();
        break;

      case OpCode.IXOR:
        executeIXOR();
        break;

      case OpCode.JSR:
        executeJSR();
        break;

      case OpCode.L2D:
        executeL2D();
        break;

      case OpCode.L2F:
        executeL2F();
        break;

      case OpCode.L2I:
        executeL2I();
        break;

      case OpCode.LADD:
        executeLADD();
        break;

      case OpCode.LAND:
        executeLAND();
        break;

      case OpCode.LCONST_0:
        executeLCONST_N(0L);
        break;

      case OpCode.LCONST_1:
        executeLCONST_N(1L);
        break;

      case OpCode.LDC2_W:
        executeLDC2_W();
        break;

      case OpCode.LDIV:
        executeLDIV();
        break;

      case OpCode.LLOAD:
        executeLLOAD();
        break;

      case OpCode.LLOAD_0:
        executeLLOAD_N(0);
        break;

      case OpCode.LLOAD_1:
        executeLLOAD_N(1);
        break;

      case OpCode.LLOAD_2:
        executeLLOAD_N(2);
        break;

      case OpCode.LLOAD_3:
        executeLLOAD_N(3);
        break;

      case OpCode.LMUL:
        executeLMUL();
        break;

      case OpCode.LNEG:
        executeLNEG();
        break;

      case OpCode.LOR:
        executeLOR();
        break;

      case OpCode.LREM:
        executeLREM();
        break;

      case OpCode.LSHL:
        executeLSHL();
        break;

      case OpCode.LSHR:
        executeLSHR();
        break;

      case OpCode.LSTORE:
        executeLSTORE();
        break;

      case OpCode.LSTORE_0:
        executeLSTORE_N(0);
        break;

      case OpCode.LSTORE_1:
        executeLSTORE_N(1);
        break;

      case OpCode.LSTORE_2:
        executeLSTORE_N(2);
        break;

      case OpCode.LSTORE_3:
        executeLSTORE_N(3);
        break;

      case OpCode.LSUB:
        executeLSUB();
        break;

      case OpCode.LUSHR:
        executeLUSHR();
        break;

      case OpCode.LXOR:
        executeLXOR();
        break;

      case OpCode.MULTIANEWARRAY:
        executeMULTIANEWARRAY();
        break;

      case OpCode.POP:
        executePOP();
        break;

      case OpCode.RET:
        executeRET();
        break;

      case OpCode.SIPUSH:
        executeSIPUSH();
        break;

      case OpCode.TABLESWITCH:
        executeTABLESWITCH();
        break;

      default:
        throw new JVMSimError();
    }

    return pc;
  }

  // Branch interprets the first two opcodes following the
  // instruction (pointed to by the current pc) as branchbyte1 and
  // branchbyte2. It uses them to form a 16 bit signed offset, which
  // it adds to the pc register. This is used to execute several
  // opcodes, such as GOTO and IF_ICMPLT.
  private void branch() {
    byte branchByte1 = code[pc + 1];
    byte branchByte2 = code[pc + 2];
    short branchOffset = branchByte1;
    branchOffset <<= 8;
    branchOffset += ((short) branchByte2) & 0xff;
    pc += branchOffset;
  }

  private short getShortOperand(int codeIndex) {
    byte shortByte1 = code[codeIndex];
    byte shortByte2 = code[codeIndex + 1];
    short theShort = shortByte1;
    theShort <<= 8;
    theShort += ((short) shortByte2) & 0xff;
    return theShort;
  }

  private int getIntOperand(int codeIndex) {
    byte intByte1 = code[codeIndex];
    byte intByte2 = code[codeIndex + 1];
    byte intByte3 = code[codeIndex + 2];
    byte intByte4 = code[codeIndex + 3];
    int theInt = (intByte1 << 24) | ((intByte2 & 0xff) << 16)
            | ((intByte3 & 0xff) << 8) | (intByte4 & 0xff);
    return theInt;
  }

  private boolean compare(int condition, int value1, int value2) {

    switch (condition) {
      case EQ:
        return (value1 == value2);
      case NE:
        return (value1 != value2);
      case LT:
        return (value1 < value2);
      case LE:
        return (value1 <= value2);
      case GT:
        return (value1 > value2);
      case GE:
        return (value1 >= value2);
      default:
        throw new JVMSimError();
    }
  }

  // This is used by executeMULTIANEWARRAY(). It calls itself recursively
  // as many times as the multi-dimensional array is deep.
  private Object createMultiDimArray(int[] size) {
    Object result;
    if (size.length == 1) {
      result = new int[size[0]];
    } else {

      // Create and initialize an array of arrays
      Object[] arrayOfArrays = new Object[size[0]];
      result = arrayOfArrays;

      // As soon as a size of zero is hit for the next array, we are done. This
      // will be the case if some of the square brackets were left empty in
      // the declaration, as in "new int[5][4][][]," in which the third and fourth
      // sizes will be zero.
      if (size[1] != 0) {
        // Create and initialize an array of sizes to be passed to a recursive call
        // to createMultiDimArray(). This array is identical to the array passed
        // to this function with the first element clipped off.
        int[] nextSize = new int[size.length - 1];
        for (int i = 1; i < size.length; ++i) {
          nextSize[i - 1] = size[i];
        }

        // Call this function recursively to create initialize this array
        // of array with the sub-arrays.
        for (int i = 0; i < size[0]; ++i) {
          arrayOfArrays[i] = createMultiDimArray(nextSize);
        }
      }
    }
    return result;
  }

  private void executeAALOAD() {

    // Pop array index.
    int index = sf.popInt();

    // Pop reference to array of object references.
    // Cast generic object reference to a reference to an array of objects. This
    // will cause the JVM to do a checkcast instruction to make sure this is a
    // valid operation. An exception will be thrown if I've got any other kind
    // of array or object reference. Once this succeeds, I can use the arrayRef
    // as an array to get the index'th object reference and push it.
    Object[] theArray = (Object[]) sf.popObject();

    // Push the object reference at theArray[index].
    sf.pushObject(theArray[index]);
    ++pc;
  }

  private void executeACONST_NULL() {
    sf.pushObject(null);
    ++pc;
  }

  private void executeALOAD() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    Object theObject = sf.getLocalObject(locVarPos);
    sf.pushObject(theObject);
    ++pc;
  }

  private void executeALOAD_N(int locVarPos) {
    Object theObject = sf.getLocalObject(locVarPos);
    sf.pushObject(theObject);
    ++pc;
  }

  private void executeASTORE() {

    int locVar = ((int) code[pc + 1]) & 0xff;
    Object theObject = sf.popObject();
    sf.setLocalObject(locVar, theObject);
    pc += 2;
  }

  private void executeASTORE_N(int locVar) {
    Object theObject = sf.popObject();
    sf.setLocalObject(locVar, theObject);
    ++pc;
  }

  private void executeATHROW() {

    // This is hard-coded for the Play Ball! simulation. It assumes
    // the exception is caught by the first entry in the exception
    // table.
    pc = exceptionTable[0].getTarget();
  }

  private void executeBIPUSH() {
    byte theByte = code[pc + 1];
    sf.pushInt(theByte);
    pc += 2;
  }

  private void executeDADD() {
    double value2 = sf.popDouble();
    double value1 = sf.popDouble();
    double result = value1 + value2;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeD2F() {
    double value = sf.popDouble();
    float result = (float) value;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeD2I() {
    double value = sf.popDouble();
    int result = (int) value;
    sf.pushInt(result);
    ++pc;
  }

  private void executeD2L() {
    double value = sf.popDouble();
    long result = (long) value;
    sf.pushLong(result);
    ++pc;
  }

  private void executeDCMPG() {
    double value2 = sf.popDouble();
    double value1 = sf.popDouble();
    if (Double.isNaN(value1) || Double.isNaN(value2)) {
      sf.pushInt(1);
    } else if (value1 > value2) {
      sf.pushInt(1);
    } else if (value1 < value2) {
      sf.pushInt(-1);
    } else {
      sf.pushInt(0);
    }
    ++pc;
  }

  private void executeDCONST_N(double theDouble) {
    sf.pushDouble(theDouble);
    ++pc;
  }

  private void executeDDIV() {
    double value2 = sf.popDouble();
    double value1 = sf.popDouble();
    double result = value1 / value2;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeDLOAD() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    double theDouble = sf.getLocalDouble(locVarPos);
    sf.pushDouble(theDouble);
    pc += 2;
  }

  private void executeDLOAD_N(int locVarPos) {
    double theDouble = sf.getLocalDouble(locVarPos);
    sf.pushDouble(theDouble);
    ++pc;
  }

  private void executeDMUL() {
    double value2 = sf.popDouble();
    double value1 = sf.popDouble();
    double result = value1 * value2;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeDNEG() {
    double value = sf.popDouble();
    double result = -value;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeDREM() {
    double value2 = sf.popDouble();
    double value1 = sf.popDouble();
    double result = value1 % value2;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeDSTORE() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    double theDouble = sf.popDouble();
    sf.setLocalDouble(locVarPos, theDouble);
    pc += 2;
  }

  private void executeDSTORE_N(int locVarPos) {
    double theDouble = sf.popDouble();
    sf.setLocalDouble(locVarPos, theDouble);
    ++pc;
  }

  private void executeDSUB() {
    double value2 = sf.popDouble();
    double value1 = sf.popDouble();
    double result = value1 - value2;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeF2D() {
    float value = sf.popFloat();
    double result = (double) value;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeF2I() {
    float value = sf.popFloat();
    int result = (int) value;
    sf.pushInt(result);
    ++pc;
  }

  private void executeF2L() {
    float value = sf.popFloat();
    long result = (long) value;
    sf.pushLong(result);
    ++pc;
  }

  private void executeFADD() {
    float value2 = sf.popFloat();
    float value1 = sf.popFloat();
    float result = value1 + value2;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeFCONST_N(float value) {
    sf.pushFloat(value);
    ++pc;
  }

  private void executeFDIV() {
    float value2 = sf.popFloat();
    float value1 = sf.popFloat();
    float result = value1 / value2;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeFLOAD() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    float f = sf.getLocalFloat(locVarPos);
    sf.pushFloat(f);
    ++pc;
  }

  private void executeFLOAD_N(int locVarPos) {
    float f = sf.getLocalFloat(locVarPos);
    sf.pushFloat(f);
    ++pc;
  }

  private void executeFMUL() {
    float value2 = sf.popFloat();
    float value1 = sf.popFloat();
    float result = value1 * value2;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeFNEG() {
    float value = sf.popFloat();
    float result = -value;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeFREM() {
    float value2 = sf.popFloat();
    float value1 = sf.popFloat();
    float result = value1 % value2;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeFSTORE() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    float f = sf.popFloat();
    sf.setLocalFloat(locVarPos, f);
    ++pc;
  }

  private void executeFSTORE_N(int locVarPos) {
    float f = sf.popFloat();
    sf.setLocalFloat(locVarPos, f);
    ++pc;
  }

  private void executeFSUB() {
    float value2 = sf.popFloat();
    float value1 = sf.popFloat();
    float result = value1 - value2;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeGETSTATIC() {
    // This is hard-coded for the Play Ball! simulation.
    // This simulation code doesn't simulate the constant
    // pool, because it was only used once or twice in all
    // the bytecode sequences. In Play Ball!'s bytecode
    // sequence, getstatic refers to constant pool entry
    // six, which refers to a class variable named ball that
    // is of type Ball.
    sf.pushObject(constantPool[6].getStaticField());
    pc += 3;
  }

  private void executeGOTO() {
    branch();
  }

  private void executeI2B() {
    int value = sf.popInt();
    int result = (byte) value;
    sf.pushInt(result);
    ++pc;
  }

  private void executeI2C() {
    int value = sf.popInt();
    int result = (char) value;
    sf.pushInt(result);
    ++pc;
  }

  private void executeI2D() {
    int value = sf.popInt();
    double result = (double) value;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeI2F() {
    int value = sf.popInt();
    float result = (float) value;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeI2L() {
    int value = sf.popInt();
    long result = (long) value;
    sf.pushLong(result);
    ++pc;
  }

  private void executeI2S() {
    int value = sf.popInt();
    int result = (short) value;
    sf.pushInt(result);
    ++pc;
  }

  private void executeIADD() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 + value2;
    sf.pushInt(result);
    ++pc;
  }

  private void executeIAND() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 & value2;
    sf.pushInt(result);
    ++pc;
  }

  private void executeIASTORE() {

    // Pop int value.
    int value = sf.popInt();

    // Pop index.
    int index = sf.popInt();

    // Pop reference to an array of integers. Must cast the generic object
    // reference to a reference to an array of integers, then use that
    // to assign arrayRef[index] = value.
    int[] theArray = (int[]) sf.popObject();

    theArray[index] = value;
    ++pc;
  }

  private void executeICONST_N(int i) {
    sf.pushInt(i);
    ++pc;
  }

  private void executeIDIV() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 / value2;
    sf.pushInt(result);
    ++pc;
  }

  private void executeIF_ICMPCOND(int condition) {

    int value2 = sf.popInt();
    int value1 = sf.popInt();

    boolean branch = compare(condition, value1, value2);

    if (branch) {
      branch();
    } else {
      pc += 3;
    }
  }

  private void executeIFCOND(int condition) {
    int value = sf.popInt();
    boolean branch = compare(condition, value, 0);

    if (branch) {
      branch();
    } else {
      pc += 3;
    }
  }

  private void executeIINC() {
    int index = code[pc + 1];
    index &= 0xff; // index is an unsigned byte
    int constant = code[pc + 2]; // constant is a signed byte
    int locVar = sf.getLocalInt(index);
    locVar += constant;
    sf.setLocalInt(index, locVar);
    pc += 3;
  }

  private void executeILOAD() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    int val = sf.getLocalInt(locVarPos);
    sf.pushInt(val);
    ++pc;
  }

  private void executeILOAD_N(int locVarPos) {
    int val = sf.getLocalInt(locVarPos);
    sf.pushInt(val);
    ++pc;
  }

  private void executeIMUL() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 * value2;
    sf.pushInt(result);
    ++pc;
  }

  private void executeINEG() {
    int value = sf.popInt();
    int result = -value;
    sf.pushInt(result);
    ++pc;
  }

  private void executeINVOKESTATIC() {
    // Hardcoded to invoke Math.random() for Slice of Pi
    double value = sf.popDouble();
    double result = Math.sqrt(value);
    sf.pushDouble(result);
    pc += 3;
  }

  private void executeIOR() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 | value2;
    sf.pushInt(result);
    ++pc;
  }

  private void executeIREM() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 % value2;
    sf.pushInt(result);
    ++pc;
  }

  private void executeISHL() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 << (value2 & 0x1f);
    sf.pushInt(result);
    ++pc;
  }

  private void executeISHR() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 >> (value2 & 0x1f);
    sf.pushInt(result);
    ++pc;
  }

  private void executeISTORE() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    int val = sf.popInt();
    sf.setLocalInt(locVarPos, val);
    pc += 2;
  }

  private void executeISTORE_N(int locVarPos) {
    int val = sf.popInt();
    sf.setLocalInt(locVarPos, val);
    ++pc;
  }

  private void executeISUB() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 - value2;
    sf.pushInt(result);
    ++pc;
  }

  private void executeIUSHR() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 >>> (value2 & 0x1f);
    sf.pushInt(result);
    ++pc;
  }

  private void executeIXOR() {
    int value2 = sf.popInt();
    int value1 = sf.popInt();
    int result = value1 ^ value2;
    sf.pushInt(result);
    ++pc;
  }

  private void executeJSR() {
    int offset = getShortOperand(pc + 1);
    sf.pushReturnAddress(new ReturnAddress(pc + 3));
    pc += offset;
  }

  private void executeL2D() {
    long value = sf.popLong();
    double result = (double) value;
    sf.pushDouble(result);
    ++pc;
  }

  private void executeL2F() {
    long value = sf.popLong();
    float result = (float) value;
    sf.pushFloat(result);
    ++pc;
  }

  private void executeL2I() {
    long value = sf.popLong();
    int result = (int) value;
    sf.pushInt(result);
    ++pc;
  }

  private void executeLADD() {
    long value2 = sf.popLong();
    long value1 = sf.popLong();
    long result = value1 + value2;
    sf.pushLong(result);
    ++pc;
  }

  private void executeLAND() {
    long value2 = sf.popLong();
    long value1 = sf.popLong();
    long result = value1 & value2;
    sf.pushLong(result);
    ++pc;
  }

  private void executeLCONST_N(long theLong) {
    sf.pushLong(theLong);
    ++pc;
  }

  private void executeLDC2_W() {
    short cpIndex = getShortOperand(pc + 1);
    // Hardcoded for Slices of Pi
    if (cpIndex == 6) {
      sf.pushDouble(0.5);
    } else if (cpIndex == 8) {
      sf.pushDouble(2.0);
    } else if (cpIndex == 10) {
      sf.pushDouble(4.0);
    }
    pc += 3;
  }

  private void executeLDIV() {
    long value2 = sf.popLong();
    long value1 = sf.popLong();
    long result = value1 / value2;
    sf.pushLong(result);
    ++pc;
  }

  private void executeLLOAD() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    long theLong = sf.getLocalLong(locVarPos);
    sf.pushLong(theLong);
    pc += 2;
  }

  private void executeLLOAD_N(int locVarPos) {
    long theLong = sf.getLocalLong(locVarPos);
    sf.pushLong(theLong);
    ++pc;
  }

  private void executeLMUL() {
    long value2 = sf.popLong();
    long value1 = sf.popLong();
    long result = value1 * value2;
    sf.pushLong(result);
    ++pc;
  }

  private void executeLNEG() {
    long value = sf.popLong();
    long result = -value;
    sf.pushLong(result);
    ++pc;
  }

  private void executeLOR() {
    long value2 = sf.popLong();
    long value1 = sf.popLong();
    long result = value1 | value2;
    sf.pushLong(result);
    ++pc;
  }

  private void executeLREM() {
    long value2 = sf.popLong();
    long value1 = sf.popLong();
    long result = value1 % value2;
    sf.pushLong(result);
    ++pc;
  }

  private void executeLSHL() {
    int value2 = sf.popInt();
    long value1 = sf.popLong();
    long result = value1 << (value2 & 0x3f);
    sf.pushLong(result);
    ++pc;
  }

  private void executeLSHR() {
    int value2 = sf.popInt();
    long value1 = sf.popLong();
    long result = value1 >> (value2 & 0x3f);
    sf.pushLong(result);
    ++pc;
  }

  private void executeLSTORE() {
    int locVarPos = ((int) code[pc + 1]) & 0xff;
    long theLong = sf.popLong();
    sf.setLocalLong(locVarPos, theLong);
    pc += 2;
  }

  private void executeLSTORE_N(int locVarPos) {
    long theLong = sf.popLong();
    sf.setLocalLong(locVarPos, theLong);
    ++pc;
  }

  private void executeLSUB() {
    long value2 = sf.popLong();
    long value1 = sf.popLong();
    long result = value1 - value2;
    sf.pushLong(result);
    ++pc;
  }

  private void executeLUSHR() {
    int value2 = sf.popInt();
    long value1 = sf.popLong();
    long result = value1 >>> (value2 & 0x3f);
    sf.pushLong(result);
    ++pc;
  }

  private void executeLXOR() {
    long value2 = sf.popLong();
    long value1 = sf.popLong();
    long result = value1 ^ value2;
    sf.pushLong(result);
    ++pc;
  }

  private void executeMULTIANEWARRAY() {

    int index = getShortOperand(pc + 1);
    int dim = code[pc + 3];
    if (dim < 1) {
      throw new JVMSimError();
    }

    // Fill an array with the sizes of the various arrays. The sizes go into the
    // array in the order in which they appear in the declaration, left to right.
    // This was the same order in which they were pushed onto the stack. Therefore,
    // the first element is assigned the value most buried (furthest down) in the
    // stack.
    int[] size = new int[dim];
    for (int i = dim - 1; i >= 0; --i) {
      size[i] = sf.popInt();
    }

    // This time around, I'll just assume it's an array of ints. In the future, I'll
    // need to check the constant pool and pass down the type.
    Object result = createMultiDimArray(size);

    sf.pushObject(result);
    pc += 4;
  }

  private void executePOP() {
    sf.pop();
    ++pc;
  }

  private void executeRET() {
    int index = ((int) code[pc + 1]) & 0xff;
    ReturnAddress returnAddress = (ReturnAddress) sf.getLocalObject(index);
    pc = returnAddress.getReturnAddress();
  }

  private void executeSIPUSH() {
    short theShort = getShortOperand(pc + 1);
    sf.pushInt(theShort);
    pc += 3;
  }

  private void executeTABLESWITCH() {

    // Skip zero to three places so the addresses are 32-bit aligned assuming
    // that the first instruction in the method is at address zero.
    int padding = (pc + 1) % 4;
    int pcPlusPadding = pc + padding;

    // Get the default branch offset
    int offset = getIntOperand(pcPlusPadding + 1);

    // Get the low value
    int low = getIntOperand(pcPlusPadding + 5);

    // Get the high value
    int high = getIntOperand(pcPlusPadding + 9);

    // Pop the index (the value of the switch expression) off
    // of the stack
    int index = sf.popInt();

    // If the index is within the low-high range, grab an offset
    // from the table. Else, use the default offset.
    if ((index >= low) || (index <= high)) {

      int result = index - low;
      result *= 4;
      result += pcPlusPadding + 12;

      // Get the branch offset
      offset = getIntOperand(result + 1);
    }
    pc += offset;
  }
}
