package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static int[][] multiple(int size) {
        StringBuilder sb = new StringBuilder();
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
                sb.append(i + 1);
                sb.append(" * ");
                sb.append(j + 1);
                sb.append(" = ");
                sb.append(table[i][j]);
                sb.append(System.lineSeparator());
            }
            sb.append(System.lineSeparator());
        }
        try (FileOutputStream out = new FileOutputStream("MuiltiplicationTable.txt")) {
            out.write(sb.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }

    public static void main(String[] args) {
        multiple(3);
    }
}
