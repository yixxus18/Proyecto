package com.example.proyecto.response;

import com.google.gson.annotations.SerializedName;

public class MyResponse {
    @SerializedName("msg")
    private String message;

    public String getMessage() {
        return message;
    }
}
