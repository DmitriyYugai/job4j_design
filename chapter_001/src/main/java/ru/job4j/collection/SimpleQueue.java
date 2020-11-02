package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        T rsl = out.pop();
        if (rsl == null) {
            throw new NoSuchElementException();
        }
        return rsl;
    }

    public void push(T value) {
        in.push(value);
    }

}

