package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                || second.getState() != Thread.State.TERMINATED) {
            System.out.println("Threads are running");
        }
        System.out.println("State of the first thread is: " + first.getState());
        System.out.println("State of the second thread is: " + second.getState());
        System.out.println("All threads are terminated");
    }
}
