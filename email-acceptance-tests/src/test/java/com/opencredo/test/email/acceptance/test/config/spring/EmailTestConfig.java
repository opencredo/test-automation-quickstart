package com.opencredo.test.email.acceptance.test.config.spring;

import com.opencredo.test.EmailAdaptor;
import com.opencredo.test.email.acceptance.test.config.EmailTestProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:/props-for-email-tests.properties"})
public class EmailTestConfig {

    @Bean
    public EmailTestProperties properties() {
        return new EmailTestProperties();
    }

    @Bean
    public EmailAdaptor emailAdaptor() {
        return new EmailAdaptor(properties().getEmailAddress(), properties().getEmailPassword());
    }

}
