package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private SimpleStack<T> in = new SimpleStack<>();
    private SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T rsl = null;
        while (!in.isEmpty()) {
            out.push(in.pop());
            if (in.isEmpty()) {
                if (rsl != null) {
                    SimpleStack<T> tmp2 = in;
                    in = out;
                    out = tmp2;
                    break;
                }
                rsl = out.pop();
                SimpleStack<T> tmp1 = in;
                in = out;
                out = tmp1;
            }
        }
        if (rsl == null) {
            throw new NoSuchElementException();
        }
        return rsl;
//        for (T el = in.pop(); el != null; el = in.pop()) {
//            out.push(el);
//        }
//        T rsl =  out.pop();
//        for (T el = out.pop(); el != null; el = out.pop()) {
//            in.push(el);
//        }
//        if (rsl == null) {
//            throw new NoSuchElementException();
//        }
//        return rsl;
    }

    public void push(T value) {
        in.push(value);
    }

}

