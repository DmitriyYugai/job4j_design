package ru.job4j.io.zip;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> list, Path target) {
       try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target.toFile())))) {
           for (File file : list) {
               zip.putNextEntry(new ZipEntry(file.getPath()));
               try (BufferedInputStream in = new BufferedInputStream(
                       new FileInputStream(file))) {
                   zip.write(in.readAllBytes());
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fillList(List<File> list, Path source, String exclude) throws Exception {
        Files.walkFileTree(source, new ZipVisitor<Path>(list, exclude));
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
        Zip zip = new Zip();
        List<File> files = new ArrayList<>();
        zip.fillList(files, source, exclude);
        zip.packFiles(files, output);
    }

}
