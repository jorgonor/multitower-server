package org.multitower.auth.server.adapter.security;

import lombok.Value;
import org.multitower.user.domain.valueobject.AuthenticationType;

import java.util.List;

@Value
public class CustomUserDetails extends org.springframework.security.core.userdetails.User {

    String nickname;
    AuthenticationType type;

    public CustomUserDetails(String username, String password, String nickname, AuthenticationType type) {
        this(username, password, true, true, true, true, nickname, type);
    }

    public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, String nickname, AuthenticationType type) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, List.of());
        this.nickname = nickname;
        this.type = type;
    }
}