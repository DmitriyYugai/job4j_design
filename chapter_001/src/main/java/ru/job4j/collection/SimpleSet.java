package ru.job4j.collection;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> simpleArray;

    public SimpleSet() {
        simpleArray = new SimpleArray<>();
    }

    public SimpleSet(int sz) {
        simpleArray = new SimpleArray<>(sz);
    }

    public void add(T model) {
        if (!exist(model)) {
            simpleArray.add(model);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }

    private boolean exist(T model) {
        for (T element : simpleArray) {
            return Objects.equals(element, model);
        }
        return false;
    }

}
