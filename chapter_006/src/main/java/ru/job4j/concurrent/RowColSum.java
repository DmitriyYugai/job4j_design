package ru.job4j.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RowColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums() {
        }

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        @Override
        public String toString() {
            return "Sums{"
                    + "rowSum=" + rowSum
                    + ", colSum=" + colSum
                    + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum
                    && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            Sums sum = new Sums();
            int row = 0;
            int column = 0;
            for (int r = 0; r < matrix[i].length; r++) {
                row += matrix[i][r];
            }
            for (int c = 0; c < matrix.length; c++) {
                column += matrix[c][i];
            }
            sum.colSum = column;
            sum.rowSum = row;
            sums[i] = sum;
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> map = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            map.put(i, getSums(matrix, i));
        }
        for (Integer key : map.keySet()) {
            sums[key] = map.get(key).get();
        }
        return sums;
    }

    private static CompletableFuture<Sums> getSums(int[][] matrix, int i) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sum = new Sums();
            int row = 0;
            int column = 0;
            for (int r = 0; r < matrix[i].length; r++) {
                row += matrix[i][r];
            }
            for (int c = 0; c < matrix.length; c++) {
                column += matrix[c][i];
            }
            sum.colSum = column;
            sum.rowSum = row;
            return sum;
        });
    }
}
