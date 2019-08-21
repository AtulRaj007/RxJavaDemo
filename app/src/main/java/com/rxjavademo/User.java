package com.rxjavademo;

public class User {
    private int userId;
    private String name;
    private String address;

    public User() {
        userId = 100;
        name = "Atul Raj";
        address = "Delhi";
    }

    @Override
    public String toString() {
        return "<<UserId>>::" + userId + "<<Name>>::" + name + "<<Address>>::" + address;
    }
}
