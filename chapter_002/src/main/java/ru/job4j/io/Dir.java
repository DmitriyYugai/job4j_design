package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(
                String.format("Total size : %s", file.getTotalSpace()));
        System.out.println(
                String.format("%-20s%-20s", "Files :", "Size :"));
        for (File subfile : file.listFiles()) {
            System.out.println(
                    String.format("%-20s%-20d", subfile.getName(), subfile.length()));
        }
    }
}
