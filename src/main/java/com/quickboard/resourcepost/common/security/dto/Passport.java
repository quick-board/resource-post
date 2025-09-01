package com.quickboard.resourcepost.common.security.dto;

import com.quickboard.resourcepost.common.security.enums.AccountState;
import com.quickboard.resourcepost.common.security.enums.PrincipalType;

public record Passport(
    Long userId,
    String guestId,
    PrincipalType principalType,
    AccountState accountState,
    boolean authenticated
) {
    public static Passport authenticatedPassport(Long userId, PrincipalType principalType, AccountState accountState){
        return new Passport(userId, null, principalType, accountState, true);
    }

    public static Passport unauthenticated(String guestId){
        return new Passport(null, guestId, PrincipalType.ANONYMOUS, null, false);
    }
}
