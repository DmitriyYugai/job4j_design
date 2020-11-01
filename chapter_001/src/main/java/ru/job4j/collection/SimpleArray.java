package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private long modCount;
    private int size;

    public SimpleArray() {
        array = new Object[10];
    }

    public SimpleArray(int sz) {
        array = new Object[sz];
    }

    public T get(int index) {
        return (T) array[Objects.checkIndex(index, size)];
    }

    public void add(T model) {
        modCount++;
        if (size >= array.length) {
            System.arraycopy(array, 0, array, 0, size * 2);
        }
        array[size++] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index;
            private long expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) array[index++];
            }

        };
    }

}
