package com.devpimentel.restapi.restapi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserSystem extends User {

    private static final long serialVersionUID = 1L;

    private com.devpimentel.restapi.restapi.model.User user;

    public UserSystem(com.devpimentel.restapi.restapi.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public com.devpimentel.restapi.restapi.model.User getUser() {
        return user;
    }
}
