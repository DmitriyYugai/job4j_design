package ru.job4j.io;

import java.io.*;
import java.nio.Buffer;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedWriter(
                             new FileWriter(target)))) {
            String[] lines = in.lines().toArray(String[]::new);
            boolean errorFLag = false;
            for (String line : lines) {
                String code = line.split(" ")[0];
                String date = line.split(" ")[1];
                if ((code.equals("400") || code.equals("500")) && !errorFLag) {
                    errorFLag = true;
                    out.write(date + ";");
                    continue;
                }
                if ((code.equals("200") || code.equals("300")) && errorFLag) {
                    errorFLag = false;
                    out.write(date + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
