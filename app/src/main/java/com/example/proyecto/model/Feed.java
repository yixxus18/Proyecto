package com.example.proyecto.model;


public class Feed {
    private String name;
    private String last_value;

    public Feed(String name, String last_value) {
        this.name = name;
        this.last_value = last_value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_value() {
        return last_value;
    }

    public void setLast_value(String last_value) {
        this.last_value = last_value;
    }
}
