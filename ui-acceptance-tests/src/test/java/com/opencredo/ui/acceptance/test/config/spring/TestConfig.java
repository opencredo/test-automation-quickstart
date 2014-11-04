package com.opencredo.ui.acceptance.test.config.spring;

import com.opencredo.ui.acceptance.test.interaction.objects.GoogleSearchPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.opencredo.ui.acceptance.test.config.webdriver.SharedDriver;
import com.opencredo.ui.acceptance.test.config.TestProperties;
import com.opencredo.ui.acceptance.test.common.World;
import com.opencredo.ui.acceptance.test.interaction.objects.GoogleSearchPage;

/**
 * Spring configuration for autowired objects
 */
@Configuration
@PropertySource(value = { "classpath:/props-for-ui-tests.properties" })
public class TestConfig {
    @Bean
    public World world() {
        return new World();
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
    public GoogleSearchPage googleSearchPage() {
        return new GoogleSearchPage(
                properties().getApplicationBaseUrl(),
                driver(),
                properties().getSeleniumWaitTimeOutSeconds()
        );
    }
}
