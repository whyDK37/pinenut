package net.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class Reactor implements Runnable {

    private ServerSocketChannel serverSocketChannel = null;

    private Selector selector = null;

    public Reactor() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(8888));
            SelectionKey selectionKey = serverSocketChannel.register(selector,
                    SelectionKey.OP_ACCEPT);
            selectionKey.attach(new Acceptor());
            System.out.println("服务器启动正常!");
        } catch (IOException e) {
            System.out.println("启动服务器时出现异常!");
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                selector.select();

                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey selectionKey = iter.next();
                    dispatch((Runnable) selectionKey.attachment());
                    iter.remove();// 只处理当前客户端的请求
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void dispatch(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    public static void main(String[] args) {
        new Thread(new Reactor()).start();
    }

    class Acceptor implements Runnable {
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    System.out.println("接收到来自客户端（"
                            + socketChannel.socket().getInetAddress().getHostAddress()
                            + "）的连接");
                    new Handler(selector, socketChannel);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Handler implements Runnable {

    private static final int READ_STATUS = 1;

    private static final int WRITE_STATUS = 2;

    private SocketChannel socketChannel;

    private SelectionKey selectionKey;

    private int status = READ_STATUS;

    public Handler(Selector selector, SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
        try {
            socketChannel.configureBlocking(false);
            selectionKey = socketChannel.register(selector, 0);
            selectionKey.interestOps(SelectionKey.OP_READ);
            selectionKey.attach(this);
            selector.wakeup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean bye = false;

    public void run() {
        try {
            if (status == READ_STATUS) {
                String read = read();

                if ("q".equalsIgnoreCase(read)) bye = true;

                selectionKey.interestOps(SelectionKey.OP_WRITE);
                status = WRITE_STATUS;
            } else if (status == WRITE_STATUS) {
                if (bye) {
                    process("bye.\n");
                    System.out.println("服务器关闭连接");
                    selectionKey.cancel();
                } else {
                    process("Hello World!\n");
                    System.out.println("服务器发送消息成功!");
                    status = READ_STATUS;
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            selectionKey.cancel();
        }
    }

    public String read() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Charset charset = Charset.forName("utf-8");
        socketChannel.read(buffer);
        String echo = charset.decode(buffer).toString();
        System.out.println("接收到来自客户端（" + socketChannel.socket().getInetAddress().getHostAddress() + "）的消息：" + echo);
        return echo;
    }

    public void process(String content) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(content.getBytes());
        socketChannel.write(buffer);
    }
}  