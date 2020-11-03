package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashTable<K, V> implements Iterable<K> {
    private MapEntry<K, V>[] array;
    private long modCount;
    private int size;
    private double loadFactor = 0.75;

    public SimpleHashTable() {
        array = (MapEntry<K, V>[]) new MapEntry[10];
    }

    public SimpleHashTable(int sz) {
        array = (MapEntry<K, V>[]) new MapEntry[sz];
    }

    public SimpleHashTable(int sz, double loadFactor) {
        array = (MapEntry<K, V>[]) new MapEntry[sz];
        if (loadFactor < 1 && loadFactor > 0) {
            this.loadFactor = loadFactor;
        }
    }

    public V get(K key) {
        int index = key.hashCode() % array.length;
        if (array[index] == null || !key.equals(array[index].getKey())) {
            return null;
        }
        return array[index].getValue();
    }

    public boolean insert(K key, V value) {
        int index = key.hashCode() % array.length;
        if (array[index] != null) {
            return false;
        }
        if (Math.round(size / array.length) >= loadFactor) {
            MapEntry<K, V>[] tmp = array;
            int newSize = size * 2;
            array = (MapEntry<K, V>[]) new MapEntry[newSize];
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] != null) {
                    int newIndex = tmp[i].getKey().hashCode() % newSize;
                    array[newIndex] = tmp[i];
                }
            }
        }
        size++;
        array[index] = new MapEntry<>(key, value);
        modCount++;
        return true;
    }

    public boolean delete(K key) {
        int index = key.hashCode() % array.length;
        if (array[index] == null || !key.equals(array[index].getKey())) {
            return false;
        }
        array[index] = null;
        size--;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index;
            private int currentIndex;
            private long expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int i = currentIndex + 1; i < array.length; i++) {
                    if (array[i] != null) {
                        index++;
                        currentIndex = i;
                        return array[i].getKey();
                    }
                }
                return null;
            }

        };
    }

}
