package ru.job4j.concurrent;

public class Base {
    private int id;
    private int version;

    public Base(int id) {
        this.id = id;
    }

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
}
