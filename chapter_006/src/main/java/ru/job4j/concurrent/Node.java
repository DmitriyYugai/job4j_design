package ru.job4j.concurrent;

public class Node<T> {
    private Node next;
    private T value;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        throw new UnsupportedOperationException("Object is immutable");
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        throw new UnsupportedOperationException("Object is immutable");
    }
}
