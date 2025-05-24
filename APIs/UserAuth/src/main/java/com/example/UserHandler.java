package com.example;

import com.sun.net.httpserver.*;
import com.google.gson.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class UserHandler implements HttpHandler {
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();

            if ("POST".equals(exchange.getRequestMethod()) && path.endsWith("/register")) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                User user = gson.fromJson(isr, User.class);
                DBHelper.registerUser(user);
                sendResponse(exchange, "{\"status\":\"registered\"}", 200);

            } else if ("POST".equals(exchange.getRequestMethod()) && path.endsWith("/login")) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                User user = gson.fromJson(isr, User.class);
                boolean valid = DBHelper.authenticateUser(user.email, user.password_hash);
                if (valid) {
                    sendResponse(exchange, "{\"status\":\"login_success\"}", 200);
                } else {
                    sendResponse(exchange, "{\"status\":\"login_failed\"}", 401);
                }

            } else {
                sendResponse(exchange, "Unsupported method or path", 405);
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
