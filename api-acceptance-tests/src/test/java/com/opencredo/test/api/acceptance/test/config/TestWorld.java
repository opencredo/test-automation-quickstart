package com.opencredo.test.api.acceptance.test.config;

import com.opencredo.test.api.acceptance.test.interaction.dto.User;

import java.util.HashMap;

public class TestWorld {
    public static final String USER_PASSWORD = "Testing123!";

    public String currentUserAlias;
    private HashMap<String, User> users = new HashMap<>();

    public void addUser(String userAlias, User user) {
        users.put(userAlias, user);
    }

    public User getUserByAlias(String userAlias) {
        return users.get(userAlias);
    }

    public User getCurrentUser() {
        return users.get(currentUserAlias);
    }
}
