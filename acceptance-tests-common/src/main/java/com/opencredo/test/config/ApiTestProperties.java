package com.opencredo.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class ApiTestProperties {
    @Autowired
    private Environment environment;

    public String getGithubStatusApiUrl() {
        return environment.getProperty("api.acceptance.test.github.status.api");
    }

    public String getGithubApiUrl() {
        return environment.getProperty("api.acceptance.test.github.api");
    }
}
