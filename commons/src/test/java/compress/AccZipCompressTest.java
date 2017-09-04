package compress;

import org.junit.Test;

import java.io.File;

/**
 * Created by hOward on 2015/8/22.
 */
public class AccZipCompressTest {

  @Test
  public void testcompress() throws Exception {
    AccZipCompress accZipCompress = new AccZipCompress();

    String[] path = new String[3];
    Class<AccZipCompressTest> clazz = AccZipCompressTest.class;
    path[0] = new File(clazz.getResource("中文.txt").toURI()).getAbsolutePath();
    path[1] = clazz.getResource("test2.xml").getFile();
    path[2] = clazz.getResource("test1.txt").getFile();

    File zipFile = new File(path[1]);
    zipFile = new File(zipFile.getParent(), "accZipCompress.zip");
    System.out.println("zip file : " + zipFile.getAbsolutePath());
    try {
      accZipCompress.compress(zipFile.getAbsolutePath(), path);
    } catch (CompressFileException e) {
      throw e;
    }
  }
}
