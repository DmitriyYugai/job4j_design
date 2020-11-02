package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SimpleTree<E> {
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    boolean isBinary();

    class Node<E> {
        @SuppressWarnings("checkstyle:VisibilityModifier")
        public final E value;
        @SuppressWarnings("checkstyle:VisibilityModifier")
        public final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }

    }
}
