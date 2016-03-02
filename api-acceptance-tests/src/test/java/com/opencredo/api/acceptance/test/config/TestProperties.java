package com.opencredo.api.acceptance.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class TestProperties {
    @Autowired
    private Environment environment;

    public String getGithubStatusApiUrl() {
        return environment.getProperty("api.acceptance.test.github.status.api");
    }

    public String getGithubApiUrl() {
        return environment.getProperty("api.acceptance.test.github.api");
    }
}
