package jdk.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by drug on 2016/3/29.
 */
public class SelectorTest {
    public static void main(String[] args)throws Exception {
//        String filename = "C:\\Windows\\System32\\drivers\\etc\\hosts";
//        RandomAccessFile fromFile = new RandomAccessFile(filename, "r");
//        FileChannel channel = fromFile.getChannel();
        ServerSocketChannel channel =  ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(1997));

        Selector selector = Selector.open();
        channel.configureBlocking(false);
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);
        while(true) {
            int readyChannels = selector.select();
            if(readyChannels == 0) continue;
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
            while(keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()) {
                    // a connection was accepted by a ServerSocketChannel.
                } else if (key.isConnectable()) {
                    // a connection was established with a remote server.
                } else if (key.isReadable()) {
                    // a channel is ready for reading
                } else if (key.isWritable()) {
                    // a channel is ready for writing
                }
                keyIterator.remove();
            }
        }
    }
}
