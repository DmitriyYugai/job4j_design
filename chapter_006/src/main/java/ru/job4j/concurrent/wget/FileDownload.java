package ru.job4j.concurrent.wget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class FileDownload implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownload.class.getName());
    private ParseArg parser;

    public FileDownload(ParseArg parser) {
        this.parser = parser;
    }

    @Override
    public void run() {
        if (!parser.isValid()) {
            LOGGER.info(
                    "Invalid input data. Usage: wget <url> <bytes_per_second>"
            );
            return;
        }
        String file = parser.getUrl();
        int speed = Integer.parseInt(parser.getSpeed());
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
        } catch (Exception e) {
            LOGGER.error("IOException", e);
        }
    }

    public static void main(String[] args) {
        new Thread(new FileDownload(new ParseArg(args))).start();
    }

}
