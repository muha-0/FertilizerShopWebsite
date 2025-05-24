package com.example;

import java.sql.*;
import java.util.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:students.db";

    public static void init() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY, name TEXT, major TEXT)");
        }
    }

    public static List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("major")));
            }
        }
        return list;
    }

    public static void insertStudent(Student s) throws SQLException {
        String sql = "INSERT INTO students(name, major) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s.name);
            pstmt.setString(2, s.major);
            pstmt.executeUpdate();
        }
    }
}
