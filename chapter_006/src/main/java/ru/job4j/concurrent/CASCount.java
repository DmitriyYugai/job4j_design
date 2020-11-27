package ru.job4j.concurrent;

import com.sun.security.jgss.GSSUtil;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        int number;
        int increment;
        do {
            number = count.get();
            increment = number + 1;
        } while (!count.compareAndSet(number, increment));
    }

    public int get() {
        return count.get();
    }
}
