package org.example;

public class User {
    private String login;
    private String password;
    private String username;
    private Role role;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public User(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
        this.role = Role.USER;
    }

    private User() {
        this.login = "admin";
        this.password = "admin";
        this.username = "admin";
        this.role = Role.ADMIN;
    }

    protected static User createAdmin() {
        return new User();
    }
}
