package ru.job4j.io.find;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Find {
    private final List<String> rsl = new ArrayList<>();

    public void findFiles(
            Path directory, String fileName, int mode, Path output) throws IOException {
        findAndPack(directory, fileName, mode);
        writeToFile(rsl, output);
    }

    private void findAndPack(Path directory, String fileName, int mode) throws IOException {
        if (mode == 0) {
            Files.walkFileTree(directory, new FindVisitorFull(rsl, fileName));
        } else if (mode == 1) {
            Files.walkFileTree(directory, new FindVisitorMask(rsl, fileName));
        } else {
            Files.walkFileTree(directory, new FindVisitorRegex(rsl, fileName));
        }
    }

    private void writeToFile(List<String> paths, Path output) {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(output.toFile())
                )
        )) {
            for (String path : paths) {
                out.println(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgFind argFind = new ArgFind(args);
        if (!argFind.valid()) {
            throw new IllegalArgumentException(
                    "Формат ввода параметров: "
                            + "-d source_directory -n file_name (-f or -m or -r) -o output_file");
        }
        Path srcDir = Paths.get(argFind.directory());
        String fileName = argFind.fileName();
        int mode = argFind.mode();
        Path output = Paths.get(argFind.output());
        new Find().findFiles(srcDir, fileName, mode, output);
    }
}
