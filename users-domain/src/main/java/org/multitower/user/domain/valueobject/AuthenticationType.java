package org.multitower.user.domain.valueobject;

import lombok.Getter;

@Getter
public enum AuthenticationType {
    OWNED("e-mail account"),
    GOOGLE("Google account");

    private String publicName;

    AuthenticationType(String publicName) {
        this.publicName = publicName;
    }
}
