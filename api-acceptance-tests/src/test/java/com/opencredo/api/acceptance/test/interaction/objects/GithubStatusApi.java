package com.opencredo.api.acceptance.test.interaction.objects;


import com.opencredo.api.acceptance.test.common.ServiceStatusResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

/**
 * Provides methods for interacting with the GitHub Service Status API
 */
public class GithubStatusApi extends AbstractApiObject {
    public GithubStatusApi (String baseUrl) {
        super(baseUrl);
    }

    public ServiceStatusResponse getServiceStatus() {
        ResponseEntity<String> responseEntity = httpRequest(HttpMethod.GET, baseUrl + "/status.json");

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseEntity.getBody(), ServiceStatusResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
