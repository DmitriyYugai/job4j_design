package ru.job4j.concurrent;

import java.util.concurrent.TimeUnit;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (count != total) {
                try {
                    monitor.wait();
                    System.out.println(Thread.currentThread().getName() + " is awaken.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        }
    }

    public static void main(String[] args) throws Exception {
        CountBarrier cb = new CountBarrier(3);
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started.");
            cb.await();
        });
        thread.start();
        for (int i = 0; i < 3; i++) {
            cb.count();
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
