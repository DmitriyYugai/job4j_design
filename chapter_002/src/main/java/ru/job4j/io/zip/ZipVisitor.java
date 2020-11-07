package ru.job4j.io.zip;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipVisitor<P> extends SimpleFileVisitor<Path> {
    private ZipOutputStream zip;
    private String exclude;

    public ZipVisitor(ZipOutputStream zip, String exclude) {
        this.zip = zip;
        this.exclude = exclude;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.getFileName().toString().contains(exclude)) {
            zip.putNextEntry(new ZipEntry(file.toFile().getPath()));
            try (BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(file.toFile()))) {
                zip.write(in.readAllBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return FileVisitResult.CONTINUE;
    }
}
