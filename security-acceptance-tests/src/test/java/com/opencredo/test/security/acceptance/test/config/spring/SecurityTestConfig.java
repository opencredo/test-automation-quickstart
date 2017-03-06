package com.opencredo.test.security.acceptance.test.config.spring;

import com.opencredo.test.security.acceptance.test.config.SecurityTestProperties;
import com.opencredo.test.security.acceptance.test.config.TestWorld;
import com.opencredo.test.security.acceptance.test.config.webdriver.SharedDriver;
import com.opencredo.test.security.acceptance.test.interaction.objects.GoogleSearchPageObject;
import com.opencredo.test.security.acceptance.test.config.ZapProxy;
import org.openqa.selenium.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.zaproxy.clientapi.core.ClientApi;


@Configuration
@PropertySource(value = {"classpath:/props-for-security-tests.properties"})
public class SecurityTestConfig {
    @Bean
    public SecurityTestProperties properties() {
        return new SecurityTestProperties();
    }

    @Bean
    public TestWorld testWorld() {
        return new TestWorld();
    }

    @Bean
    public ZapProxy zapProxy() {
        return new ZapProxy(properties().getProxyUrl(), properties().getProxyPort());
    }

    @Bean
    public Proxy webDriverProxy() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(zapProxy().getProxyHost() + ":" + zapProxy().getProxyPort());
        proxy.setSslProxy(zapProxy().getProxyHost() + ":" + zapProxy().getProxyPort());
        return proxy;
    }

    @Bean
    public SharedDriver driver() {
        return new SharedDriver(properties().isProxyEnabled(), webDriverProxy());
    }

    @Bean
    public GoogleSearchPageObject googleSearchPage() {
        return new GoogleSearchPageObject(
                properties().getApplicationBaseUrl(),
                driver(),
                properties().getSeleniumWaitTimeOutSeconds()
        );
    }

    @Bean
    public ClientApi clientApi() {
        return new ClientApi(zapProxy().getProxyHost(), Integer.valueOf(zapProxy().getProxyPort()));
    }
}
