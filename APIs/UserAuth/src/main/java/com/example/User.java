package com.example;

public class User {
    public long user_id;
    public String name;
    public String email;
    public String password_hash;
    public String phone_number;

    public User() {}

    public User(long user_id, String name, String email, String password_hash, String phone_number) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password_hash = password_hash;
        this.phone_number = phone_number;
    }
}
