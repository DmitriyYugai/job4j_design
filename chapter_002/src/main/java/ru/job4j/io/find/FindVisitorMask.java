package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class FindVisitorMask extends SimpleFileVisitor<Path> {
    private List<String> paths;
    private String fileName;
    private PathMatcher matcher;

    public FindVisitorMask(List<String> paths, String fileName) {
        this.paths = paths;
        this.fileName = fileName;
        matcher = FileSystems.getDefault().getPathMatcher("glob:**" + fileName);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (matcher.matches(file)) {
            paths.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
