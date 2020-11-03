package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
