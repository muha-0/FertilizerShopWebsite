package com.example;

import java.sql.*;
import java.util.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:orders.db";

    public static void initOrdersTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS orders (" +
                    "order_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user_id INTEGER," +
                    "shipping_address_id INTEGER," +
                    "total_price REAL NOT NULL," +
                    "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "status TEXT CHECK(status IN ('pending', 'confirmed', 'shipped', 'delivered', 'canceled')) DEFAULT 'pending'"
                    +
                    ")");
        }
    }

    public static List<Order> getAllOrders() throws SQLException {
        List<Order> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM orders")) {
            while (rs.next()) {
                list.add(new Order(
                        rs.getLong("order_id"),
                        rs.getInt("user_id"),
                        rs.getObject("shipping_address_id") != null ? rs.getInt("shipping_address_id") : null,
                        rs.getDouble("total_price"),
                        rs.getTimestamp("date"),
                        rs.getString("status")));
            }
        }
        return list;
    }

    public static Order getOrderById(long id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE order_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getLong("order_id"),
                            rs.getInt("user_id"),
                            rs.getObject("shipping_address_id") != null ? rs.getInt("shipping_address_id") : null,
                            rs.getDouble("total_price"),
                            rs.getTimestamp("date"),
                            rs.getString("status"));
                }
            }
        }
        return null;
    }

    public static void insertOrder(Order o) throws SQLException {
        String sql = "INSERT INTO orders(user_id, shipping_address_id, total_price, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, o.user_id);
            if (o.shipping_address_id != null) {
                pstmt.setInt(2, o.shipping_address_id);
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            pstmt.setDouble(3, o.total_price);
            pstmt.setString(4, o.status);
            pstmt.executeUpdate();
        }
    }

    public static void updateOrder(Order o) throws SQLException {
        String sql = "UPDATE orders SET user_id = ?, shipping_address_id = ?, total_price = ?, status = ? WHERE order_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, o.user_id);
            if (o.shipping_address_id != null) {
                pstmt.setInt(2, o.shipping_address_id);
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            pstmt.setDouble(3, o.total_price);
            pstmt.setString(4, o.status);
            pstmt.setLong(5, o.order_id);
            pstmt.executeUpdate();
        }
    }

    public static void deleteOrder(long id) throws SQLException {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        }
    }

}
