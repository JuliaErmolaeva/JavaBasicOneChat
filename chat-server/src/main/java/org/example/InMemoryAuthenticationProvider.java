package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.Role.ADMIN;

public class InMemoryAuthenticationProvider implements AuthenticationProvider {
    private final List<User> users;

    public InMemoryAuthenticationProvider() {
        this.users = new ArrayList<>();
        this.users.add(User.createAdmin());
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (User user : users) {
            if (Objects.equals(user.getPassword(), password) && Objects.equals(user.getLogin(), login)) {
                return user.getUsername();
            }
        }
        return null;
    }

    @Override
    public synchronized boolean register(String login, String password, String username) {
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username) || Objects.equals(user.getLogin(), login)) {
                return false;
            }
        }
        users.add(new User(login, password, username));
        return true;
    }

    @Override
    public synchronized boolean isCurrentUserCanKick(String username) {
        for (User user : users) {
            if (username.equals(user.getUsername()) && ADMIN.equals(user.getRole())) {
                return true;
            }
        }
        return false;
    }
}
