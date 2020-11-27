package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenIncrement() throws Exception {
        CASCount cas = new CASCount();
        Thread t1 = new Thread(() -> cas.increment());
        Thread t2 = new Thread(() -> cas.increment());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(cas.get(), is(2));
    }
}