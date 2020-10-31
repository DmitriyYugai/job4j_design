package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        for (T el = in.pop(); el != null; el = in.pop()) {
            out.push(el);
        }
        T rsl =  out.pop();
        if (rsl == null) {
            throw new NoSuchElementException();
        }
        return rsl;
    }

    public void push(T value) {
        for (T el = out.pop(); el != null; el = out.pop()) {
            in.push(el);
        }
        in.push(value);
    }

}

