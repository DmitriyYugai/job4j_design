package ru.job4j.jdbc;

import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class TableEditor implements AutoCloseable {
    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        final String URL = properties.getProperty("url");
        final String LOGIN = properties.getProperty("login");
        final String PASSWORD = properties.getProperty("password");
        connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    public void createTable(String tableName) {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists %s (id serial primary key,"
                            + "name varchar(255))", tableName);
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String table) {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "drop table if exists %s", table);
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "alter table if exists %s "
                            + "add column if not exists %s %s", tableName, columnName, type);
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "alter table if exists %s "
                            + "drop column if exists %s", tableName, columnName);
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement st = connection.createStatement()) {
            String sql = String.format(
                    "alter table if exists %s "
                            + "rename %s to %s", tableName, columnName, newColumnName);
            st.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getScheme(String tableName) throws SQLException {
        StringBuilder scheme = new StringBuilder();
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties p = new Properties();
        try (FileReader fr = new FileReader(new File(
                "chapter_003/src/main/resources/app.resources"))) {
            p.load(fr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (TableEditor te = new TableEditor(p)) {
            te.dropTable("statements");
            te.createTable("statements");
            System.out.println(te.getScheme("statements"));
            te.addColumn("statements", "age", "int");
            System.out.println(te.getScheme("statements"));
            te.renameColumn("statements", "age", "year");
            System.out.println(te.getScheme("statements"));
            te.dropColumn("statements", "year");
            System.out.println(te.getScheme("statements"));
            te.dropTable("statements");
            System.out.println(te.getScheme("statements"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
