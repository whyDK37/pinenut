package pool;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.SoftReferenceObjectPool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by whydk on 2016/3/27.
 */
public class ReaderUtilTest {
  public static void main(String[] args) throws IOException {

    String file = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    ObjectPool<StringBuilder> objectPool = new SoftReferenceObjectPool(new StringBuilderFactory());
    ReaderUtil readerUtil = new ReaderUtil(objectPool);
    Reader reader = new FileReader(new File(file));
    readerUtil.readToString(new FileReader(new File(file)));
    readerUtil.readToString(new FileReader(new File(file)));
    readerUtil.readToString(new FileReader(new File(file)));
    readerUtil.readToString(new FileReader(new File(file)));
    readerUtil.readToString(new FileReader(new File(file)));
    readerUtil.readToString(new FileReader(new File(file)));
    String content = readerUtil.readToString(new FileReader(new File(file)));
    System.out.println(content);

  }
}
