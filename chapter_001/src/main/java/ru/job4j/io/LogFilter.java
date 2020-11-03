package ru.job4j.io;

import ru.job4j.it.ListUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> rsl = List.of();
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            rsl = in.lines()
                    .filter(line -> {
                        String[] strings = line.split(" ");
                        return strings[strings.length - 2].equals("404");
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
