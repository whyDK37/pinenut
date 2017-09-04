package jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by why on 3/5/2017.
 */
public class DatagramChannelTest {
  public static void main(String[] args) throws IOException {
    DatagramChannel channel = DatagramChannel.open();
    channel.socket().bind(new InetSocketAddress(9999));


//        接收数据
    ByteBuffer buf = ByteBuffer.allocate(48);
    buf.clear();
    channel.receive(buf);

//        发送数据
    String newData = "New String to write to file..." + System.currentTimeMillis();

    buf = ByteBuffer.allocate(48);
    buf.clear();
    buf.put(newData.getBytes());
    buf.flip();

    int bytesSent = channel.send(buf, new InetSocketAddress(9999));

  }
}
