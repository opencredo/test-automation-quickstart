package com.opencredo.test.security.acceptance.test.config.spring;

import com.opencredo.test.security.acceptance.test.config.TestWorld;
import com.opencredo.test.security.acceptance.test.config.UiTestProperties;
import com.opencredo.test.security.acceptance.test.interaction.objects.GoogleSearchPageObject;
import com.opencredo.test.security.acceptance.test.config.webdriver.SharedDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(value = {"classpath:/props-for-ui-tests.properties"})
public class UiTestConfig {
    @Bean
    public TestWorld testWorld() {
        return new TestWorld();
    }

    @Bean
    public SharedDriver driver() {
        if (properties().isGridEnabled())
            return new SharedDriver(properties().getDefaultBrowser(), properties().getDefaultHubUrl());
        return new SharedDriver();
    }

    @Bean
    public UiTestProperties properties() {
        return new UiTestProperties();
    }

    @Bean
    public GoogleSearchPageObject googleSearchPage() {
        return new GoogleSearchPageObject(
                properties().getApplicationBaseUrl(),
                driver(),
                properties().getSeleniumWaitTimeOutSeconds()
        );
    }
}
