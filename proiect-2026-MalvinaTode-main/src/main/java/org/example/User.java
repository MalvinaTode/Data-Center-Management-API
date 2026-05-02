package org.example;

public abstract class User {
    protected String name;
    protected String role;
    protected String email;

    public User(String name, String role, String email) {
        this.name = name;
        this.role = role;
        this.email = email;
    }
    //gettere
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getEmail() {
        return email;
    }
}
