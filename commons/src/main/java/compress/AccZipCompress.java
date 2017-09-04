package compress;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

/**
 * acc-apache commons compress
 * Created by hOward on 2015/8/22.
 */
public class AccZipCompress implements CompressFile {

  public void compress(String compressPath, String[] sourcePath) throws CompressFileException {
    ZipArchiveOutputStream zipOutput = null;
    try {
      File zipFile = new File(compressPath);
      zipOutput = (ZipArchiveOutputStream) new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, new FileOutputStream(zipFile));
      zipOutput.setEncoding("UTF-8");
      zipOutput.setUseZip64(Zip64Mode.AsNeeded);
      for (String filepath : sourcePath) {
        File file = new File(filepath);
        InputStream in = null;
        try {
          in = new FileInputStream(file);
          ZipArchiveEntry entry = new ZipArchiveEntry(file, file.getName());
          //zipOutput.createArchiveEntry(logFile, logFile.getName());
          zipOutput.putArchiveEntry(entry);
          IOUtils.copy(in, zipOutput);
          zipOutput.closeArchiveEntry();
        } finally {
          if (in != null) {
            try {
              in.close();
            } catch (Exception e) {
            }
          }
        }
      }

    } catch (Exception e) {
      if (zipOutput != null) {
        try {
          zipOutput.close();
        } catch (Exception e1) {
        }
      }
      throw new CompressFileException(e);
    } finally {
      try {
        if (zipOutput != null) {
          zipOutput.finish();
          zipOutput.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
