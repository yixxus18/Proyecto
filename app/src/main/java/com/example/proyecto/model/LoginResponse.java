package com.example.proyecto.model;

// Modelo para la respuesta del inicio de sesión
public class LoginResponse {

    private String access_token;
    private String token_type;
    private int expires_in;
    public String getAccessToken() {
        return access_token;
    }

    public String getTokenType() {
        return token_type;
    }

    public int getExpiresIn() {
        return expires_in;
    }
}

