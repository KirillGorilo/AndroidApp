package com.example.scud.model;

public class AuthRequest {
    private String username;
    private String password;

    public AuthRequest(String login, String password) {
        this.username = login;
        this.password = password;
    }

    public String getLogin() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
