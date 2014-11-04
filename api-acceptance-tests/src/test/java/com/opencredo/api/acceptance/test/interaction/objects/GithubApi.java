package com.opencredo.api.acceptance.test.interaction.objects;


import com.opencredo.api.acceptance.test.common.RepositoryResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public class GithubApi extends AbstractApiObject {
    public GithubApi (String baseUrl) {
        super(baseUrl);
    }

    public List<RepositoryResponse> getRepositoryListForUser(String user) {
        ResponseEntity<String> responseEntity = httpRequest(HttpMethod.GET, path + "/users/" + user + "/repos");

        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(responseEntity.getBody(), new TypeReference<List<RepositoryResponse>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
