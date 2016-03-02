package com.opencredo.test.config.spring;

import com.opencredo.test.TestWorld;
import com.opencredo.test.config.UiTestProperties;
import com.opencredo.test.config.webdriver.SharedDriver;
import com.opencredo.test.interaction.ui.objects.GoogleSearchPageObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(value = { "classpath:/props-for-ui-tests.properties" })
public class UiTestConfig {
    @Bean
    public TestWorld testWorld() {
        return new TestWorld();
    }

    @Bean
    public SharedDriver driver() {
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
