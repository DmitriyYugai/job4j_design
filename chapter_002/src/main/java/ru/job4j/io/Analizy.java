package ru.job4j.io;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Analizy {

    public void unavailable(String source, String target) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            boolean errorFLag = false;
            while ((line = in.readLine()) != null) {
                String code = line.split(" ")[0];
                String date = line.split(" ")[1];
                if ((code.equals("400") || code.equals("500")) && !errorFLag) {
                    errorFLag = true;
                    list.add(date + ";");
                }
                if ((code.equals("200") || code.equals("300")) && errorFLag) {
                    errorFLag = false;
                    list.add(date + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(
                        new FileWriter(target)))) {
            for (String s : list) {
                out.write(s);
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
