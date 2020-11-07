package ru.job4j.io.find;

import java.util.HashMap;
import java.util.Map;

public class ArgFind {
    private static final int FULL_NAME = 0;
    private static final int MASK = 1;
    private static final int REGEX = 2;
    private final String[] args;
    private final Map<String, String> params = new HashMap<>();

    public ArgFind(String[] args) {
        this.args = args;
    }

    @SuppressWarnings("checkstyle:LineLength")
    public boolean valid() {
        if (args.length != 7) {
            return false;
        }
        params.put(args[0], args[1]);
        params.put(args[2], args[3]);
        params.put(args[4], null);
        params.put(args[5], args[6]);
        return params.containsKey("-d") && params.containsKey("-n")
                && (params.containsKey("-m") || params.containsKey("-f") || params.containsKey("-r"))
                && params.containsKey("-o");
    }

    public String directory() {
        return params.get("-d");
    }

    public String fileName() {
        return params.get("-n");
    }

    public int mode() {
        if (params.containsKey("-f")) {
            return FULL_NAME;
        } else if (params.containsKey("-m")) {
            return MASK;
        } else {
            return REGEX;
        }
    }

    public String output() {
        return params.get("-o");
    }
}
