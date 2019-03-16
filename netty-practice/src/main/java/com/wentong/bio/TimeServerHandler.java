package com.wentong.bio;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;

public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream(); OutputStream outputStream = socket.getOutputStream()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            // 为什么要做成死循环的样子
            while (true) {
                String body = bufferedReader.readLine();
                if (StringUtils.isNotBlank(body)) {
                    System.out.println("The time server receive order: " + body);
                    String currentTime = Objects.equals(body, "QUERY_TIME_ORDER") ? new Date(System.currentTimeMillis()).toString() : "BAD_REQUEST";
                    printWriter.println(currentTime);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
