package uk.co.opencredo.api.acceptance.test.interaction.objects;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import uk.co.opencredo.api.acceptance.test.common.ServiceStatusResponse;

import java.io.IOException;
import java.util.Arrays;

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
