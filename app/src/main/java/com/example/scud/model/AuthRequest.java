package com.example.scud.model;

public class AuthRequest {
    private String username;
    private String password;
    private String email;

    public AuthRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public AuthRequest(String login, String password) {
        this.username = login;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
