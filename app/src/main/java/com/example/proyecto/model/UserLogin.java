package com.example.proyecto.model;

public class UserLogin {
    private String email;
    private String password;

    public UserLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
