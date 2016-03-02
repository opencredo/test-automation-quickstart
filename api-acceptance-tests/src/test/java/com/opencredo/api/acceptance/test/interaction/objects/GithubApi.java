package com.opencredo.api.acceptance.test.interaction.objects;


import com.opencredo.api.acceptance.test.common.Repository;
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
