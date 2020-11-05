package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(Path source, String exclude, Path target) throws Exception {
       try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target.toFile())))) {
           Files.walkFileTree(source, new ZipVisitor<Path>(zip, exclude));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        ArgZip argZip = new ArgZip(args);
        if (!argZip.valid()) {
            throw new IllegalArgumentException(
                    "Формат ввода параметров: "
                            + "-d source_directory -e exclude_files -o output_zip");
        }
        Path source = Paths.get(argZip.directory());
        String exclude = argZip.exclude();
        Path output = Paths.get(argZip.output());
        new Zip().packFiles(source, exclude, output);
    }

}
