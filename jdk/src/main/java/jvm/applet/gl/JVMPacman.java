package jvm.applet.gl;/*
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
* This class is a canvas upon which a JVM is shown consuming a stream
* of bytes that makes up a particular Java class file.
*
* @author  Bill Venners
*/
class JVMPacman extends Canvas {

    private String theString;
    private boolean stringValid = false;
    private int currentGobblePosition;
    private int interestingCharsCount;
    private int charsThatFitBetweenRectanglesCount = 2;

    JVMPacman() {
        setBackground(Color.cyan);
    }

    void setText(String passedText) {

        theString = passedText;
        stringValid = true;
    }

    public Dimension minimumSize() {
        return new Dimension(110, 60);
    }
    public Dimension preferredSize() {
        return new Dimension(110, 60);
    }

    public void setGobblePosition(int pos, int interesting) {
        // Multiply the passed position by two because the passed position represents
        // a byte position whereas we want currentGobblePosition to represent a
        // character position, and there are two hex characters for each byte.
        currentGobblePosition = pos * 2;
        interestingCharsCount = interesting * 2;
        repaint();
    }

    public void gobbleToPosition(int pos, int interesting) {
        currentGobblePosition = pos * 2;
        interestingCharsCount = interesting * 2;
        repaint();
    }

    public void paint(Graphics g) {

        Font font = getFont();
        FontMetrics fm = getFontMetrics(font);
        int heightOfOneLine = fm.getHeight();

        // Calculate x starting point
        Dimension dim = new Dimension();

        dim = size();

        int xStartingPoint = 5;

        // Calculate y starting point
        int totalHeight = heightOfOneLine * 2;
        int yStartingPoint = (dim.height - totalHeight) / 2;
        if (yStartingPoint < 5) {
            yStartingPoint = 5;
        }

        // Calculate width of JVM rectangle. This will be heightOfOneLine more than
        // the stringWidth of "JVM" which I'll write in the middle of the rectangle.
        // This will make the border around the "JVM" the same width and height on
        // all sides and will equal heightOfOneLine / 2. I'll make the height of the
        // rectangle heightOfOneLine *2.
        int jvmRectangleWidth = fm.stringWidth("JVM") + heightOfOneLine;

        // Draw the filled rectangle
        g.setColor(Color.green);
        g.fillRoundRect(xStartingPoint, yStartingPoint, jvmRectangleWidth, totalHeight,
            5, 5);

        // Give it a handsome black outline
        g.setColor(Color.black);
        g.drawRoundRect(xStartingPoint, yStartingPoint, jvmRectangleWidth - 1, totalHeight - 1,
            5, 5);

        // Calculate width of Server rectangle. This will be heightOfOneLine more than
        // the stringWidth of "Server" which I'll write in the middle of the rectangle.
        // This will make the border around the "Server" the same width and height on
        // all sides and will equal heightOfOneLine / 2. I'll make the height of the
        // rectangle heightOfOneLine *2.
        int serverRectangleWidth = fm.stringWidth("Server") + heightOfOneLine;

        // Draw the filled rectangle. The x starting point is the width of the
        // canvas minus the width of the server rectangle minus the 5 pixel margin.
        int xStartingPointServerRect = dim.width - serverRectangleWidth - 5;
        g.setColor(Color.green);
        g.fillRoundRect(xStartingPointServerRect, yStartingPoint,
            serverRectangleWidth, totalHeight, 5, 5);

        // Give this rectangle a handsome black outline
        g.setColor(Color.black);
        g.drawRoundRect(xStartingPointServerRect, yStartingPoint,
            serverRectangleWidth - 1, totalHeight - 1, 5, 5);

        int whiteRectangleWidth = xStartingPointServerRect - jvmRectangleWidth - 5;
        if (whiteRectangleWidth > 0) {

            g.setColor(Color.white);
            g.fillRect(jvmRectangleWidth + 5, yStartingPoint + (heightOfOneLine / 2),
                whiteRectangleWidth, heightOfOneLine);
        }

        // Draw "JVM" inside the rectangle
        g.setColor(Color.black);
        xStartingPoint += (heightOfOneLine / 2);
        int ascent = fm.getAscent();
        yStartingPoint += ascent + (heightOfOneLine / 2);
        g.drawString("JVM", xStartingPoint, yStartingPoint);

        // Draw "Server" inside the rectangle
        int xStartingPointServerText = xStartingPointServerRect + (heightOfOneLine / 2);
        g.drawString("Server", xStartingPointServerText, yStartingPoint);

        // The string should be written so that it fits between the JVM and Server
        // rectangles, leaving at least 5 pixels space between the rectangle and
        // the string.
        if (stringValid && currentGobblePosition < theString.length()) {

            // First need to figure out how many characters will fit in
            // the space between the two rectangles.
            int xTextStartingPoint = jvmRectangleWidth + 10;
            int xTextEndingPoint = xStartingPointServerRect - 5;
            int pixelsAvailableBetweenRectangles = xTextEndingPoint - xTextStartingPoint;
            if (pixelsAvailableBetweenRectangles < 0) {
                pixelsAvailableBetweenRectangles = 0;
            }

            // Initialize the number of characters to write as the number of
            // remaining characters. This will be reduced below innerfloat this amount of
            // characters doesn't fit.
            int charsToWriteCount = theString.length() - currentGobblePosition;

            // Check to see innerfloat the string to be displayed already fits between the
            // two rectangles. If so, we'll just use the total number of characters
            // remaining as the number of characters to write.
            int pixelWidthOfRemainingString = fm.stringWidth(theString.substring(currentGobblePosition));
            if (pixelWidthOfRemainingString > pixelsAvailableBetweenRectangles) {

                // The first while loop increments the charsThatFitBetweenTwoRectanglesCount
                // until the width of the string in pixels just exceeds the available space.
                String tryThisString = theString.substring(currentGobblePosition,
                    currentGobblePosition + charsThatFitBetweenRectanglesCount);
                int pixelsEaten = fm.stringWidth(tryThisString);
                while (pixelsEaten <= pixelsAvailableBetweenRectangles) {
                    ++charsThatFitBetweenRectanglesCount;
                    tryThisString = theString.substring(currentGobblePosition,
                        currentGobblePosition + charsThatFitBetweenRectanglesCount);
                    pixelsEaten = fm.stringWidth(tryThisString);
                }

                // The second while loop decreases the charsThatFit variable until the
                // width of the string in pixels is just under the available width.
                while (pixelsEaten > pixelsAvailableBetweenRectangles) {
                    --charsThatFitBetweenRectanglesCount;
                    tryThisString = theString.substring(currentGobblePosition,
                        currentGobblePosition + charsThatFitBetweenRectanglesCount);
                    pixelsEaten = fm.stringWidth(tryThisString);
                }

                charsToWriteCount = charsThatFitBetweenRectanglesCount;
            }

            // Draw the interesting characters in red.
            g.setColor(Color.red);
            int redCharsCount = interestingCharsCount;
            if (redCharsCount > charsToWriteCount) {
                redCharsCount = charsToWriteCount;
            }
            String redString = theString.substring(currentGobblePosition,
                currentGobblePosition + redCharsCount);
            g.drawString(redString, xTextStartingPoint, yStartingPoint);

            // Draw the remaining characters in black.
            int blackStringStartingPosition = currentGobblePosition + redCharsCount;
            int blackCharsCount = charsToWriteCount - redCharsCount;
            if (blackStringStartingPosition < theString.length()
                && blackCharsCount > 0) {

                xTextStartingPoint += fm.stringWidth(redString);
                g.setColor(Color.black);
                g.drawString(theString.substring(blackStringStartingPosition,
                    blackStringStartingPosition + blackCharsCount),
                    xTextStartingPoint, yStartingPoint);
            }
        }
    }
}
