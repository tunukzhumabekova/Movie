package org.example.Models;

public class users {
    private int id;
    private String username;
    private int password;
    private String email;

    public users(String username, int password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + password +
                ", email='" + email + '\'' +
                '}';
    }
}
