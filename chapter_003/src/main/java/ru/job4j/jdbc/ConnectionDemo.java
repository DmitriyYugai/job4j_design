package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        HashMap<String, String> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(
                "chapter_003/src/main/resources/app.resources"))) {
            br.lines()
                    .forEach(s -> {
                        String[] pair = s.split(("="));
                        if (pair.length == 2) {
                            map.put(pair[0], pair[1]);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Class.forName("org.postgresql.Driver");
        String url = map.get("url");
        String login = map.get("login");
        String password = map.get("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
