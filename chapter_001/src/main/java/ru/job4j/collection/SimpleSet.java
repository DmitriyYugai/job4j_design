package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {
    private SimpleArray<T> simpleArray;

    public SimpleSet() {
        simpleArray = new SimpleArray<>();
    }

    public SimpleSet(int sz) {
        simpleArray = new SimpleArray<>(sz);
    }

    public void add(T model) {
        for (T element : simpleArray) {
            if (element.equals(model)) {
                return;
            }
        }
        simpleArray.add(model);
    }

    @Override
    public Iterator<T> iterator() {
        return simpleArray.iterator();
    }

}
