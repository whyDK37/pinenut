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

/**
 * This class represents that portion of the user interface
 * that displays the bytecodes in the method area.
 *
 * @author Bill Venners
 */
class MethodAreaPanel extends Panel {

  private int memoryLocationsVisibleCount;

  private Label[] pointer;
  private Label[] address;
  private Label[] byteValue;
  private Label[] logicalValue;

  private int[] theProgram;
  private String[] theMnemonics;

  private int firstVisibleRow;
  private int currentProgramCounterRow;

  private ColoredLabel pcRegister;

  MethodAreaPanel(int memoryLocationsVisibleCount, int[] theProgram,
                  String[] theMnemonics) {

    this.memoryLocationsVisibleCount = memoryLocationsVisibleCount;
    this.theProgram = theProgram;
    this.theMnemonics = theMnemonics;

    pointer = new Label[memoryLocationsVisibleCount];
    address = new Label[memoryLocationsVisibleCount];
    byteValue = new Label[memoryLocationsVisibleCount];
    logicalValue = new Label[memoryLocationsVisibleCount];

    // Initialize the title
    Label title = new Label(StringTable.theMethod, Label.CENTER);
    title.setFont(new Font("Helvetica", Font.BOLD, 11));

    // Initialize the labelled pc register panel
    Panel registerPanel = new Panel();
    registerPanel.setLayout(new GridLayout(1, 2, 5, 5));
    pcRegister = new ColoredLabel("00000000", Label.CENTER, Color.lightGray);
    pcRegister.setFont(new Font("TimesRoman", Font.PLAIN, 11));
    Label pcRegTitle = new Label(StringTable.pc, Label.RIGHT);
    pcRegTitle.setFont(new Font("Helvetica", Font.ITALIC, 11));
    registerPanel.add(pcRegTitle);
    registerPanel.add(pcRegister);

    // Put the title and the pc register panel on the same panel
    Panel titleRegisterPanel = new Panel();
    titleRegisterPanel.setLayout(new BorderLayout());
    titleRegisterPanel.add("West", registerPanel);
    titleRegisterPanel.add("Center", title);

    // Initialize column titles
    Panel columnTitles = new Panel();
    int[] hComponentCellWidths = {2, 2, 2, 3};
    columnTitles.setLayout(new GridSnapLayout(1, 9, hComponentCellWidths));
    columnTitles.setFont(new Font("Helvetica", Font.ITALIC, 11));
    columnTitles.add(new Label("", Label.CENTER));
    columnTitles.add(new Label(StringTable.offset, Label.CENTER));
    columnTitles.add(new Label(StringTable.bytecodes, Label.CENTER));
    columnTitles.add(new Label(StringTable.mnemonics, Label.LEFT));

    // Initialize the 4 column view of the method
    Panel methodView = new Panel();
    methodView.setLayout(new GridSnapLayout(memoryLocationsVisibleCount, 9, hComponentCellWidths));
    methodView.setBackground(Color.lightGray);
    Font plainFont = new Font("TimesRoman", Font.PLAIN, 11);
    methodView.setFont(plainFont);
    Font italicFont = new Font("TimesRoman", Font.ITALIC, 11);

    for (int i = 0; i < memoryLocationsVisibleCount; ++i) {

      pointer[i] = new Label("", Label.RIGHT);
      pointer[i].setFont(italicFont);
      methodView.add(pointer[i]);

      address[i] = new Label("", Label.CENTER);
      methodView.add(address[i]);

      byteValue[i] = new Label("", Label.CENTER);
      methodView.add(byteValue[i]);

      logicalValue[i] = new Label("", Label.LEFT);
      methodView.add(logicalValue[i]);
    }

    Panel methodViewWithTitles = new Panel();
    methodViewWithTitles.setLayout(new BorderLayout());
    methodViewWithTitles.add("North", columnTitles);
    methodViewWithTitles.add("Center", methodView);

    setLayout(new BorderLayout());
    add("North", titleRegisterPanel);
    add("Center", methodViewWithTitles);

    // Call updateView() first because updateProgramCounter() only changes
    // the address, byte, and mnemonic columns if the program counter has
    // moved off the page.
    updateView(0);
    updateProgramCounter(0);
  }

  // UpdateView() repaints the rightmost three columns: address, byte, and mnemonic
  // starting with the passed initialaddress of theProgram.
  private void updateView(int initialAddress) {

    int filledRowsCount = theProgram.length < memoryLocationsVisibleCount ?
            theProgram.length : memoryLocationsVisibleCount;

    for (int i = 0; i < filledRowsCount; ++i) {

      address[i].setText(Integer.toString(initialAddress + i));

      int theByte = theProgram[initialAddress + i];
      HexString byteValueHexString = new HexString(theByte, 2);
      byteValue[i].setText(byteValueHexString.getString());

      String logicalValueStr = theMnemonics[initialAddress + i];
      logicalValue[i].setText(logicalValueStr);
    }
  }

  // Will change the pc register and pc> pointer. If necessary, will also shift
  // the other three columns--address, byte, and mnemonic--by calling updateView().
  public void updateProgramCounter(int newPCValue) {

    // Erase the old "pc>" pointer
    pointer[currentProgramCounterRow].setText("");

    // Only change the address, byte, and mnemonic columns if the program
    // counter has gone off the edge of the screen.
    if (newPCValue - firstVisibleRow >= memoryLocationsVisibleCount) {
      firstVisibleRow = newPCValue;
      if (firstVisibleRow > theProgram.length - memoryLocationsVisibleCount) {
        firstVisibleRow = theProgram.length - memoryLocationsVisibleCount;
      }
      updateView(firstVisibleRow);
    } else if (newPCValue < firstVisibleRow) {
      firstVisibleRow = newPCValue;
      updateView(firstVisibleRow);
    }

    // Place the "pc>" pointer
    pointer[newPCValue - firstVisibleRow].setText(StringTable.pcPointer);
    currentProgramCounterRow = newPCValue - firstVisibleRow;

    // Set the program counter register
    //HexString hexString = new HexString(newPCValue, 8);
    pcRegister.setLabelText("    " + Integer.toString(newPCValue));
  }

  public Insets insets() {
    return new Insets(5, 5, 5, 5);
  }
}
