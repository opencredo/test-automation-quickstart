package com.opencredo.test.api.acceptance.test.interaction.api.objects;

import com.opencredo.test.api.acceptance.test.interaction.dto.Repository;

import java.util.Arrays;
import java.util.List;

public class GithubApi extends AbstractApiObject {
    public GithubApi(String baseUrl) {
        super(baseUrl);
    }

    public List<Repository> getRepositoryListForOrganisation(String organisation) {
        return Arrays.asList(restTemplate.getForEntity(baseUrl + "/orgs/" + organisation + "/repos", Repository[].class).getBody());
    }
}
