package org.multitower.user.infrastructure.jpa.adapter;

import lombok.RequiredArgsConstructor;
import org.multitower.user.domain.application.service.UserRepository;
import org.multitower.user.domain.entity.User;
import org.multitower.user.domain.identity.UserId;
import org.multitower.user.infrastructure.jpa.entity.UserEntity;
import org.multitower.user.infrastructure.jpa.repository.UserJpaRepository;

import java.util.Optional;

@RequiredArgsConstructor
public class JpaAdapterUserRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
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
    public User save(User user) {
        UserEntity userEntity = userEntityJpaMapper.mapToJpa(user);
        UserEntity savedEntity;

        savedEntity = userJpaRepository.save(userEntity);

        return userEntityJpaMapper.mapToDomain(savedEntity);
    }

    @Override
    public User update(User user) {
        throw new IllegalStateException("Not implemented yet.");
    }

    @Override
    public void moveUserToTheRecycleBin(User user) {
        throw new IllegalStateException("Not implemented yet.");
    }

    @Override
    public User restoreUser(User user) {
        throw new IllegalStateException("Not implemented yet.");
    }

}
