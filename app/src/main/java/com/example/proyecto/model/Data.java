package com.example.proyecto.model;

import java.time.OffsetDateTime;

public class Data {

    private User user;
    private Object revisando;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Object getRevisando() {
        return revisando;
    }

    public void setRevisando(Object revisando) {
        this.revisando = revisando;
    }
    private OffsetDateTime updatedAt;
    private String name;
    private OffsetDateTime createdAt;
    private long id;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime value) {
        this.updatedAt = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime value) {
        this.createdAt = value;
    }

    public long getid() {
        return id;
    }

    public void setid(long value) {
        this.id = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public static class User {
        private long id;
        private String name;
        private String email;
        private String email_verified_at;
        private int is_active;
        private OffsetDateTime created_at;
        private OffsetDateTime updated_at;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        public void setEmail_verified_at(String email_verified_at) {
            this.email_verified_at = email_verified_at;
        }

        public int getIs_active() {
            return is_active;
        }

        public void setIs_active(int is_active) {
            this.is_active = is_active;
        }

        public OffsetDateTime getCreated_at() {
            return created_at;
        }

        public void setCreated_at(OffsetDateTime created_at) {
            this.created_at = created_at;
        }

        public OffsetDateTime getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(OffsetDateTime updated_at) {
            this.updated_at = updated_at;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }
}
