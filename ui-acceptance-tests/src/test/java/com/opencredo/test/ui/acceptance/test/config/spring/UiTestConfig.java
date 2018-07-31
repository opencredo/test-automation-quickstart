package com.opencredo.test.ui.acceptance.test.config.spring;

import com.opencredo.test.ui.acceptance.test.interaction.objects.GoogleSearchPageObject;
import com.opencredo.test.ui.acceptance.test.config.TestWorld;
import com.opencredo.test.ui.acceptance.test.config.UiTestProperties;
import com.opencredo.test.ui.acceptance.test.config.webdriver.SharedDriver;
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
        return new SharedDriver(properties().getBrowser());
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
