package jdk.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by drug on 2016/3/29.
 */
public class SocketChannelTest {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(1987));

        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = socketChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip(); // make buffer ready for read

            //逐个字节输出
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // read 1 byte at a time
            }
//            buf.rewind();
//            buf.compact().mark().reset();
            buf.clear(); // make buffer ready for writing
//            bytesRead = socketChannel.read(buf);
            bytesRead = -1;
        }
        socketChannel.close();
    }
}
