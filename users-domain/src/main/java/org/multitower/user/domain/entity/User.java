package org.multitower.user.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.multitower.user.domain.identity.UserId;
import org.multitower.user.domain.valueobject.AuthenticationType;

import java.time.Instant;

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

    public boolean validate() {

        if (type == null || createdAt == null || (nickName == null || nickName.isEmpty())) {
            return false;
        }

        if (type == AuthenticationType.OWNED) {
            return password != null;
        } else {
            throw new IllegalStateException("AuthenticationType not implemented yet.");
        }
    }
}
