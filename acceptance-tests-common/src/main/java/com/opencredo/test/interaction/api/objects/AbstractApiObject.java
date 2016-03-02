package com.opencredo.test.interaction.api.objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public abstract class AbstractApiObject {
    protected String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    public AbstractApiObject (String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
