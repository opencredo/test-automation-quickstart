package com.opencredo.api.acceptance.test.common;


import org.codehaus.jackson.annotate.JsonProperty;

public class ServiceStatusResponse {
    @JsonProperty("last_updated")
    public String lastUpdated;
    @JsonProperty
    public String status;
}
