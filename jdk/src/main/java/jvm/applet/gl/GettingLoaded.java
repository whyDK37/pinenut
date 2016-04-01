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
import java.applet.*;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

/**
* This applet describes the contents of a particular a Java class
* file as it is loaded, one chunk of bytes at a time, by the JVM.
*
* @author  Bill Venners
*/
public class GettingLoaded extends Applet
    implements Runnable {

    private URL theClassFileURL;
    private URL theActTextURL;
    private Thread runner;
    private TextArea ta = new TextArea();
    private StepNode firstNode;
    private StepNode lastNode;
    private StepNode currentNode;
    private boolean ready = false;
    private boolean jvmFinishedGobbling = false;
    private int currentGobblePosition = 0;
    private JVMPacman jvmPacman;
    private String titleString = "Getting Loaded\n\n";
    private boolean urlExceptionWasThrown = false;
    private String cantGoFurtherString = "Unfortunately this means the applet cannot go any further.\n";
    private String ioErrorMsg = "An IO Error occured while trying to read a file from the server.\n";
    private String securityErrorMsg = "An security exception occured while trying to read a file from the server.\n";
    private String urlErrorMsg = "This HTML file contains a malformed URL of a file required by this applet.\n";

    public void init() {

        super.init();

        ta.setEditable(false);

        setBackground(Color.blue);

        String url = getParameter("classURL");
        try {
            this.theClassFileURL = new URL(getDocumentBase(), url);
        }
        catch (MalformedURLException e) {
            urlExceptionWasThrown = true;
            URL docBase = getDocumentBase();
            ta.setText(titleString + "Bad URL: " + docBase.toString() + url + "\n\n" + urlErrorMsg + cantGoFurtherString);
        }
        url = getParameter("textURL");
        try {
            this.theActTextURL = new URL(getDocumentBase(), url);
        }
        catch (MalformedURLException e) {
            urlExceptionWasThrown = true;
            URL docBase = getDocumentBase();
            ta.setText(titleString + "Bad URL: " + docBase.toString() + url + "\n\n" + urlErrorMsg + cantGoFurtherString);
        }
        ControlPanel controlPanel = new ControlPanel();
        jvmPacman = controlPanel.getJVMPacman();
        setLayout(new BorderLayout(5, 5));

        ta.setBackground(Color.white);

        add("North", new ColoredLabel("GETTING LOADED", Label.CENTER, Color.cyan));
        add("South", controlPanel);
        add("Center", ta);
    }

    public boolean handleEvent(Event event) {
        return super.handleEvent(event);
    }

    public boolean action(Event evt, Object arg) {
        if (evt.target instanceof Button) {
            String bname = (String) arg;
            if (bname.equals("Reset")) {
                if (ready) {
                    if (!currentNode.first()) {
                        currentNode = firstNode;
                        currentGobblePosition = 0;
                        jvmPacman.setGobblePosition(0, currentNode.getByteCount());
                        ta.setText(currentNode.getString());
                    }
                }
            }
            else if (bname.equals("Step")) {
                if (ready) {
                    if (!currentNode.last()) {
                        currentGobblePosition += currentNode.getByteCount();
                        currentNode = currentNode.getNext();
                        jvmPacman.gobbleToPosition(currentGobblePosition, currentNode.getByteCount());
                        ta.setText(currentNode.getString());
                    }
                    else {
                        if (!jvmFinishedGobbling) {
                            currentGobblePosition += currentNode.getByteCount();
                            jvmPacman.gobbleToPosition(currentGobblePosition, 0);
                            jvmFinishedGobbling = true;
                            ta.setText("(The End)");
                        }
                    }
                }
            }
            else if (bname.equals("Back")) {
                if (ready) {
                    if (!currentNode.first()) {
                        if (jvmFinishedGobbling) {
                            jvmFinishedGobbling = false;
                            currentGobblePosition -= currentNode.getByteCount();
                        }
                        else {
                            currentNode = currentNode.getPrev();
                            currentGobblePosition -= currentNode.getByteCount();
                        }
                        jvmPacman.setGobblePosition(currentGobblePosition, currentNode.getByteCount());
                        ta.setText(currentNode.getString());
                    }
                }
            }
        }
        return true;
    }

    public Insets insets() {
        return new Insets(5, 5, 5, 5);
    }

    public void start() {
        if (runner == null && !ready && !urlExceptionWasThrown) {
            runner = new Thread (this);
            runner.start();
        }
    }

    public void stop() {
        if (runner != null) {
            runner.stop();
            runner = null;
        }
    }

    public void run() {
        InputStream conn = null;
        DataInputStream data = null;
        String line;
        StringBuffer buf = new StringBuffer();

        ta.setText(titleString + "Loading First Of Two Files...\n");

        try {
            conn = this.theClassFileURL.openStream();
            data = new DataInputStream(new BufferedInputStream(conn));

            try {
                while (true) {
                    int unsignedByte = data.readUnsignedByte();
                    HexString hexStr = new HexString(unsignedByte, 2);
                    buf.append(hexStr.getString());
                }
            }
            catch (EOFException e) {
                jvmPacman.setText(buf.toString());
            }
            try {
                ta.setText(titleString + "Loading Second Of Two Files...\n");
                conn = this.theActTextURL.openStream();
                data = new DataInputStream(new BufferedInputStream(conn));
                buf.setLength(0);

                while ((line = data.readLine()) != null) {
                    if (line.length() > 0 && line.charAt(0) == '*') {
                        int starCount = line.length();
                        StepNode nextNode = new StepNode(buf.toString(), starCount);
                        if (firstNode == null) {
                            firstNode = nextNode;
                            lastNode = nextNode;
                        }
                        else {
                            lastNode.setNext(nextNode);
                            nextNode.setPrev(lastNode);
                            lastNode = nextNode;
                        }
                        buf.setLength(0);
                    }
                    else {
                        buf.append(line + "\n");
                    }
                }
                ready = true;
                currentNode = firstNode;
                jvmPacman.setGobblePosition(0, firstNode.getByteCount());
                ta.setText(currentNode.getString());
            }
            catch (IOException e) {
                ta.setText(titleString + "IO Error: " + e.getMessage() + "\n\n"
                    + ioErrorMsg + cantGoFurtherString);
            }
            catch (SecurityException e) {
                ta.setText(titleString + "Security Exception: " + e.getMessage() + "\n\n"
                    + securityErrorMsg + cantGoFurtherString);
            }
        }
        catch (IOException e) {
            ta.setText(titleString + "IO Error: " + e.getMessage() + "\n\n"
                    + ioErrorMsg + cantGoFurtherString);
        }
        catch (SecurityException e) {
            ta.setText(titleString + "Security Exception: " + e.getMessage() + "\n\n"
                + securityErrorMsg + cantGoFurtherString);
        }
        finally {
            runner = null;
        }
    }
}