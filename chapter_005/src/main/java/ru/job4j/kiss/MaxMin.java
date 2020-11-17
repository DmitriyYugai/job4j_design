package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T maxMin(List<T> value, Comparator<T> comparator) {
        List<T> copy = new ArrayList<>(value);
        for (int index = 0; index < copy.size() - 1; index++) {
            T current = copy.get(index);
            T next = copy.get(index + 1);
            if (comparator.compare(current, next) > 0) {
                copy.set(index, next);
                copy.set(index + 1, current);
            }
        }
        return copy.get(value.size() - 1);
    }

    public static void main(String[] args) {
        MaxMin maxMin = new MaxMin();
        System.out.println(
                maxMin.maxMin(List.of(4, 2, 6, 8, 1, 4, 14, 6, 1),
                        Integer::compareTo));
        System.out.println(
                maxMin.maxMin(List.of(4, 2, 6, 8, 1, 4, 14, 6, 1),
                        Collections.reverseOrder(Integer::compareTo)));
    }
}
