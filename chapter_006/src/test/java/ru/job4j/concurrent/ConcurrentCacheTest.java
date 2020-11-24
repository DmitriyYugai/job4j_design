package ru.job4j.concurrent;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConcurrentCacheTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        ConcurrentCache cache = new ConcurrentCache();
        cache.add(new Base(1, 1));
        cache.update(new Base(1, 2));
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, 2));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        assertThat(ex.get().getMessage(), is("Invalid version"));
    }
}