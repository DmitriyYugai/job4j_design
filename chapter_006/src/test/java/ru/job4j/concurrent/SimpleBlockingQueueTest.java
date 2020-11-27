package ru.job4j.concurrent;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void whenOnlyOffer() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        Thread producer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                queue.offer(5);
            }
        });
        producer.start();
        while (producer.getState() == Thread.State.RUNNABLE) {
            continue;
        }
        Thread.State rsl = producer.getState();
        producer.interrupt();
        assertThat(rsl, is(Thread.State.WAITING));
    }

    @Test
    public void whenOnlyGet() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.start();
        while (consumer.getState() == Thread.State.RUNNABLE) {
            continue;
        }
        Thread.State rsl = consumer.getState();
        consumer.interrupt();
        assertThat(rsl, is(Thread.State.WAITING));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}