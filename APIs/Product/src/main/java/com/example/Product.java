package com.example;

import java.util.List;

public class Product {
    public int product_id;
    public String title;
    public String description;
    public String image;
    public List<String> available_sizes;
    public double regular_price;
    public double sale_price;

    public Product() {
    }

    public Product(int product_id, String title, String description, String image, List<String> available_sizes,
            double regular_price, double sale_price) {
        this.product_id = product_id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.available_sizes = available_sizes;
        this.regular_price = regular_price;
        this.sale_price = sale_price;
    }
}
