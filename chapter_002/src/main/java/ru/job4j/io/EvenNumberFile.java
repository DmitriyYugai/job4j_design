package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream in = new FileInputStream("even.txt")) {
            int symbol;
            while ((symbol = in.read()) != -1) {
                sb.append((char) symbol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] lines = sb.toString().split(System.lineSeparator());
        for (String line : lines) {
            if ((Integer.valueOf(line) % 2) != 0) {
                System.out.println(line + " - " + "нечётное");
                continue;
            }
            System.out.println(line + " - " + "чётное");
        }
    }
}
