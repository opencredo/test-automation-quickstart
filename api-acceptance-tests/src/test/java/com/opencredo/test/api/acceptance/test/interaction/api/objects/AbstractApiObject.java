package com.opencredo.test.api.acceptance.test.interaction.api.objects;

import com.opencredo.test.api.acceptance.test.config.spring.ApiTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = ApiTestConfig.class)
public abstract class AbstractApiObject {
    protected String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    public AbstractApiObject(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
