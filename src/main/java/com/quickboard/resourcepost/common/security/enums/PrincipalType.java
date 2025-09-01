package com.quickboard.resourcepost.common.security.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PrincipalType {
    USER,
    ADMIN,
    ANONYMOUS;

    @JsonCreator
    public static PrincipalType from(String role){
        try {
            return PrincipalType.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid argument: " + role);
        }
    }


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    public static String[] validRoleName(){
        return new String[]{
            PrincipalType.ADMIN.toString(),
            PrincipalType.USER.toString()
        };
    }
}
