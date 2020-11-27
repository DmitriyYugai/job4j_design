package ru.job4j.concurrent;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent() throws IOException {
        return getContent(data -> true);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(data -> data < 0x80);
    }

    public synchronized void saveContent(String content) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(content);
        }
    }

    private String getContent(Predicate<Integer> pred) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = i.read()) != -1) {
                if (pred.test(data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

}
