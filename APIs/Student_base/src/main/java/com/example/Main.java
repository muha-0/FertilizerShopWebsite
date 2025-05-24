package com.example;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        DBHelper.init();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/students", new StudentHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("🚀 Server started on http://localhost:8080/students");
    }
}
