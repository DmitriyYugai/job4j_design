package ru.job4j.generics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int pointer = 0;

    public SimpleArray(int size) {
        array = new Object[size];
    }

    public void add(T model) {
        array[pointer++] = model;
    }

    public T get(int index) {
        return (T) array[Objects.checkIndex(index, pointer)];
    }

    public void set(int index, T model) {
        array[Objects.checkIndex(index, pointer)] = model;
    }

    public void remove(int index) {
        System.arraycopy(array, Objects.checkIndex(index, pointer) + 1,
                array, index, pointer - index - 1);
        array[--pointer] = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < pointer;
            }

            @Override
            public T next() {
                return (T) array[index++];
            }

        };
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
