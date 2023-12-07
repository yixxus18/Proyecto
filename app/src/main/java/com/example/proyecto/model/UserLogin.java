package com.example.proyecto.model;

// Modelo para los datos de inicio de sesión (correo y contraseña)
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
