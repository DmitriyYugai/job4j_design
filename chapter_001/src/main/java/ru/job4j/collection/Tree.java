package ru.job4j.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> node = findBy(parent);
        Node<E> newNode = new Node<>(child);
        if (!node.isPresent() || !resursive(root, list -> list.contains(newNode))) {
            return false;
        }
        List<Node<E>> listNodes = node.get().children;
        listNodes.add(newNode);
        return true;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean isBinary() {
        return resursive(root, list -> list.size() > 2);
    }

    private boolean resursive(Node<E> node, Predicate<List<Node<E>>> predicate) {
        boolean rsl = true;
        List<Node<E>> listNodes = node.children;
        if (predicate.test(listNodes)) {
            return false;
        }
        for (Node<E> n : listNodes) {
            rsl = resursive(n, list -> list.size() > 2);
        }
        return rsl;
    }

}
