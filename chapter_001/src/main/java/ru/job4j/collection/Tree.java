package ru.job4j.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> node = findBy(parent);
        if (!node.isPresent()) {
            return false;
        }
        List<Node<E>> listNodes = node.get().children;
        for (Node<E> n : listNodes) {
            if (n.value.equals(child)) {
                return false;
            }
        }
        listNodes.add(new Node<>(child));
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
        return resursive(root);
    }

    private boolean resursive(Node<E> node) {
        boolean rsl = true;
        List<Node<E>> listNodes = node.children;
        if (listNodes.size() > 2) {
            return false;
        }
        for (Node<E> n : listNodes) {
            rsl = resursive(n);
        }
        return rsl;
    }

}
