package com.example.scud.model;

public class UsersList {
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String identity_qrcode;
    private String middle_name;

    public String getDescription() {
        return description;
    }

    private String description;

    public String getMiddle_name() {
        return middle_name;
    }

    public String getIdentity_qrcode() {
        return identity_qrcode;
    }

    public String getEmail() {
        return email;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getUsername() {
        return username;
    }


    public UsersList(String username, String first_name, String last_name, String email, String identity_qrcode, String middle_name, String description) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.identity_qrcode = identity_qrcode;
        this.middle_name = middle_name;
        this.description = description;
    }

}
