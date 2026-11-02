package org.multitower.user.domain.exception;

/**
 * Thrown when persisting a user that violates a uniqueness constraint
 * (e.g. duplicated email or nickname).
 */
public class DuplicatedUserException extends UserException {

    public DuplicatedUserException(String message) {
        super(message);
    }

    public DuplicatedUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
