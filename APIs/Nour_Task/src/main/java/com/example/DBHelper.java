package com.example;

import java.sql.*;
import java.util.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:doctors.db";

    public static void init() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS doctors (id INTEGER PRIMARY KEY, name TEXT, age TEXT)");
        }
    }

    public static List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM doctors")) {
            while (rs.next()) {
                list.add(new Doctor(rs.getInt("id"), rs.getString("name"), rs.getString("age")));
            }
        }
        return list;
    }

    public static void insertDoctor(Doctor d) throws SQLException {
        String sql = "INSERT INTO doctors(name, age) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, d.name);
            pstmt.setString(2, d.age);
            pstmt.executeUpdate();
        }
    }
}
