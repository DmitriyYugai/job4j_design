package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FindVisitorFull extends SimpleFileVisitor<Path> {
    private List<String> paths;
    private String fileName;

    public FindVisitorFull(List<String> paths, String fileName) {
        this.paths = paths;
        this.fileName = fileName;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.getFileName().toString().equals(fileName)) {
            paths.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }

}
