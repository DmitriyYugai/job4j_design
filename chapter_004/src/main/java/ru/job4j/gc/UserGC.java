package ru.job4j.gc;

public class UserGC {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 36000; i++) {
            new User(i, i);
        }
        Thread.sleep(1000);
    }
}
