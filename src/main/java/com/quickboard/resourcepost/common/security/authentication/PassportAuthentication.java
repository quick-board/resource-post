package com.quickboard.resourcepost.common.security.authentication;

import com.quickboard.resourcepost.common.security.dto.Passport;
import com.quickboard.resourcepost.common.security.enums.PrincipalType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class PassportAuthentication implements Authentication {

    private final Passport passport;
    private boolean authenticated;
    private final List<GrantedAuthority> authorities;

    private PassportAuthentication(Passport passport, boolean authenticated) {
        this.passport = passport;
        this.authenticated = authenticated;
        authorities = List.of(new SimpleGrantedAuthority("ROLE_" + this.passport.principalType()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return passport;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {

        if(passport.principalType() == PrincipalType.ADMIN){
            return "admin:" + passport.guestId();
        }
        else if (passport.principalType() == PrincipalType.USER) {
            return "user:" + passport.userId();
        }

        return "anonymous:" + passport.guestId();
    }

    public static PassportAuthentication authenticated(Passport passport){
        return new PassportAuthentication(passport, true);
    }

}
