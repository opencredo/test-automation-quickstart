package com.opencredo.test.interaction.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceStatus {
    public String last_updated;
    public String status;
}
