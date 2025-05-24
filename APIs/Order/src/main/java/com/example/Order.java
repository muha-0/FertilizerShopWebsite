package com.example;

import java.sql.Timestamp;

public class Order {
    public long order_id;
    public int user_id;
    public Integer shipping_address_id;
    public double total_price;
    public Timestamp date;
    public String status;

    public Order() {}

    public Order(long order_id, int user_id, Integer shipping_address_id, double total_price, Timestamp date, String status) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.shipping_address_id = shipping_address_id;
        this.total_price = total_price;
        this.date = date;
        this.status = status;
    }
}
