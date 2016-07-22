package jdk.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
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

//        toChannel.transferFrom(fromChannel, position, count);
        fromChannel.transferTo(position,count,toChannel);
    }
}
