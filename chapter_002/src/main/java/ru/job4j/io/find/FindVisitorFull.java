package ru.job4j.io.find;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.Predicate;

public class FindVisitorFull extends SimpleFileVisitor<Path> {
    private List<String> paths;
    private Predicate<Path> pred;

    public FindVisitorFull(List<String> paths, Predicate<Path> pred) {
        this.paths = paths;
        this.pred = pred;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (pred.test(file)) {
            paths.add(file.toAbsolutePath().toString());
        }
        return FileVisitResult.CONTINUE;
    }

}
