package com.opencredo.test.api.acceptance.test.interaction.dto;

import com.opencredo.test.utils.RandomUtils;

public class User {
    public String userName;
    public String firstName;
    public String lastName;
    public String authToken;

    public User() {
        this.firstName = RandomUtils.randomAlphaString(8);
        this.lastName = RandomUtils.randomAlphaString(8);

        this.userName = firstName.charAt(0) + lastName;
    }
}
