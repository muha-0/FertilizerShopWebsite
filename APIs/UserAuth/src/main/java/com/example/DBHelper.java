package com.example;

import java.sql.*;
import java.util.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public static void initUsersTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT UNIQUE NOT NULL," +
                    "password_hash TEXT NOT NULL," +
                    "phone_number TEXT UNIQUE NOT NULL)");
        }
    }

    public static void registerUser(User u) throws SQLException {
        String sql = "INSERT INTO users(name, email, password_hash, phone_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, u.name);
            pstmt.setString(2, u.email);
            pstmt.setString(3, u.password_hash); // hash this later
            pstmt.setString(4, u.phone_number);
            pstmt.executeUpdate();
        }
    }

    public static boolean authenticateUser(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password_hash = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password); // later: compare hashed password
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

}
