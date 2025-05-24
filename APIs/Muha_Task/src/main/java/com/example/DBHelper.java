package com.example;

import java.sql.*;
import java.util.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:employees.db";

    public static void init() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS employees (id INTEGER PRIMARY KEY, name TEXT, age TEXT)");
        }
    }

    public static List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
            while (rs.next()) {
                list.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("age")));
            }
        }
        return list;
    }

    public static void insertEmployee(Employee s) throws SQLException {
        String sql = "INSERT INTO employees(name, age) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, s.name);
            pstmt.setString(2, s.age);
            pstmt.executeUpdate();
        }
    }
}
