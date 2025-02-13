package com.example;

import java.sql.*;

public class WorkoutService {

    private final Connection connection;

    public WorkoutService(Connection connection) {
        this.connection = connection;
    }

    public void saveWorkoutResult(int userId, String result) {
        try {
            String insertResultSQL = "INSERT INTO workout_results (user_id, result) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(insertResultSQL);
            pstmt.setInt(1, userId);
            pstmt.setString(2, result);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}