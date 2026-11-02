package org.multitower.user.infrastructure.jpa.adapter;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.multitower.user.domain.exception.DuplicatedUserException;
import org.multitower.user.domain.application.service.UserRepository;
import org.multitower.user.domain.entity.User;
import org.multitower.user.domain.identity.UserId;
import org.multitower.user.infrastructure.jpa.entity.DeletedUserEntity;
import org.multitower.user.infrastructure.jpa.entity.UserEntity;
import org.multitower.user.infrastructure.jpa.repository.DeletedUserJpaRepository;
import org.multitower.user.infrastructure.jpa.repository.UserJpaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
public class JpaAdapterUserRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final DeletedUserJpaRepository deletedUserJpaRepository;
    private final UserEntityJpaMapper userEntityJpaMapper;

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.getId())
            .map(userEntityJpaMapper::mapToDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
            .map(userEntityJpaMapper::mapToDomain);
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<DeletedUserEntity> tombstone = deletedUserJpaRepository.findByEmail(user.getEmail());
        if (tombstone.isPresent()) {
            return restoreDeletedUser(tombstone.get(), user);
        }

        UserEntity userEntity = userEntityJpaMapper.mapToJpa(user);

        try {
            UserEntity savedEntity = userJpaRepository.saveAndFlush(userEntity);
            return userEntityJpaMapper.mapToDomain(savedEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedUserException("User with email '" + user.getEmail() + "' already exists.", e);
        }
    }

    @Override
    @Transactional
    public User update(User user) {
        UserEntity userEntity = userJpaRepository.findById(user.getId().getId())
            .orElseThrow(() -> new EntityNotFoundException("User with id '" + user.getId().getId() + "' not found."));
        userEntityJpaMapper.updateEntity(userEntity, user);

        try {
            UserEntity savedEntity = userJpaRepository.saveAndFlush(userEntity);
            return userEntityJpaMapper.mapToDomain(savedEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedUserException("User with email '" + user.getEmail() + "' already exists.", e);
        }
    }

    @Override
    @Transactional
    public void moveUserToTheRecycleBin(User user) {
        UserEntity userEntity = userJpaRepository.findById(user.getId().getId())
            .orElseThrow(() -> new EntityNotFoundException("User with id '" + user.getId().getId() + "' not found."));

        DeletedUserEntity tombstone = userEntityJpaMapper.mapToDeletedEntity(
            userEntityJpaMapper.mapToDomain(userEntity), Instant.now());
        deletedUserJpaRepository.saveAndFlush(tombstone);
        userJpaRepository.delete(userEntity);
    }

    @Override
    public User restoreUser(User user) {
        throw new IllegalStateException("Not implemented yet.");
    }

    private User restoreDeletedUser(DeletedUserEntity tombstone, User user) {
        UserEntity restoredEntity = userEntityJpaMapper.mapToRestoredEntity(tombstone, user);
        deletedUserJpaRepository.delete(tombstone);

        try {
            UserEntity savedEntity = userJpaRepository.saveAndFlush(restoredEntity);
            return userEntityJpaMapper.mapToDomain(savedEntity);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedUserException("User with email '" + user.getEmail() + "' already exists.", e);
        }
    }

}
