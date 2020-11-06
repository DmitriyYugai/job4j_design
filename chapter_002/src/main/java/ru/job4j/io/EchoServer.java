package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    @SuppressWarnings("checkstyle:InnerAssignment")
    public static void main(String[] args) throws IOException {
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
                        if (params.split("=")[1].equals("Bye")) {
                            break;
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}
