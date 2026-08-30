package org.multitower.user.domain.application.service;

import org.multitower.user.domain.entity.User;
import org.multitower.user.domain.identity.UserId;

import java.util.Optional;

/**
 * Repository interface to read, write and update
 */
public interface UserRepository {
    /**
     * Finds a user by its id
     * @param userId The user id
     * @return       A user entity
     */
    Optional<User> findById(UserId userId);

    /**
     * Finds a user by its email
     * @param email The user email
     * @return      A user entity
     */
    Optional<User> findByEmail(String email);
    /**
     * Persist the user state into the storage
     * @param user The user entity
     * @return     The saved user entity
     */
    User save(User user);
    /**
     * Updates the user state into the storage
     * @param user The user entity
     * @return     The updated user entity
     */
    User update(User user);

    /**
     * Moves a user to the recycle bin
     * @param user The user entity to move to the recycle bin
     */
    void moveUserToTheRecycleBin(User user);

    /**
     * Restores a user that has been deleted previously
     * @param user The user entity into the recycle bin
     * @return     The restored user entity
     */
    User restoreUser(User user);
}
