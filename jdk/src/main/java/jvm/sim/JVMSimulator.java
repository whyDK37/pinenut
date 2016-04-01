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

import java.awt.*;
import java.applet.*;

/**
* An applet that simulates the Java Virtual Machine executing a
* sequence of bytecodes.
*
* @author      Bill Venners
*/
public abstract class JVMSimulator extends Applet implements Runnable {

    ColoredLabel explanationLabel = new ColoredLabel("",
        Label.CENTER, Color.lightGray);
    GrayButton stepButton = new GrayButton(StringTable.step);
    GrayButton resetButton = new GrayButton(StringTable.reset);
    GrayButton runButton = new GrayButton(StringTable.run);
    GrayButton stopButton = new GrayButton(StringTable.stop);
    MethodAreaPanel methodAreaPanel;
    LocalVarsPanel localVarsPanel;
    OperandStackPanel operandStackPanel;
    int pcRegister;
    int optopRegister;
    StackFrame currentFrame;
    Method currentMethod;
    int bytecodeViewSize;
    int[] theProgram;
    String[] bytecodeMnemonics;
    int maxStack;
    int maxLocals;
	ExceptionTableEntry[] exceptionTable;
	ConstantPoolEntry[] constantPool;
	boolean stoppedThreadWhenLeftPage = false;

    // If the "run" button is pushed, a separate thread will be invoked that
    // will cause the JVM to execute until the "stop" button is pressed.
    Thread runner;
    final int millisecondDelayBetweenSteps = 250;

    public void init() {

        theProgram = getTheProgram();
        bytecodeMnemonics = getBytecodeMnemonics();
        bytecodeViewSize = getBytecodeViewSize();
        maxStack = getMaxStack();
        maxLocals = getMaxLocals();
		exceptionTable = getExceptionTable();
		constantPool = getConstantPool();

        methodAreaPanel = new MethodAreaPanel(bytecodeViewSize,
            theProgram, bytecodeMnemonics);
        localVarsPanel = new LocalVarsPanel(maxLocals);
        operandStackPanel = new OperandStackPanel(maxStack);

        // Set background and layout for the applet panel
        setBackground(getAppletBackgroundColor());
        setLayout(new BorderLayout(5, 5));

        // Initialize the title bar
        ColoredLabel title = new ColoredLabel(getAppletTitle(),
            Label.CENTER, getTitleColor());
        title.setFont(new Font("Helvetica", Font.BOLD, 12));

        // Initialize the control panel
        Panel controlPanel = new Panel();
        controlPanel.setLayout(new BorderLayout(5, 5));

        // Build the panel of four buttons
        Panel leftButtonPanel = new Panel();
        leftButtonPanel.setLayout(new GridLayout(2,2,5,5));
        leftButtonPanel.add(stepButton);
        resetButton.disable();
        leftButtonPanel.add(runButton);
        leftButtonPanel.add(resetButton);
        leftButtonPanel.add(stopButton);
        stopButton.disable();

        // Initialize the explanation label
        explanationLabel.setBackground(getExplanationLabelColor());
        Font plainFont = new Font("TimesRoman", Font.ITALIC, 12);
        explanationLabel.setFont(plainFont);

        // Place the four button panel and explanation label onto
        // the control panel
        controlPanel.add("West", leftButtonPanel);
        controlPanel.add("Center", explanationLabel);

        // Initialize the panel that holds both the stack and
        // the method area (the twoParts panel)
        Panel twoParts = new Panel();
        twoParts.setLayout(new GridLayout(1, 2, 5, 5));

        Panel stackPanel = new Panel();
        stackPanel.setLayout(new GridLayout(2, 1, 5, 5));
        stackPanel.add(localVarsPanel);
        stackPanel.add(operandStackPanel);
        stackPanel.setBackground(getStackAreaColor());
        methodAreaPanel.setBackground(getMethodAreaColor());

        // Add stack and methodarea panels to the twoParts panel
        twoParts.add(stackPanel);
        twoParts.add(methodAreaPanel);

        // Place the title, controlPanel, and twoParts onto the applet
        // panel.
        add("North", title);
        add("South", controlPanel);
        add("Center", twoParts);

        // Place the bytecodes into a byte array.
        byte[] ba = new byte[theProgram.length];
        for (int i = 0; i < ba.length; ++i) {

            ba[i] = (byte) theProgram[i];
        }

        // Pass byte array to new StackFrame
        currentFrame = new StackFrame(maxStack, maxLocals);
        currentMethod = new Method(currentFrame, ba, exceptionTable, constantPool);

        resetState();
        updateStateDisplay();
    }

    // If they leave the page, stop a Run button press, then restart
    // automatically if they come back. In effect, leaving the page is like clicking
    // the Stop button. Returning to the page is like clicking the Run button.
    public void start() {
        if (runner == null && stoppedThreadWhenLeftPage) {
            stopButton.enable();
            runButton.disable();
            stepButton.disable();
            resetButton.disable();
            stoppedThreadWhenLeftPage = false;
            runner = new Thread(this);
            runner.start();
        }
    }

    public void stop() {
        runButton.enable();
        stepButton.enable();
        resetButton.enable();
        stopButton.disable();
        if (runner != null) {
            // Synchronize on currentMethod to allow the thread
            // to complete execution of the current instruction before
            // killing it.
            synchronized (currentMethod) {
                runner.stop();
            }
            runner = null;
            stoppedThreadWhenLeftPage = true;
        }
    }

    // Pushing the Reset button will cause resetState() to be executed, which will
    // reset all the data to its initial values.
    void resetState() {

        pcRegister = 0;
        optopRegister = 0;

        currentMethod.resetState();
    }

    // updateStateDisplay writes the current state of the JVM to the UI.
    void updateStateDisplay() {

        methodAreaPanel.updateProgramCounter(pcRegister);
        updateExplanationText();

        localVarsPanel.updateView(currentFrame.getLocalVars());
        operandStackPanel.updateView(currentFrame.getOptop(),
            currentFrame.getOperandStack());
    }

    public void run() {
        while (true) {
            try {
                pcRegister = currentMethod.executeNextInstruction();
            }
            catch (BreakpointException be) {
                // On a breakpoint, kill the thread.
                runButton.enable();
                stepButton.enable();
                resetButton.enable();
                stopButton.disable();
                if (runner != null) {
                    Thread runnerAboutToStop = runner;
                    runner = null;
                    runnerAboutToStop.stop();
                }
            }
            updateStateDisplay();
            try {
                Thread.sleep(millisecondDelayBetweenSteps);
            }
            catch (InterruptedException e) {
            }
        }
    }

    // updateExplanationText() prints out a line of text to the bottom of the
    // applet that describes the next opcode to execute.
    private void updateExplanationText() {

        int nextOpCode = theProgram[pcRegister];

        switch (nextOpCode) {

        case OpCode.AALOAD:
            explanationLabel.setLabelText(StringTable.aaloadText);
            break;

        case OpCode.ALOAD_0:
            explanationLabel.setLabelText(StringTable.aload_0Text);
            break;

        case OpCode.ALOAD_1:
            explanationLabel.setLabelText(StringTable.aload_1Text);
            break;

        case OpCode.ALOAD_2:
            explanationLabel.setLabelText(StringTable.aload_2Text);
            break;

        case OpCode.ALOAD_3:
            explanationLabel.setLabelText(StringTable.aload_3Text);
            break;

        case OpCode.ASTORE:
            explanationLabel.setLabelText(StringTable.astoreText);
            break;

        case OpCode.ASTORE_0:
            explanationLabel.setLabelText(StringTable.astore_0Text);
            break;

        case OpCode.ASTORE_1:
            explanationLabel.setLabelText(StringTable.astore_1Text);
            break;

        case OpCode.ASTORE_2:
            explanationLabel.setLabelText(StringTable.astore_2Text);
            break;

        case OpCode.ASTORE_3:
            explanationLabel.setLabelText(StringTable.astore_3Text);
            break;

        case OpCode.BIPUSH:
            explanationLabel.setLabelText(StringTable.bipushText);
            break;

        case OpCode.BREAKPOINT:
            explanationLabel.setLabelText(StringTable.breakpointText);
            break;

        case OpCode.DCMPG:
            explanationLabel.setLabelText(StringTable.dcmpgText);
            break;

        case OpCode.DCONST_0:
            explanationLabel.setLabelText(StringTable.dconst_0Text);
            break;

        case OpCode.DCONST_1:
            explanationLabel.setLabelText(StringTable.dconst_1Text);
            break;

        case OpCode.DDIV:
            explanationLabel.setLabelText(StringTable.ddivText);
            break;

        case OpCode.DLOAD:
            explanationLabel.setLabelText(StringTable.dloadText);
            break;

        case OpCode.DLOAD_0:
            explanationLabel.setLabelText(StringTable.dload_0Text);
            break;

        case OpCode.DLOAD_2:
            explanationLabel.setLabelText(StringTable.dload_2Text);
            break;

        case OpCode.DMUL:
            explanationLabel.setLabelText(StringTable.dmulText);
            break;

        case OpCode.DSTORE:
            explanationLabel.setLabelText(StringTable.dstoreText);
            break;

        case OpCode.DSTORE_0:
            explanationLabel.setLabelText(StringTable.dstore_0Text);
            break;

        case OpCode.DSTORE_2:
            explanationLabel.setLabelText(StringTable.dstore_2Text);
            break;

        case OpCode.DSUB:
            explanationLabel.setLabelText(StringTable.dsubText);
            break;

        case OpCode.FCONST_0:
            explanationLabel.setLabelText(StringTable.fconst_0Text);
            break;

        case OpCode.FCONST_2:
            explanationLabel.setLabelText(StringTable.fconst_2Text);
            break;

        case OpCode.FLOAD_0:
            explanationLabel.setLabelText(StringTable.fload_0Text);
            break;

        case OpCode.FMUL:
            explanationLabel.setLabelText(StringTable.fmulText);
            break;

        case OpCode.FSTORE_0:
            explanationLabel.setLabelText(StringTable.fstore_0Text);
            break;

        case OpCode.FSUB:
            explanationLabel.setLabelText(StringTable.fsubText);
            break;

        case OpCode.GETSTATIC:
            explanationLabel.setLabelText(StringTable.getstaticText);
            break;

        case OpCode.GOTO:
            explanationLabel.setLabelText(StringTable.gotoText);
            break;

        case OpCode.IADD:
            explanationLabel.setLabelText(StringTable.iaddText);
            break;

        case OpCode.IAND:
            explanationLabel.setLabelText(StringTable.iandText);
            break;

        case OpCode.IASTORE:
            explanationLabel.setLabelText(StringTable.iastoreText);
            break;

        case OpCode.ICONST_M1:
            explanationLabel.setLabelText(StringTable.iconst_m1Text);
            break;

        case OpCode.ICONST_0:
            explanationLabel.setLabelText(StringTable.iconst_0Text);
            break;

        case OpCode.ICONST_1:
            explanationLabel.setLabelText(StringTable.iconst_1Text);
            break;

        case OpCode.ICONST_2:
            explanationLabel.setLabelText(StringTable.iconst_2Text);
            break;

        case OpCode.ICONST_3:
            explanationLabel.setLabelText(StringTable.iconst_3Text);
            break;

        case OpCode.ICONST_4:
            explanationLabel.setLabelText(StringTable.iconst_4Text);
            break;

        case OpCode.ICONST_5:
            explanationLabel.setLabelText(StringTable.iconst_5Text);
            break;

        case OpCode.IF_ICMPGT:
            explanationLabel.setLabelText(StringTable.if_icmpgtText);
            break;

        case OpCode.IF_ICMPLT:
            explanationLabel.setLabelText(StringTable.if_icmpltText);
            break;

        case OpCode.IF_ICMPNE:
            explanationLabel.setLabelText(StringTable.if_icmpneText);
            break;

        case OpCode.IDIV:
            explanationLabel.setLabelText(StringTable.idivText);
            break;

        case OpCode.IFEQ:
            explanationLabel.setLabelText(StringTable.ifeqText);
            break;

        case OpCode.IFLT:
            explanationLabel.setLabelText(StringTable.ifltText);
            break;

        case OpCode.IFNE:
            explanationLabel.setLabelText(StringTable.ifneText);
            break;

        case OpCode.IINC:
            explanationLabel.setLabelText(StringTable.iincText);
            break;

        case OpCode.ILOAD_0:
            explanationLabel.setLabelText(StringTable.iload_0Text);
            break;

        case OpCode.ILOAD_1:
            explanationLabel.setLabelText(StringTable.iload_1Text);
            break;

        case OpCode.ILOAD_2:
            explanationLabel.setLabelText(StringTable.iload_2Text);
            break;

        case OpCode.ILOAD_3:
            explanationLabel.setLabelText(StringTable.iload_3Text);
            break;

        case OpCode.IMUL:
            explanationLabel.setLabelText(StringTable.imulText);
            break;

        case OpCode.I2B:
            explanationLabel.setLabelText(StringTable.i2bText);
            break;

        case OpCode.INVOKESTATIC:
            explanationLabel.setLabelText(StringTable.invokestaticText);
            break;

        case OpCode.IOR:
            explanationLabel.setLabelText(StringTable.iorText);
            break;

        case OpCode.IREM:
            explanationLabel.setLabelText(StringTable.iremText);
            break;

        case OpCode.ISHL:
            explanationLabel.setLabelText(StringTable.ishlText);
            break;

        case OpCode.ISTORE:
            explanationLabel.setLabelText(StringTable.istoreText);
            break;

        case OpCode.ISTORE_0:
            explanationLabel.setLabelText(StringTable.istore_0Text);
            break;

        case OpCode.ISTORE_1:
            explanationLabel.setLabelText(StringTable.istore_1Text);
            break;

        case OpCode.ISTORE_2:
            explanationLabel.setLabelText(StringTable.istore_2Text);
            break;

        case OpCode.ISTORE_3:
            explanationLabel.setLabelText(StringTable.istore_3Text);
            break;

        case OpCode.IXOR:
            explanationLabel.setLabelText(StringTable.ixorText);
            break;

        case OpCode.JSR:
            explanationLabel.setLabelText(StringTable.jsrText);
            break;

        case OpCode.LADD:
            explanationLabel.setLabelText(StringTable.laddText);
            break;

        case OpCode.LCONST_1:
            explanationLabel.setLabelText(StringTable.lconst_1Text);
            break;

        case OpCode.LDC2_W:
            explanationLabel.setLabelText(StringTable.ldc2_wText);
            break;

        case OpCode.LLOAD:
            explanationLabel.setLabelText(StringTable.lloadText);
            break;

        case OpCode.LLOAD_0:
            explanationLabel.setLabelText(StringTable.lload_0Text);
            break;

        case OpCode.LLOAD_2:
            explanationLabel.setLabelText(StringTable.lload_2Text);
            break;

        case OpCode.LSTORE:
            explanationLabel.setLabelText(StringTable.lstoreText);
            break;

        case OpCode.LSTORE_0:
            explanationLabel.setLabelText(StringTable.lstore_0Text);
            break;

        case OpCode.LSTORE_2:
            explanationLabel.setLabelText(StringTable.lstore_2Text);
            break;

        case OpCode.MULTIANEWARRAY:
            explanationLabel.setLabelText(StringTable.multianewarrayText);
            break;

        case OpCode.POP:
            explanationLabel.setLabelText(StringTable.popText);
            break;

        case OpCode.RET:
            explanationLabel.setLabelText(StringTable.retText);
            break;

        case OpCode.TABLESWITCH:
            explanationLabel.setLabelText(StringTable.tableswitchText);
            break;

        default:
            explanationLabel.setLabelText("");
            break;
        }
    }

    // Make pretty border around entire applet panel
    public Insets insets() {
        return new Insets(5, 5, 5, 5);
    }

    public boolean action(Event evt, Object arg) {
        if (evt.target instanceof Button) {
            String bname = (String) arg;
            if (bname.equals(StringTable.reset)) {

                stopButton.disable();
                runButton.enable();
                stepButton.enable();
                resetButton.disable();
                resetState();
                updateStateDisplay();
            }
            else if (bname.equals(StringTable.step)) {

                resetButton.enable();
                try {
                    pcRegister = currentMethod.executeNextInstruction();
                }
                catch (BreakpointException be) {
                    // Ignore this. User can reset.
                }
                updateStateDisplay();
            }
            else if (bname.equals(StringTable.run)) {

                stopButton.enable();
                runButton.disable();
                stepButton.disable();
                resetButton.disable();
                if (runner == null) {
                    runner = new Thread(this);
                    runner.start();
                }
            }
            else if (bname.equals(StringTable.stop)) {

                runButton.enable();
                stepButton.enable();
                resetButton.enable();
                stopButton.disable();
                if (runner != null) {
                    // Synchronize on currentMethod to allow the thread
                    // to complete execution of the current instruction before
                    // killing it.
                    synchronized (currentMethod) {
                        runner.stop();
                    }
                    runner = null;
                }
            }
        }
        return true;
    }

    public abstract int[] getTheProgram();
    public abstract String[] getBytecodeMnemonics();
    public abstract int getBytecodeViewSize();
    public abstract int getMaxStack();
    public abstract int getMaxLocals();
    public abstract Color getMethodAreaColor();
    public abstract Color getStackAreaColor();
    public abstract Color getExplanationLabelColor();
    public abstract Color getTitleColor();
    public abstract Color getAppletBackgroundColor();
    public abstract String getAppletTitle();

	// Some methods don't have an exception table. They can
	// use this default implementation of getExceptionTable
    public ExceptionTableEntry[] getExceptionTable() {
        return null;
    }

	// All methods have a constant pool, but not all simulations
	// use it. Those that don't use a constant pool can
	// use this default implementation of getConstantPool
    public ConstantPoolEntry[] getConstantPool() {
        return null;
    }
}
