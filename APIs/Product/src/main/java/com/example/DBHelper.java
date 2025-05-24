package com.example;

import java.sql.*;
import java.util.*;

public class DBHelper {
    private static final String DB_URL = "jdbc:sqlite:products.db";

    public static void initProductsTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS products (" +
                    "product_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT NOT NULL," +
                    "description TEXT," +
                    "image TEXT," +
                    "available_sizes TEXT," +
                    "regular_price REAL NOT NULL," +
                    "sale_price REAL)");
        }
    }

    public static List<Product> getAllProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        Arrays.asList(rs.getString("available_sizes").split(",")),
                        rs.getDouble("regular_price"),
                        rs.getDouble("sale_price")));
            }
        }
        return list;
    }

    public static Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("product_id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("image"),
                            Arrays.asList(rs.getString("available_sizes").split(",")),
                            rs.getDouble("regular_price"),
                            rs.getDouble("sale_price"));
                }
            }
        }
        return null;
    }

    public static void insertProduct(Product p) throws SQLException {
        String sql = "INSERT INTO products(title, description, image, available_sizes, regular_price, sale_price) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.title);
            pstmt.setString(2, p.description);
            pstmt.setString(3, p.image);
            pstmt.setString(4, String.join(",", p.available_sizes));
            pstmt.setDouble(5, p.regular_price);
            pstmt.setDouble(6, p.sale_price);
            pstmt.executeUpdate();
        }
    }

    public static void updateProduct(Product p) throws SQLException {
        String sql = "UPDATE products SET title = ?, description = ?, image = ?, available_sizes = ?, regular_price = ?, sale_price = ? WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, p.title);
            pstmt.setString(2, p.description);
            pstmt.setString(3, p.image);
            pstmt.setString(4, String.join(",", p.available_sizes));
            pstmt.setDouble(5, p.regular_price);
            pstmt.setDouble(6, p.sale_price);
            pstmt.setInt(7, p.product_id);
            pstmt.executeUpdate();
        }
    }

    public static void deleteProduct(int product_id) throws SQLException {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product_id);
            pstmt.executeUpdate();
        }
    }

}
