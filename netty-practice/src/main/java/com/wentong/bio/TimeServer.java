package com.wentong.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 阻塞式的 bio server
 */
public class TimeServer {

    public static void main(String[] args) throws Exception {
        final int defaultPort = 8080;
        try (ServerSocket serverSocket = new ServerSocket(defaultPort)) {
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        }

    }
}
