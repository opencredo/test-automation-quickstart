package com.opencredo.ui.acceptance.test.config.spring;

import com.opencredo.ui.acceptance.test.interaction.objects.GoogleSearchPageObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.opencredo.ui.acceptance.test.config.webdriver.SharedDriver;
import com.opencredo.ui.acceptance.test.config.TestProperties;
import com.opencredo.ui.acceptance.test.common.TestWorld;

/**
 * Spring configuration for autowired objects
 */
@Configuration
@PropertySource(value = { "classpath:/props-for-ui-tests.properties" })
public class TestConfig {
    @Bean
    public TestWorld testWorld() {
        return new TestWorld();
    }

    @Bean
    public SharedDriver driver() {
        return new SharedDriver();
    }

    @Bean
    public TestProperties properties() {
        return new TestProperties();
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
