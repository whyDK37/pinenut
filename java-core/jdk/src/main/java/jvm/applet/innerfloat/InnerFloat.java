package jvm.applet.innerfloat;/*
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
import java.applet.*;

/**
* This applet allows the user to experiment with the IEEE 754
* floating point format.
*
* @author  Bill Venners
*/
public class InnerFloat extends Applet {

    private Label binaryField;
    private Label signField;
    private Label exponentField;
    private Label mantissaField;
    private Label hexField;
    private Label base2Field;
    private Label base10Field;
    private float value;

    private final String titleString = "INNER FLOAT";
    private final String binaryString = "binary";
    private final String signString = "sign";
    private final String exponentString = "exponent";
    private final String mantissaString = "mantissa";
    private final String hexString = "hex";
    private final String base10String = "radix 10";
    private final String base2String = "radix 2";

    private final String incrementButtonString = "++";
    private final String decrementButtonString = "--";
    private final String multByZeroButtonString = "*=(0)";
    private final String piButtonString = "Pi";
    private final String positiveInfinityButtonString = "+Inf";
    private final String negativeInfinityButtonString = "-Inf";
    private final String maximumButtonString = "Max";
    private final String minimumButtonString = "Min";
    private final String notANumberButtonString = "NaN";
    private final String changeSignButtonString = "*=(-1)";
    private final String doubleButtonString = "*=(2)";
    private final String halveButtonString = "/=(2)";
    private final String notANumberString = "Not A Number";
    private final String positiveInfinityString = "+Infinity";
    private final String negativeInfinityString = "-Infinity";

    GrayButton maximumButton = new GrayButton(maximumButtonString);
    GrayButton minimumButton = new GrayButton(minimumButtonString);
    GrayButton positiveInfinityButton = new GrayButton(positiveInfinityButtonString);
    GrayButton negativeInfinityButton = new GrayButton(negativeInfinityButtonString);
    GrayButton piButton = new GrayButton(piButtonString);
    GrayButton notANumberButton = new GrayButton(notANumberButtonString);

    public void init() {

        Panel buttonPanel = new PanelWithInsets(0, 0, 0, 0);
        buttonPanel.setLayout(new GridLayout(6, 2, 5, 5));
        buttonPanel.add(maximumButton);
        buttonPanel.add(minimumButton);
        buttonPanel.add(positiveInfinityButton);
        buttonPanel.add(negativeInfinityButton);
        buttonPanel.add(piButton);
        buttonPanel.add(notANumberButton);
        buttonPanel.add(new GrayButton(multByZeroButtonString));
        buttonPanel.add(new GrayButton(changeSignButtonString));
        buttonPanel.add(new GrayButton(doubleButtonString));
        buttonPanel.add(new GrayButton(halveButtonString));
        buttonPanel.add(new GrayButton(incrementButtonString));
        buttonPanel.add(new GrayButton(decrementButtonString));

        binaryField = new Label("00000000000000000000000000000000");
        signField = new Label("0");
        exponentField = new Label("00000000");
        mantissaField = new Label("000000000000000000000000");
        hexField = new Label("00000000");
        base2Field = new Label("0");
        base10Field = new Label("0");

        Font fieldFont = new Font("TimesRoman", Font.PLAIN, 12);
        binaryField.setFont(fieldFont);
        signField.setFont(fieldFont);
        exponentField.setFont(fieldFont);
        mantissaField.setFont(fieldFont);
        hexField.setFont(fieldFont);
        base2Field.setFont(fieldFont);
        base10Field.setFont(fieldFont);

        Panel numberPanel = new Panel();
        numberPanel.setBackground(Color.white);
        numberPanel.setLayout(new GridLayout(7, 1));
        numberPanel.add(signField);
        numberPanel.add(exponentField);
        numberPanel.add(mantissaField);
        Panel binaryPanel = new Panel();
        binaryPanel.setLayout(new BorderLayout());
        binaryPanel.add("Center", binaryField);
        numberPanel.add(binaryPanel);

        Panel hexPanel = new Panel();
        hexPanel.setLayout(new BorderLayout());
        hexPanel.add("Center", hexField);
        numberPanel.add(hexPanel);
        numberPanel.add(base2Field);
        numberPanel.add(base10Field);

        Panel labelPanel = new Panel();
        labelPanel.setBackground(Color.white);
        labelPanel.setLayout(new GridLayout(7, 1));
        Font labelFont = new Font("Helvetica", Font.ITALIC, 11);
        Label label = new Label(signString, Label.CENTER);
        label.setFont(labelFont);
        labelPanel.add(label);
        label = new Label(exponentString, Label.CENTER);
        label.setFont(labelFont);
        labelPanel.add(label);
        label = new Label(mantissaString, Label.CENTER);
        label.setFont(labelFont);
        labelPanel.add(label);
        label = new Label(binaryString, Label.CENTER);
        label.setFont(labelFont);
        labelPanel.add(label);
        label = new Label(hexString, Label.CENTER);
        label.setFont(labelFont);
        labelPanel.add(label);
        label = new Label(base2String, Label.CENTER);
        label.setFont(labelFont);
        labelPanel.add(label);
        label = new Label(base10String, Label.CENTER);
        label.setFont(labelFont);
        labelPanel.add(label);

        Panel dataPanel = new Panel();
        dataPanel.setLayout(new BorderLayout());
        dataPanel.add("West", labelPanel);
        dataPanel.add("Center", numberPanel);

        ColoredLabel title = new ColoredLabel(titleString, Label.CENTER, Color.cyan);
        title.setFont(new Font("Helvetica", Font.BOLD, 12));

        setBackground(Color.green);
        setLayout(new BorderLayout(5, 5));
        add("North", title);
        add("West", buttonPanel);
        add("Center", dataPanel);
    }

    public boolean action(Event evt, Object arg) {

        if (evt.target instanceof Button) {
            String bname = (String) arg;
            if (bname.equals(incrementButtonString)) {

                ++value;
            }
            else if (bname.equals(decrementButtonString)) {

                --value;
            }
            else if (bname.equals(multByZeroButtonString)) {

                value *= (float) 0.0;
            }
            else if (bname.equals(piButtonString)) {

                value = (float) Math.PI;
            }
            else if (bname.equals(positiveInfinityButtonString)) {

                value = Float.POSITIVE_INFINITY;
            }
            else if (bname.equals(negativeInfinityButtonString)) {

                value = Float.NEGATIVE_INFINITY;
            }
            else if (bname.equals(maximumButtonString)) {

                value = Float.MAX_VALUE;
            }
            else if (bname.equals(minimumButtonString)) {

                value = Float.MIN_VALUE;
            }
            else if (bname.equals(notANumberButtonString)) {

                value = Float.NaN;
            }
            else if (bname.equals(changeSignButtonString)) {

                value *= -1.0;
            }
            else if (bname.equals(doubleButtonString)) {

                value *= 2.0;
            }
            else if (bname.equals(halveButtonString)) {

                value /= 2.0;
            }
            updateNumberFields();
            enableDisableButton(maximumButton, Float.MAX_VALUE);
            enableDisableButton(minimumButton, Float.MIN_VALUE);
            enableDisableButton(positiveInfinityButton, Float.POSITIVE_INFINITY);
            enableDisableButton(negativeInfinityButton, Float.NEGATIVE_INFINITY);
            enableDisableButton(piButton, (float) Math.PI);
            enableDisableButton(notANumberButton, Float.NaN);
            if (!notANumberButton.isEnabled()) {
                if (!Float.isNaN(value)) {
                    notANumberButton.enable();
                }
            } else if (Float.isNaN(value)) {
                notANumberButton.disable();
            }
        }
        return true;
    }

    void enableDisableButton(Button b, float val) {

        if (!b.isEnabled()) {
            if (value != val) {
                b.enable();
            }
        } else if (value == val) {
            b.disable();
        }
    }

    void updateNumberFields() {

        int intBits = Float.floatToIntBits(value);

        if (Float.isNaN(value)) {
            base10Field.setText(notANumberString);
        }
        else if (Float.isInfinite(value)) {
            if ((intBits >>> 31) == 1) {
                // This is a negative infinity
                base10Field.setText(negativeInfinityString);
            }
            else {
                // This is a positive infinity
                base10Field.setText(positiveInfinityString);
            }
        }
        else if (intBits == (int) 0x80000000) {
            base10Field.setText("-0");
        }
        else {
            base10Field.setText(Float.toString(value));
        }

        int v = intBits;
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

        v = intBits;
        buf.setLength(0);
        for (int i = 0; i < 32; ++i) {
            // Get lowest bit
            int remainder = v & 0x1;

            // Convert bit to a character and insert it into the beginning of the string
            if (remainder == 0) {
                buf.insert(0, "0");
            }
            else {
                buf.insert(0, "1");
            }

            // Shift the int to the right one bit
            v >>>= 1;
        }
        binaryField.setText(buf.toString());

        if (intBits < 0) {

            signField.setText("1");
        }
        else {

            signField.setText("0");
        }

        v = intBits >> 23;
        buf.setLength(0);
        for (int i = 0; i < 8; ++i) {
            // Get lowest bit
            int remainder = v & 0x1;

            // Convert bit to a character and insert it into the beginning of the string
            if (remainder == 0) {
                buf.insert(0, "0");
            }
            else {
                buf.insert(0, "1");
            }

            // Shift the int to the right one bit
            v >>>= 1;
        }
        exponentField.setText(buf.toString());

        // Do the mantissa
        v = intBits;
        buf.setLength(0);
        for (int i = 0; i < 23; ++i) {
            // Get lowest bit
            int remainder = v & 0x1;

            // Convert bit to a character and insert it into the beginning of the string
            if (remainder == 0) {
                buf.insert(0, "0");
            }
            else {
                buf.insert(0, "1");
            }

            // Shift the int to the right one bit
            v >>>= 1;
        }
        if (((intBits >> 23) & 0xff) == 0) {
            // This is a denormalized number, first bit is 0
            buf.insert(0, "0");
        }
        else {
            // This is a normalized number, first bit is 1
            buf.insert(0, "1");
        }
        mantissaField.setText(buf.toString());

        // Print out a denormalized base 2 version.
        buf.setLength(0);
        if (Float.isNaN(value)) {
            buf.append(notANumberString);
        }
        else if (Float.isInfinite(value)) {
            if ((intBits >>> 31) == 1) {
                // This is a negative infinity
                buf.append(negativeInfinityString);
            }
            else {
                // This is a positive infinity
                buf.append(positiveInfinityString);
            }
        }
        else {

            if ((intBits >>> 31) == 1) {
                // This is a negative number
                buf.append("-");
            }

            // Convert mantissa to int.
            v = (intBits & 0x007fffff);
            if (((intBits >> 23) & 0xff) != 0) {
                // Set bit 23 if the number is normalized
                v |= 0x00800000;
            }
            buf.append(v);

            // print out the exponent
            // 151 equals 127 (the bias) plus 24 (the power of two by
            // which I multiply the base two mantissa to get an integral
            // number that I can print out in base ten)
            v = (intBits >> 23) & 0xff;
            if (v != 151 && intBits != 0 && intBits != (int) 0x80000000) {
                if (v != 0) {
                    // regular normalized number
                    buf.append("e" + (v - 151));
                }
                else {
                    // denormalized number (-150 equals -126, the power of two
                    // for denormalized floats, plus -24, the adjustment I
                    // must make to the exponent for multiplying the mantissa
                    // by two to the 24th power.
                    buf.append("e-150");
                }
            }
        }

        base2Field.setText(buf.toString());
    }

    public Insets insets() {
        return new Insets(5, 5, 5, 5);
    }
}