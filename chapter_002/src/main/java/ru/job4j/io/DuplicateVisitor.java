package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DuplicateVisitor extends SimpleFileVisitor {
    private final List<Path> list = new ArrayList<>();

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        list.add((Path) file);
        return FileVisitResult.CONTINUE;
    }

}
