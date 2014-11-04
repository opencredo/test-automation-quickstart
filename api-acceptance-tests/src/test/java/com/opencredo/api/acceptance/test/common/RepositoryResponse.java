package com.opencredo.api.acceptance.test.common;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryResponse {
    @JsonProperty
    public String id;
    @JsonProperty
    public String name;
    @JsonProperty ("full_name")
    public String fullName;
    //etc
}
