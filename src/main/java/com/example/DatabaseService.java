package com.example;

import java.sql.*;

public class DatabaseService {

    private Connection connection;

    public DatabaseService(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "gender TEXT, " +
                    "age INTEGER, " +
                    "height INTEGER, " +
                    "weight INTEGER, " +
                    "body_type TEXT)");

            statement.execute("CREATE TABLE IF NOT EXISTS workout_results (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER, " +
                    "result TEXT, " +
                    "FOREIGN KEY(user_id) REFERENCES users(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
