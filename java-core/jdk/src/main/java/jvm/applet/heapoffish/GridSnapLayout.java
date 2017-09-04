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
package jvm.applet.heapoffish;

import java.awt.*;

/**
 * GridSnapLayout lays out components in a grid that can have columns of
 * varying width. This is not a very general purpose layout manager. It
 * solves the specific problem of getting all the information I want to display about
 * the stack and method areas in a nice grid. Because some columns of info need
 * more room than others, and space is limited on a web page, I needed to be
 * able to specify varying widths of columns in a grid.
 *
 * @author Bill Venners
 */
class GridSnapLayout implements LayoutManager {

  // rows and cols are the number of rows and columns of the grid upon
  // which the components are placed. Components are always one row
  // in height, but may be more than one column in width. The number
  // of columns width of each component is stored in hComponentCellWidths.
  // The array length of hComponentCellWidths indicates the number of
  // components that will appear on each row.
  private int rows;
  private int cols;
  private int[] hComponentCellWidths;

  public GridSnapLayout(int rows, int cols, int[] hComponentCellWidths) {

    this.rows = rows;
    this.cols = cols;
    this.hComponentCellWidths = hComponentCellWidths;
  }

  public void addLayoutComponent(String name, Component comp) {
  }

  public void removeLayoutComponent(Component comp) {
  }

  // Calculate preferred size as if each component were taking an equal
  // share of the width of a row.
  public Dimension preferredLayoutSize(Container parent) {

    int rowCount = rows;
    int colCount = cols;
    Insets parentInsets = parent.insets();
    int componentCount = parent.countComponents();

    if (rowCount > 0) {
      colCount = (componentCount + rowCount - 1) / rowCount;
    } else {
      rowCount = (componentCount + colCount - 1) / colCount;
    }

    // Find the maximum preferred width and the maximum preferred height
    // of any component.
    int w = 0;
    int h = 0;
    for (int i = 0; i < componentCount; i++) {

      Component comp = parent.getComponent(i);
      Dimension d = comp.preferredSize();
      if (w < d.width) {
        w = d.width;
      }
      if (h < d.height) {
        h = d.height;
      }
    }

    // Return the maximum preferred component width and height times the number
    // of columns and rows, respectively, plus any insets in the parent.
    return new Dimension(parentInsets.left + parentInsets.right + colCount * w,
            parentInsets.top + parentInsets.bottom + rowCount * h);
  }

  // Calculate minimum size as if each component were taking an equal
  // share of the width of a row.
  public Dimension minimumLayoutSize(Container parent) {

    Insets parentInsets = parent.insets();
    int componentCount = parent.countComponents();
    int rowCount = rows;
    int colCount = cols;

    if (rowCount > 0) {
      colCount = (componentCount + rowCount - 1) / rowCount;
    } else {
      rowCount = (componentCount + colCount - 1) / colCount;
    }

    // Find the maximum "minimum width" and the maximum "minimum height"
    // of any component.
    int w = 0;
    int h = 0;
    for (int i = 0; i < componentCount; i++) {

      Component comp = parent.getComponent(i);
      Dimension d = comp.minimumSize();
      if (w < d.width) {
        w = d.width;
      }
      if (h < d.height) {
        h = d.height;
      }
    }

    // Return the maximum "minimum component width and height" times the number
    // of columns and rows, respectively, plus any insets in the parent.
    return new Dimension(parentInsets.left + parentInsets.right + colCount * w,
            parentInsets.top + parentInsets.bottom + rowCount * h);
  }

  // Layout the container such that the widths of columns correspond
  // to the number of columns in that components hComponentCellWidth
  // array element. For example, if the
  public void layoutContainer(Container parent) {

    int rowCount = rows;
    int colCount = hComponentCellWidths.length;
    Insets parentInsets = parent.insets();
    int componentCount = parent.countComponents();

    if (componentCount == 0) {
      return;
    }

    // Calculate the width and height of each grid cell. The height will
    // be the height of each component, but the width may not. The width
    // of a component will be some multiple of a grid cell width. The
    // number of grid cells for each component is defined by the
    // hComponentCellWidths array. w is width of each grid cell. h is
    // height of each grid cell.
    Dimension parentDim = parent.size();
    int w = parentDim.width - (parentInsets.left + parentInsets.right);
    int h = parentDim.height - (parentInsets.top + parentInsets.bottom);
    w /= cols;
    h /= rowCount;

    // For each row and column of components (not grid cells) position
    // the component.
    for (int c = 0, x = parentInsets.left; c < colCount; c++) {
      for (int r = 0, y = parentInsets.top; r < rowCount; r++) {

        int i = r * colCount + c;
        if (i < componentCount) {
          parent.getComponent(i).reshape(x, y, w * hComponentCellWidths[c], h);
        }
        y += h;
      }
      x += (w * hComponentCellWidths[c]);
    }
  }
}

