package com.example;

import com.sun.net.httpserver.*;
import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class EmployeeHandler implements HttpHandler {
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if ("GET".equals(exchange.getRequestMethod())) {
                List<Employee> employees = DBHelper.getAllEmployees();
                String response = gson.toJson(employees);
                sendResponse(exchange, response, 200);
            } else if ("POST".equals(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                Employee employee = gson.fromJson(isr, Employee.class);
                DBHelper.insertEmployee(employee);
                sendResponse(exchange, "{\"status\":\"inserted\"}", 200);
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
