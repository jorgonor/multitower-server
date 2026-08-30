package org.multitower.user.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.multitower.user.domain.identity.UserId;
import org.multitower.user.domain.valueobject.AuthenticationType;

import java.time.Instant;

public class UserTest {

    @Test
    void testValidateUserWithOwnedAuthenticationTypeRequiresNickName() {
        User user = new User();
        user.setId(UserId.of(1L));
        user.setEmail("username@gmail.com");
        user.setPassword("$2a$12$oe.Scc.2upf2hJ5ZkzI5tO38DNTHJPBTAK7XmVamibWrR/KKcZnCW");
        user.setType(AuthenticationType.OWNED);
        user.setCreatedAt(Instant.now());

        Assertions.assertFalse(user.validate());
    }

    @Test
    void testValidateUserWithOwnedAuthenticationTypeRequiresPassword() {
        User user = new User();
        user.setId(UserId.of(1L));
        user.setNickName("username1");
        user.setEmail("username@gmail.com");
        user.setPassword(null);
        user.setType(AuthenticationType.OWNED);
        user.setCreatedAt(Instant.now());

        Assertions.assertFalse(user.validate());
    }
}
