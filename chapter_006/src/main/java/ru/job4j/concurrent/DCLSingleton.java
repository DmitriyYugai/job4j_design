package ru.job4j.concurrent;

public class DCLSingleton {
    private static DCLSingleton inst;

    private DCLSingleton() { }

    public static synchronized DCLSingleton instOf() {
        if (inst == null) {
            inst = new DCLSingleton();
        }
        return inst;
    }

}
