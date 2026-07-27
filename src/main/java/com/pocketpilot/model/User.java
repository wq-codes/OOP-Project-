package com.pocketpilot.model;

public class User {

    private String userId;
    private String username;
    private String password;
    private String email;

    public User() {

    }

    public User(String userId, String username, String password, String email) {

        this.userId = userId;

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }
        this.username = username;

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.password = password;

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}