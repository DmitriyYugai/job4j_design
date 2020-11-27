package ru.job4j.concurrent.wget;

import java.util.ArrayList;
import java.util.List;

public class ParseArg {
    private List<String> list;

    public ParseArg(String[] args) {
        list = new ArrayList<>();
        for (String arg : args) {
            list.add(arg);
        }
    }

    public boolean isValid() {
        return list.size() == 2;
    }

    public String getUrl() {
        return list.get(0);
    }

    public String getSpeed() {
        return list.get(1);
    }
}
