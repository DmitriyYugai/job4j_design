package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return row < data.length && data[data.length - 1].length > 0;
    }

    @Override
    public Integer next() {
        int rsl = 0;
        while (row < data.length) {
            if (column < data[row].length) {
                rsl = data[row][column];
                column++;
                return rsl;
            }
            column = 0;
            row++;
        }
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return rsl;
    }
}
