package com.opencredo.test.api.acceptance.test.interaction.api.objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractApiObject {
    protected String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    public AbstractApiObject (String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
