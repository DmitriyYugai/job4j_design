package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;
    private long modCount;

    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, size, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Node<E> start = first;
        while (Objects.checkIndex(index, size) != start.index) {
            start = start.next;
        }
        return start.value;
    }

    @Override
    public Iterator<E> iterator() {
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
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return get(index++);
            }

        };
    }

    private static class Node<E> {
        private int index;
        private E value;
        private Node<E> prev;
        private Node<E> next;

        public Node(Node<E> prev, int index, E value, Node<E> next) {
            this.index = index;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
