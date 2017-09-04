package jvm.applet.ii;/*
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

import java.applet.Applet;
import java.awt.*;

/**
 * This applet allows the user to experiment with the two's-complement
 * format.
 *
 * @author Bill Venners
 */
public class InnerInt extends Applet {

  private Label binaryField;
  private Label hexField;
  private Label decimalField;
  private int value;

  private GrayButton maximumButton = new GrayButton(StringTable.max);
  private GrayButton minimumButton = new GrayButton(StringTable.min);
  private GrayButton zeroButton = new GrayButton(StringTable.zero);

  public void init() {

    Panel buttonPanel = new PanelWithInsets(0, 0, 0, 0);
    buttonPanel.setLayout(new GridLayout(3, 2, 5, 5));
    buttonPanel.add(new GrayButton(StringTable.increment));
    buttonPanel.add(new GrayButton(StringTable.decrement));
    buttonPanel.add(minimumButton);
    buttonPanel.add(maximumButton);
    buttonPanel.add(zeroButton);
    buttonPanel.add(new GrayButton(StringTable.negate));
    zeroButton.disable();

    binaryField = new Label("00000000000000000000000000000000");
    hexField = new Label("00000000");
    decimalField = new Label("0");

    Font fieldFont = new Font("TimesRoman", Font.PLAIN, 12);
    binaryField.setFont(fieldFont);
    hexField.setFont(fieldFont);
    decimalField.setFont(fieldFont);

    Panel numberPanel = new Panel();
    numberPanel.setBackground(Color.white);
    numberPanel.setLayout(new GridLayout(3, 1));
    Panel binaryPanel = new Panel();
    binaryPanel.setLayout(new BorderLayout());
    binaryPanel.add("Center", binaryField);
    numberPanel.add(binaryPanel);

    Panel hexPanel = new Panel();
    hexPanel.setLayout(new BorderLayout());
    hexPanel.add("Center", hexField);
    numberPanel.add(hexPanel);
    numberPanel.add(decimalField);

    Panel labelPanel = new Panel();
    labelPanel.setBackground(Color.white);
    labelPanel.setLayout(new GridLayout(3, 1));
    Label label = new Label(StringTable.binary, Label.CENTER);
    Font labelFont = new Font("Helvetica", Font.ITALIC, 11);
    label.setFont(labelFont);
    labelPanel.add(label);
    label = new Label(StringTable.hex, Label.CENTER);
    label.setFont(labelFont);
    labelPanel.add(label);
    label = new Label(StringTable.decimal, Label.CENTER);
    label.setFont(labelFont);
    labelPanel.add(label);

    Panel dataPanel = new Panel();
    dataPanel.setLayout(new BorderLayout());
    dataPanel.add("West", labelPanel);
    dataPanel.add("Center", numberPanel);

    ColoredLabel title = new ColoredLabel(StringTable.title, Label.CENTER, Color.yellow);
    title.setFont(new Font("Helvetica", Font.BOLD, 12));

    setBackground(Color.blue);
    setLayout(new BorderLayout(5, 5));
    add("North", title);
    add("West", buttonPanel);
    add("Center", dataPanel);
  }

  public boolean action(Event evt, Object arg) {

    if (evt.target instanceof Button) {
      String bname = (String) arg;
      if (bname.equals(StringTable.increment)) {

        ++value;
      } else if (bname.equals(StringTable.decrement)) {

        --value;
      } else if (bname.equals(StringTable.min)) {

        value = 0x80000000;
      } else if (bname.equals(StringTable.max)) {

        value = 0x7fffffff;
      } else if (bname.equals(StringTable.zero)) {

        value = 0;
      } else if (bname.equals(StringTable.negate)) {

        value *= -1;
      }
      UpdateNumberFields();
      enableDisableButton(maximumButton, Integer.MAX_VALUE);
      enableDisableButton(minimumButton, Integer.MIN_VALUE);
      enableDisableButton(zeroButton, 0);
    }
    return true;
  }

  void enableDisableButton(Button b, int val) {

    if (!b.isEnabled()) {
      if (value != val) {
        b.enable();
      }
    } else if (value == val) {
      b.disable();
    }
  }

  void UpdateNumberFields() {
    decimalField.setText(Integer.toString(value));

    int v = value;
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < 8; ++i) {
      // Get lowest bit
      int remainder = v & 0xf;

      // Convert bit to a character and insert it into the beginning of the string
      switch (remainder) {
        case 0:
          buf.insert(0, "0");
          break;
        case 1:
          buf.insert(0, "1");
          break;
        case 2:
          buf.insert(0, "2");
          break;
        case 3:
          buf.insert(0, "3");
          break;
        case 4:
          buf.insert(0, "4");
          break;
        case 5:
          buf.insert(0, "5");
          break;
        case 6:
          buf.insert(0, "6");
          break;
        case 7:
          buf.insert(0, "7");
          break;
        case 8:
          buf.insert(0, "8");
          break;
        case 9:
          buf.insert(0, "9");
          break;
        case 10:
          buf.insert(0, "a");
          break;
        case 11:
          buf.insert(0, "b");
          break;
        case 12:
          buf.insert(0, "c");
          break;
        case 13:
          buf.insert(0, "d");
          break;
        case 14:
          buf.insert(0, "e");
          break;
        case 15:
          buf.insert(0, "f");
          break;
      }

      // Shift the int to the right one bit
      v >>>= 4;
    }
    hexField.setText(buf.toString());

    v = value;
    buf.setLength(0);
    for (int i = 0; i < 32; ++i) {
      // Get lowest bit
      int remainder = v & 0x1;

      // Convert bit to a character and insert it into the beginning of the string
      if (remainder == 0) {
        buf.insert(0, "0");
      } else {
        buf.insert(0, "1");
      }

      // Shift the int to the right one bit
      v >>>= 1;
    }
    binaryField.setText(buf.toString());
  }

  public Insets insets() {
    return new Insets(5, 5, 5, 5);
  }
}