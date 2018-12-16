package net.reactor.v1;

import java.io.IOException;

/**
 * https://blog.csdn.net/weililansehudiefei/article/details/70889424
 */
public class Server {


    public static void main(String[] args) throws IOException {
        Thread reactor = new Thread(new TCPReactor(1333));
        reactor.setDaemon(false);
        reactor.start();
    }

}  