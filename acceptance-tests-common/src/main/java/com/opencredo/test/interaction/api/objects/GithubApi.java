package com.opencredo.test.interaction.api.objects;

import com.opencredo.test.interaction.dto.Repository;

import java.util.Arrays;
import java.util.List;

public class GithubApi extends AbstractApiObject {
    public GithubApi(String baseUrl) {
        super(baseUrl);
    }

    public List<Repository> getRepositoryListForUser(String user) {
        return Arrays.asList(restTemplate.getForEntity(baseUrl + "/users/" + user + "/repos", Repository[].class).getBody());
    }
}
