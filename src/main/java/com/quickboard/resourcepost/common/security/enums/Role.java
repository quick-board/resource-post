package com.quickboard.resourcepost.common.security.enums;

public enum Role {
    USER,
    ADMIN;

    public static Role from(String role){
        try {
            return Role.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid argument: " + role);
        }
    }


    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
