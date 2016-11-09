package ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

/**
 * Created by whydk on 11/9/2016.
 */
public class Demo {
    public static void main(String[] args) throws IOException {
        String server = "120.26.231.118";
        int port =21;
        String username="hzftp";
        String password="HzFtp@O!6";

        boolean storeFile = false, binaryTransfer = false, error = false, listFiles = false, listNames = false, hidden = false;
        boolean localActive = false, useEpsvWithIPv4 = false, feat = false, printHash = false;
        boolean mlst = false, mlsd = false, mdtm = false, saveUnparseable = false;
        boolean lenient = false;

        FTPClient ftp = new FTPClient();
        ftp.connect(server, port);

        if(ftp.login(username, password)){
            System.out.println("login success.");
        }else{
            System.out.println("login failure");
        }


        System.out.println("Remote system is " + ftp.getSystemType());
        if (binaryTransfer) {
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
        } else {
            // in theory this should not be necessary as servers should default to ASCII
            // but they don't all do so - see NET-500
            ftp.setFileType(FTP.ASCII_FILE_TYPE);
        }


        // Use passive mode as default because most of us are
        // behind firewalls these days.
        if (localActive) {
            ftp.enterLocalActiveMode();
        } else {
            ftp.enterLocalPassiveMode();
        }

        ftp.setUseEPSVwithIPv4(useEpsvWithIPv4);

        FTPFile[] ftpFiles = ftp.listFiles("/");
        for (int i = 0; i < ftpFiles.length; i++) {
            System.out.println(ftpFiles[i].toString());
        }

        ftp.noop(); // check that control connection is working OK

        ftp.logout();
    }
}
