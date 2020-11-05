package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.size() == 0) {
            throw new IllegalArgumentException("Нет входных параметров");
        }
        return values.get(key);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void parse(String[] args) {
        for (String arg : args) {
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Входной параметр должен начинаться с '-'");
            }
            arg = arg.substring(1);
            String[] splits = arg.split("=");
            if (splits.length != 2) {
                throw new IllegalArgumentException(
                        "Входной параметр должен иметь формат ключ=значение");
            }
            values.put(splits[0], splits[1]);
        }
    }
}
