package com.example;

import java.sql.*;
import java.util.Scanner;

public class UserService {

    private final Connection connection;

    public UserService(Connection connection) {
        this.connection = connection;
    }

    public int registerUser(String gender, int age, int height, int weight, String bodyType) {
        try {
            String insertUserSQL = "INSERT INTO users (gender, age, height, weight, body_type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, gender);
            pstmt.setInt(2, age);
            pstmt.setInt(3, height);
            pstmt.setInt(4, weight);
            pstmt.setString(5, bodyType);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
