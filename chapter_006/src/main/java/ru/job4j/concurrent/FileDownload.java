package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println(
                    "Invalid input data. Usage: wget <url> <bytes_per_second>"
            );
        }
        String file = args[1];
        int speed = Integer.parseInt(args[2]);
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int timeSleep;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                timeSleep = bytesRead * 1000 / speed;
                Thread.sleep(timeSleep);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
