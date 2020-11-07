package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FindVisitorRegex extends SimpleFileVisitor<Path> {
    private List<String> paths;
    private String fileName;
    private PathMatcher matcher;

    public FindVisitorRegex(List<String> paths, String fileName) {
        this.paths = paths;
        this.fileName = fileName;
        matcher = FileSystems.getDefault().getPathMatcher("regex:" + fileName);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (matcher.matches(file)) {
            paths.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
