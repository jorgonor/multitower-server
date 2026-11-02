package org.multitower.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.multitower.user.domain.identity.UserId;
import org.multitower.user.domain.valueobject.AuthenticationType;
import org.multitower.user.domain.valueobject.UserValidationError;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    /**
     * The entity identity
     */
    UserId id;
    /**
     * The user name used to login
     */
    String email;
    /**
     * The nickname visible for other users
     */
    String nickName;
    /**
     * The encrypted user password
     */
    String password;
    /**
     * The type of authentication.
     */
    @NonNull
    AuthenticationType type;
    @NonNull
    Instant createdAt;
    Instant lastAccess;
    Instant removedAt;

    /**
     * Validates the entity state and collects the validation errors found.
     * @return the list of validation errors, empty when the state is valid
     */
    public List<UserValidationError> validate() {
        List<UserValidationError> validationErrors = new ArrayList<>();

        if (type == null) {
            validationErrors.add(UserValidationError.MISSING_AUTHENTICATION_TYPE);
        }
        if (createdAt == null) {
            validationErrors.add(UserValidationError.MISSING_CREATED_AT);
        }
        if (nickName == null || nickName.isEmpty()) {
            validationErrors.add(UserValidationError.MISSING_NICK_NAME);
        }

        if (type == null) {
            return validationErrors;
        }

        if (type == AuthenticationType.OWNED) {
            if (password == null) {
                validationErrors.add(UserValidationError.MISSING_PASSWORD);
            }
        } else {
            throw new IllegalStateException("AuthenticationType not implemented yet.");
        }

        return validationErrors;
    }
}
