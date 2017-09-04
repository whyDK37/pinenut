package jdk.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by drug on 2016/3/29.
 */
public class ServerSocketChannelTest {
  public static void main(String[] args) throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.socket().bind(new InetSocketAddress(1997));
    while (true) {
      SocketChannel socketChannel = serverSocketChannel.accept();
      if (socketChannel != null) {
        System.out.println("client:" + socketChannel.getRemoteAddress().toString());
        String newData = "New String to write to file..." + System.currentTimeMillis();
        ByteBuffer src = ByteBuffer.allocate(48);
        src.clear();
        src.put(newData.getBytes());
        src.flip();

        socketChannel.write(src);
        //逐个字节输出
//                while (buf.hasRemaining()) {
//                    System.out.print((char) buf.get()); // read 1 byte at a time
//                }
        //do something with socketChannel...
      }
      //do something with socketChannel...
    }
  }
}
