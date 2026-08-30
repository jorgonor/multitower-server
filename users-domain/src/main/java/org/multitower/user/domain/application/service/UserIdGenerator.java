package org.multitower.user.domain.application.service;

import org.multitower.user.domain.identity.UserId;

/**
 * Interface to generate unique user entity identifiers
 */
public interface UserIdGenerator {
    /**
     * Generates a new unique user identifier
     * @return A unique user entity identity
     */
    UserId generateUserId();
}
