package com.opencredo.test.email.acceptance.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class EmailTestProperties {

    @Autowired
    private Environment environment;

    public String getEmailAddress() {
        return environment.getProperty("email.acceptance.test.email.address");
    }

    public String getEmailPassword() {
        return environment.getProperty("email.acceptance.test.email.password");
    }
}
