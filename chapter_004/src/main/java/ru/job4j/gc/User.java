package ru.job4j.gc;

public class User {
    private int i;
    private long j;

    public User(int i, long j) {
        this.i = i;
        this.j = j;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("User: i = " + i + "; j = " + j);
    }
}
