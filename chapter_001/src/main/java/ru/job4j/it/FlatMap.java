package ru.job4j.it;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor;

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        while (data.hasNext()) {
            cursor = data.next();
            if (cursor.hasNext()) {
                break;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return cursor.hasNext();
    }

    @Override
    public T next() {
        while (true) {
            if (!hasNext() && !data.hasNext()) {
                throw new NoSuchElementException();
            }
            if (hasNext()) {
                return cursor.next();
            }
            cursor = data.next();
        }
    }

    public static void main(String[] args) {
        Iterator<Iterator<Integer>> data = List.of(
                List.of(1, 2, 3).iterator(),
                List.of(4, 5, 6).iterator(),
                List.of(7, 8, 9).iterator()
        ).iterator();
        FlatMap<Integer> flat = new FlatMap<>(data);
        while (flat.hasNext()) {
            System.out.println(flat.next());
        }
    }

}
