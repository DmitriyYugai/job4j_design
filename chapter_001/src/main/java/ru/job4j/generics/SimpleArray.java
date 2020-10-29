package ru.job4j.generics;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Class<T> type;
    private T[] array;
    private int pointer = 0;

    public SimpleArray(Class<T> type, int size) {
        this.type = type;
        array = (T[]) Array.newInstance(type, size);
    }

    public T[] getArray() {
        return Arrays.copyOf(array, pointer);
    }

    public void add(T model) {
        array[pointer++] = model;
    }

    public T get(int index) {
        return array[Objects.checkIndex(index, pointer)];
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
                return array[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
