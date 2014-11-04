package com.opencredo.api.acceptance.test.common;


import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Representation of GitHub service status response
 * for JSON mapping
 */
public class ServiceStatusResponse {
    @JsonProperty("last_updated")
    public String lastUpdated;
    @JsonProperty
    public String status;
}
