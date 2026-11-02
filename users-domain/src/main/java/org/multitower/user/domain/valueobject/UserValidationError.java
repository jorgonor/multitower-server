package org.multitower.user.domain.valueobject;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Validation errors that a {@code User} entity can report.
 */
@Getter
@RequiredArgsConstructor
public enum UserValidationError {
    MISSING_AUTHENTICATION_TYPE("AUTH_TYPE_MISSING", "The authentication type is missing."),
    MISSING_CREATED_AT("CREATED_AT_MISSING", "The creation timestamp is missing."),
    MISSING_NICK_NAME("NICK_NAME_MISSING", "The nickname is required."),
    MISSING_PASSWORD("PASSWORD_MISSING", "The password is required for owned authentication.");

    private final String code;
    private final String description;
}
