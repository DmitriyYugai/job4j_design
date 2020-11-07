package ru.job4j.io.zip;

import java.util.HashMap;
import java.util.Map;

public class ArgZip {
    private final String[] args;
    private final Map<String, String> params = new HashMap<>();

    public ArgZip(String[] args) {
        this.args = args;
    }

    public boolean valid() {
        if (args.length != 6) {
            return false;
        }
        params.put(args[0], args[1]);
        params.put(args[2], args[3]);
        params.put(args[4], args[5]);
        return params.containsKey("-d") && params.containsKey("-e") && params.containsKey("-o");
    }

    public String directory() {
        return params.get("-d");
    }

    public String exclude() {
        return params.get("-e");
    }

    public String output() {
        return params.get("-o");
    }
}
