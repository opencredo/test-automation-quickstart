package com.opencredo.test.api.acceptance.test.interaction.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    public String id;
    public String name;
    public String full_name;
}
