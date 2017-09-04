package jdk.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by drug on 2016/3/29.
 */
public class BufferTest {
  public static void main(String[] args) throws Exception {
    String filename = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    RandomAccessFile aFile = new RandomAccessFile(filename, "r");
    FileChannel inChannel = aFile.getChannel();
    // create buffer with capacity of 48 bytes
    ByteBuffer buf = ByteBuffer.allocate(48);
    ByteBuffer.allocateDirect(48);
    int bytesRead = inChannel.read(buf); // read into buffer.
    while (bytesRead != -1) {
      buf.flip(); // make buffer ready for read

      //逐个字节输出
      while (buf.hasRemaining()) {
        System.out.print((char) buf.get()); // read 1 byte at a time
      }
      buf.rewind();
      buf.compact().mark().reset();
      buf.clear(); // make buffer ready for writing
      bytesRead = inChannel.read(buf);
    }
    aFile.close();
  }
}
