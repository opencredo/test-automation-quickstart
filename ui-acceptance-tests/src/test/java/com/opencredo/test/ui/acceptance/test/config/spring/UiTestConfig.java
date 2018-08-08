package com.opencredo.test.ui.acceptance.test.config.spring;

import com.opencredo.test.LocalDriver;
import com.opencredo.test.SeleniumHubDriver;
import com.opencredo.test.ui.acceptance.test.config.TestWorld;
import com.opencredo.test.ui.acceptance.test.config.UiTestProperties;
import com.opencredo.test.ui.acceptance.test.interaction.objects.GoogleSearchPageObject;
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

    private LocalDriver localDriver() {
        return new LocalDriver(properties().getBrowser(), false, null);
    }

    private SeleniumHubDriver hubDriver() {
        return new SeleniumHubDriver(properties().getBrowser(), properties().getHubUrl());
    }

    @Bean
    public UiTestProperties properties() {
        return new UiTestProperties();
    }

    @Bean
    public GoogleSearchPageObject googleSearchPage() {
        return new GoogleSearchPageObject(
                properties().getApplicationBaseUrl(),
                localDriver(),
                properties().getSeleniumWaitTimeOutSeconds()
        );
    }
}
