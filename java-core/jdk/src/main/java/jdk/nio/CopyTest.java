package jdk.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 通道之间的数据传输
 * 在Java NIO中，如果两个通道中有一个是FileChannel，
 * 那你可以直接将数据从一个channel（译者注：channel中文常译作通道）传输到另外一个channel。
 * Created by drug on 2016/3/29.
 */
public class CopyTest {
  public static void main(String[] args) throws Exception {
    String filename = "C:\\Windows\\System32\\drivers\\etc\\hosts";
    RandomAccessFile fromFile = new RandomAccessFile(filename, "r");
    FileChannel fromChannel = fromFile.getChannel();

    RandomAccessFile toFile = new RandomAccessFile("d:\\hosts", "rw");
    FileChannel toChannel = toFile.getChannel();

    long position = 0;
    long count = fromChannel.size();

//        FileChannel的transferFrom()方法可以将数据从源通道传输到FileChannel中
//        toChannel.transferFrom(fromChannel, position, count);
//        transferTo()方法将数据从FileChannel传输到其他的channel中。下面是一个简单的例子：
    fromChannel.transferTo(position, count, toChannel);
  }
}
