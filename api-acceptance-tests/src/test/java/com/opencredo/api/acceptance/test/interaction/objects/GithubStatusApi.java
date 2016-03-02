package com.opencredo.api.acceptance.test.interaction.objects;


import com.opencredo.api.acceptance.test.common.ServiceStatus;

public class GithubStatusApi extends AbstractApiObject {
    public GithubStatusApi(String baseUrl) {
        super(baseUrl);
    }

    public ServiceStatus getServiceStatus() {
        return restTemplate.getForEntity(baseUrl + "/status.json", ServiceStatus.class).getBody();
    }
}
