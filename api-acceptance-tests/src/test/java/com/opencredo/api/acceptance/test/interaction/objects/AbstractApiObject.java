package com.opencredo.api.acceptance.test.interaction.objects;

import com.opencredo.api.acceptance.test.common.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Reusable methods for all API objects
 */
public abstract class AbstractApiObject {
    protected String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    public AbstractApiObject (String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public ResponseEntity<String> httpRequest (HttpMethod method, String url) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return restTemplate.exchange(url, method, entity, String.class);
    }
}
