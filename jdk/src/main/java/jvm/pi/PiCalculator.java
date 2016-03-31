package jvm.pi;/*
* Copyright (c) 1997-1999 Bill Venners. All rights reserved.
*
* Source code file from the book "Inside the Java 2 Virtual Machine,"
* by Bill Venners, published by McGraw-Hill, 1999, ISBN: 0-07-135093-4. 
*
* This source file may not be copied, modified, or redistributed
* EXCEPT as allowed by the following statements: You may freely use
* this file for your own work, including modifications and distribution
* in compiled (class files, native executable, etc.) form only. You may
* not copy and distribute this file. You may not remove this copyright
* notice. You may not distribute modified versions of this source file.
* You may not use this file in printed media without the express
* permission of Bill Venners. 
*
* BILL VENNERS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
* SUITABILITY OF THIS SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING
* BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS
* FOR PARTICULAR PURPOSE, OR NON-INFRINGEMENT. BILL VENNERS SHALL NOT
* BE LIABLE FOR ANY DAMAGES SUFFERED BY A LICENSEE AS A RESULT OF
* USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/

// On CD-ROM in file pi/ex1/PiCalculator.java
class PiCalculator {

    static void calculatePi() {

        double pi = 4.0;
        double sliceWidth = 0.5;
        double y;

        int iterations = 1;

        for (;;) {

            double x = 0.0;
            while (x < 1.0) {

                y = Math.sqrt(1 - (x * x));
                pi -= 4 * (sliceWidth * y);
                x += sliceWidth;

                y = Math.sqrt(1 - (x * x));
                pi += 4 * (sliceWidth * y);
                x += sliceWidth;
            }

            ++iterations;

            sliceWidth /= 2;
        }
    }
}
