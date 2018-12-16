package net.reactor.v1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class TCPHandler implements Runnable {

    private final SelectionKey sk;
    private final SocketChannel sc;
    static final int READING = 0, SENDING = 1;
    volatile int state = READING;

    public TCPHandler(SelectionKey sk, SocketChannel sc) {
        this.sk = sk;
        this.sc = sc;
        state = 0; // 初始狀態設定為READING  
    }

    @Override
    public void run() {
        try {
            if (state == READING) {
                read(); // 讀取網絡數據
            } else if (state == SENDING) {
                send(); // 發送網絡數據
            }

        } catch (IOException e) {
            System.out.println("[Warning!] A client has been closed.");
            closeChannel();
        }
    }

    private void closeChannel() {
        try {
            sk.cancel();
            sc.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private synchronized void read() throws IOException {
        // non-blocking下不可用Readers，因為Readers不支援non-blocking  
        byte[] arr = new byte[1024];
        ByteBuffer buf = ByteBuffer.wrap(arr);

        int numBytes = sc.read(buf); // 讀取字符串  
        if (numBytes == -1) {
            System.out.println("[Warning!] A client has been closed.");
            closeChannel();
            return;
        }
        String str = new String(arr); // 將讀取到的byte內容轉為字符串型態  
        if (!str.startsWith("exit")) {
            process(str); // 邏輯處理  
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + " > " + str);
            state = SENDING; // 改變狀態
            sk.interestOps(SelectionKey.OP_WRITE); // 通過key改變通道註冊的事件  
            sk.selector().wakeup(); // 使一個阻塞住的selector操作立即返回  
        } else {
            System.out.println("[Warning!] A client has been closed.");
            closeChannel();
        }
    }

    private void send() throws IOException {
        // get message from message queue  

        String str = "Your message has sent to " + sc.socket().getLocalSocketAddress().toString() + "\r\n";
        ByteBuffer buf = ByteBuffer.wrap(str.getBytes()); // wrap自動把buf的position設為0，所以不需要再flip()  

        while (buf.hasRemaining()) {
            sc.write(buf); // 回傳給client回應字符串，發送buf的position位置 到limit位置為止之間的內容  
        }

        state = READING; // 改變狀態
        sk.interestOps(SelectionKey.OP_READ); // 通過key改變通道註冊的事件  
        sk.selector().wakeup(); // 使一個阻塞住的selector操作立即返回  
    }

    void process(String str) {
        // do process(decode, logically process, encode)..  
        // ..  
    }
}  