package com.example;

import com.sun.net.httpserver.*;
import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class OrderHandler implements HttpHandler {
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();
            String[] pathParts = path.split("/");

            if ("GET".equals(exchange.getRequestMethod())) {
                if (pathParts.length == 3 && pathParts[2].matches("\\d+")) {
                    long id = Long.parseLong(pathParts[2]);
                    Order order = DBHelper.getOrderById(id);
                    if (order != null) {
                        sendResponse(exchange, gson.toJson(order), 200);
                    } else {
                        sendResponse(exchange, "Order not found", 404);
                    }
                } else {
                    List<Order> orders = DBHelper.getAllOrders();
                    sendResponse(exchange, gson.toJson(orders), 200);
                }
            } else if ("POST".equals(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                Order order = gson.fromJson(isr, Order.class);
                DBHelper.insertOrder(order);
                sendResponse(exchange, "{\"status\":\"inserted\"}", 200);
            } else if ("PUT".equals(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                Order order = gson.fromJson(isr, Order.class);
                DBHelper.updateOrder(order);
                sendResponse(exchange, "{\"status\":\"updated\"}", 200);
            } else if ("DELETE".equals(exchange.getRequestMethod())) {
                if (pathParts.length == 3 && pathParts[2].matches("\\d+")) {
                    long id = Long.parseLong(pathParts[2]);
                    DBHelper.deleteOrder(id);
                    sendResponse(exchange, "{\"status\":\"deleted\"}", 200);
                } else {
                    sendResponse(exchange, "Missing order ID", 400);
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
