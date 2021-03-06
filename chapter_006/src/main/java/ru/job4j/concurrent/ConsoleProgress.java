package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    private final String[] process = {"\\", "|", "/"};

    @Override
    public void run() {
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[index++]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (index == 3) {
                index = 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
