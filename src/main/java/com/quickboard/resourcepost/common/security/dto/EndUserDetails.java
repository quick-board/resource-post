package com.quickboard.resourcepost.common.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quickboard.resourcepost.common.security.enums.AccountState;
import com.quickboard.resourcepost.common.security.enums.Role;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EndUserDetails(
        Long accountId,
        AccountState accountState,
        Role role,
        Long profileId,
        String nickname
) { }
