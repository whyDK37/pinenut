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
* This class encapsulates some utility methods that
* print a hex value filled with leading zeros.
*
* @author  Bill Venners
*/
class HexString {

    private final String hexChar = "0123456789abcdef";
    private StringBuffer buf = new StringBuffer();

    void Convert(int val, int maxNibblesToConvert) {

        buf.setLength(0);

        int v = val;
        for (int i = 0; i < maxNibblesToConvert; ++i) {

            if (v == 0) {

                if (i == 0) {
                    buf.insert(0, '0');
                }
                break;
            }

            // Get lowest nibble
            int remainder = v & 0xf;

            // Convert nibble to a character and insert it into the beginning of the string
            buf.insert(0, hexChar.charAt(remainder));

            // Shift the int to the right four bits
            v >>>= 4;
        }
    }

    HexString(int val, int minWidth) {

        Convert(val, minWidth);

        int charsNeeded = minWidth - buf.length();
        for (int i = 0; i < charsNeeded; ++i) {
            buf.insert(0, '0');
        }
    }

    public String getString() {

        return buf.toString();
    }
}

