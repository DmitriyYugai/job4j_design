package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class);

    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String firstStr = in.readLine();
                    System.out.println(firstStr);
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                    }
                    if (firstStr.contains("?")) {
                        String params = firstStr.split("/")[1];
                        int end = params.indexOf(" ");
                        params = params.substring(1, end);
                        String value = params.split("=")[1];
                        if (value.equals("Exit")) {
                            break;
                        } else if (value.equals("Hello")) {
                            out.write("HTTP/1.1 200 OK\r\n".getBytes());
                            out.write("Hello, friend\r\n".getBytes());
                            continue;
                        } else {
                            out.write("HTTP/1.1 200 OK\r\n".getBytes());
                            out.write((value + "\r\n").getBytes());
                            continue;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                }
            }
        } catch (IOException e) {
            LOG.error("Exception during connection", e);
        }
    }
}
