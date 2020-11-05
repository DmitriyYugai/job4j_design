package ru.job4j.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class DuplicateSearch {
    public static void searchDuplicates(String src) throws Exception {
        Path path = Paths.get(src);
        DuplicateVisitor visitor = new DuplicateVisitor();
        Files.walkFileTree(path, visitor);
        List<Path> list = visitor.getList();
        Comparator<Path> cmp = Comparator.comparing(Path::getFileName);
        list.sort(cmp);
        boolean flag = false;
        for (int i = 0; i < list.size() - 1; i++) {
            Path p1 = list.get(i);
            Path p2 = list.get(i + 1);
            if (p1.getFileName().equals(p2.getFileName())
                    && p1.toFile().length() == p2.toFile().length()) {
                flag = true;
                System.out.println(p1.toAbsolutePath());
                continue;
            }
            if (flag) {
                System.out.println(p1.toAbsolutePath());
                System.out.println("------------------------");
                flag = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        searchDuplicates("C:\\projects\\duplicates_test");
    }
}
