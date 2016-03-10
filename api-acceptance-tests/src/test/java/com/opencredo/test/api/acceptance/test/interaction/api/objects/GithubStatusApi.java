package com.opencredo.test.api.acceptance.test.interaction.api.objects;


import com.opencredo.test.api.acceptance.test.interaction.dto.ServiceStatus;

public class GithubStatusApi extends AbstractApiObject {
    public GithubStatusApi(String baseUrl) {
        super(baseUrl);
    }

    public ServiceStatus getServiceStatus() {
        return restTemplate.getForEntity(baseUrl + "/status.json", ServiceStatus.class).getBody();
    }
}
