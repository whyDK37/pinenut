package net.reactor.v1;

import java.io.IOException;

/**
 * https://blog.csdn.net/weililansehudiefei/article/details/70889424
 */
public class Server {


    public static void main(String[] args) {
        try {
            TCPReactor reactor = new TCPReactor(1333);
            reactor.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}  