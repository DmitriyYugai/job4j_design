package ru.job4j.concurrent;

public class Switcher {
    private static Object monitor = new Object();
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    while (true) {
                        synchronized (monitor) {
                            while (flag) {
                                try {
                                    monitor.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("Thread A");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            flag = true;
                            monitor.notifyAll();
                        }
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        synchronized (monitor) {
                            while (!flag) {
                                try {
                                    monitor.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("Thread B");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            flag = false;
                            monitor.notifyAll();
                        }
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
    }
}
