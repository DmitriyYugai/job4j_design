package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return maxMin(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return maxMin(value, Collections.reverseOrder(comparator));
    }

    private <T> T maxMin(List<T> value, Comparator<T> comparator) {
        T extremum = value.get(0);
        for (int index = 1; index < value.size() - 1; index++) {
            T current = value.get(index);
            if (comparator.compare(current, extremum) > 0) {
                extremum = current;
            }
        }
        return extremum;
    }

    public static void main(String[] args) {
        MaxMin maxMin = new MaxMin();
        System.out.println(
                maxMin.max(List.of(4, 2, 6, 8, 1, 4, 14, 6, 1),
                        Comparator.naturalOrder()));
        System.out.println(
                maxMin.min(List.of(4, 2, 6, 8, 1, 4, 14, 6, 1),
                        Comparator.naturalOrder()));
    }
}
