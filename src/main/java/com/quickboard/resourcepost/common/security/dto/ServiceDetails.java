package com.quickboard.resourcepost.common.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ServiceDetails(
    String serviceName,
    String clientRequestPath
) { }
