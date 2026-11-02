package org.multitower.user.infrastructure.jpa.adapter;

import org.multitower.user.domain.entity.User;
import org.multitower.user.domain.identity.UserId;
import org.multitower.user.domain.valueobject.AuthenticationType;
import org.multitower.user.infrastructure.jpa.entity.DeletedUserEntity;
import org.multitower.user.infrastructure.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class UserEntityJpaMapper {
    public User mapToDomain(UserEntity userEntity) {
        return User.builder()
            .id(UserId.of(userEntity.getUserId()))
            .email(userEntity.getEmail())
            .nickName(userEntity.getNickname())
            .password(userEntity.getPassword())
            .type(AuthenticationType.valueOf(userEntity.getAuthType())) // Assuming Enum conversion
            .createdAt(userEntity.getCreatedAt())
            .lastAccess(userEntity.getLastAccess())
            .build();
    }

    public UserEntity mapToJpa(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getId().getId());
        userEntity.setEmail(user.getEmail());
        userEntity.setNickname(user.getNickName());
        userEntity.setPassword(user.getPassword());
        userEntity.setAuthType(user.getType().name());
        userEntity.setCreatedAt(user.getCreatedAt());
        userEntity.setLastAccess(user.getLastAccess());

        return userEntity;
    }

    public void updateEntity(UserEntity userEntity, User user) {
        userEntity.setEmail(user.getEmail());
        userEntity.setNickname(user.getNickName());
        userEntity.setPassword(user.getPassword());
        userEntity.setAuthType(user.getType().name());
        userEntity.setLastAccess(user.getLastAccess());
    }

    public DeletedUserEntity mapToDeletedEntity(User user, Instant removedAt) {
        DeletedUserEntity deletedUserEntity = new DeletedUserEntity();
        deletedUserEntity.setUserId(user.getId().getId());
        deletedUserEntity.setEmail(user.getEmail());
        deletedUserEntity.setNickname(user.getNickName());
        deletedUserEntity.setAuthType(user.getType().name());
        deletedUserEntity.setCreatedAt(user.getCreatedAt());
        deletedUserEntity.setLastAccess(user.getLastAccess());
        deletedUserEntity.setRemovedAt(removedAt);

        return deletedUserEntity;
    }

    public UserEntity mapToRestoredEntity(DeletedUserEntity tombstone, User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(tombstone.getUserId());
        userEntity.setCreatedAt(tombstone.getCreatedAt());
        userEntity.setEmail(user.getEmail());
        userEntity.setNickname(user.getNickName());
        userEntity.setPassword(user.getPassword());
        userEntity.setAuthType(user.getType().name());
        userEntity.setLastAccess(user.getLastAccess());

        return userEntity;
    }
}
