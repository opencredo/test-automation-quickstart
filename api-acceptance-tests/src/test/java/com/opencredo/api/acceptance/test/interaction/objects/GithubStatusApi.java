package com.opencredo.api.acceptance.test.interaction.objects;


import com.opencredo.api.acceptance.test.common.ServiceStatusResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.*;

import java.io.IOException;

public class GithubStatusApi extends AbstractApiObject {
    public GithubStatusApi (String baseUrl) {
        super(baseUrl);
    }

    public ServiceStatusResponse getServiceStatus() {
        ResponseEntity<String> responseEntity = httpRequest(HttpMethod.GET, path + "/status.json");

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseEntity.getBody(), ServiceStatusResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
