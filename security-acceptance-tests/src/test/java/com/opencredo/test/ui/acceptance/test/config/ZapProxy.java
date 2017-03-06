package com.opencredo.test.ui.acceptance.test.config;

public class ZapProxy {
    private final String proxyHost;
    private final String proxyPort;

    public ZapProxy(String proxyHost, String proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

}
