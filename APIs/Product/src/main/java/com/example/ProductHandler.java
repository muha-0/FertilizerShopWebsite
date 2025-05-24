package com.example;

import com.sun.net.httpserver.*;
import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class ProductHandler implements HttpHandler {
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();
            String[] pathParts = path.split("/");

            if ("GET".equals(exchange.getRequestMethod())) {
                if (pathParts.length == 3 && pathParts[2].matches("\\d+")) {
                    int id = Integer.parseInt(pathParts[2]);
                    Product product = DBHelper.getProductById(id);
                    if (product != null) {
                        sendResponse(exchange, gson.toJson(product), 200);
                    } else {
                        sendResponse(exchange, "Product not found", 404);
                    }
                } else {
                    List<Product> products = DBHelper.getAllProducts();
                    sendResponse(exchange, gson.toJson(products), 200);
                }
            } else if ("POST".equals(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                Product product = gson.fromJson(isr, Product.class);
                DBHelper.insertProduct(product);
                sendResponse(exchange, "{\"status\":\"inserted\"}", 200);
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                Product product = gson.fromJson(isr, Product.class);
                DBHelper.updateProduct(product);
                sendResponse(exchange, "{\"status\":\"updated\"}", 200);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                if (pathParts.length == 3 && pathParts[2].matches("\\d+")) {
                    int id = Integer.parseInt(pathParts[2]);
                    DBHelper.deleteProduct(id);
                    sendResponse(exchange, "{\"status\":\"deleted\"}", 200);
                } else {
                    sendResponse(exchange, "Missing product ID", 400);
                }
            } else {
                sendResponse(exchange, "Unsupported method", 405);
            }
        } catch (SQLException e) {
            sendResponse(exchange, "DB Error: " + e.getMessage(), 500);
        }
    }

    private void sendResponse(HttpExchange exchange, String response, int code) throws IOException {
        exchange.sendResponseHeaders(code, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
