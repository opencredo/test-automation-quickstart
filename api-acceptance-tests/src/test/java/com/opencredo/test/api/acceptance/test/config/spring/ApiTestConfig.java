package com.opencredo.test.api.acceptance.test.config.spring;

import com.opencredo.test.api.acceptance.test.config.ApiTestProperties;
import com.opencredo.test.api.acceptance.test.config.TestWorld;
import com.opencredo.test.api.acceptance.test.interaction.api.objects.GithubApi;
import com.opencredo.test.api.acceptance.test.interaction.api.objects.GithubStatusApi;
import com.opencredo.test.api.acceptance.test.interaction.api.objects.MessagingApi;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource(value = {"classpath:/props-for-api-tests.properties"})
public class ApiTestConfig {
    @Bean
    public TestWorld testWorld() {
        return new TestWorld();
    }

    @Bean
    public ApiTestProperties properties() {
        return new ApiTestProperties();
    }

    @Bean
    public GithubApi githubApi() {
        return new GithubApi(properties().getGithubApiUrl());
    }

    @Bean
    public GithubStatusApi githubStatus() {
        return new GithubStatusApi(properties().getGithubStatusApiUrl());
    }

    @Bean
    public MessagingApi messagingApi() {
        return new MessagingApi(properties().getMessagingApiUrl());
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClientBuilder.create().build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient()));
    }
}
