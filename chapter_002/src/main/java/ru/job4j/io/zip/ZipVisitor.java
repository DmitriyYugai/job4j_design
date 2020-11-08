package ru.job4j.io.zip;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.ZipEntry;

public class ZipVisitor<P> extends SimpleFileVisitor<Path> {
    private List<File> list;
    private String exclude;

    public ZipVisitor(List<File> list, String exclude) {
        this.list = list;
        this.exclude = exclude;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.getFileName().toString().contains(exclude)) {
            list.add(file.toFile());
        }
        return FileVisitResult.CONTINUE;
    }
}
