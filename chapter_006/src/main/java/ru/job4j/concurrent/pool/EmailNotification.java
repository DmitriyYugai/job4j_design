package ru.job4j.concurrent.pool;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ThreadSafe
public class EmailNotification {
    private final ExecutorService executor;

    public EmailNotification(ExecutorService executor) {
        this.executor = executor;
    }

    public void emailTo(User user) {
        executor.execute(() -> {
            String subject = String.format(
                    "Notification %s to email %s", user.getName(), user.getEmail());
            String body = String.format("Add a new event to %s", user.getName());
            send(subject, body, user.getEmail());
        });
    }

    public void close() {
        executor.shutdown();
    }

    public void send(String subject, String body, String email) {

    }

    public static void main(String[] args) {
        EmailNotification notification = new EmailNotification(
                Executors.newFixedThreadPool(
                        Runtime.getRuntime().availableProcessors()));
        notification.emailTo(new User("Dmitry", "student@mail.ru"));
        notification.close();
    }

}
