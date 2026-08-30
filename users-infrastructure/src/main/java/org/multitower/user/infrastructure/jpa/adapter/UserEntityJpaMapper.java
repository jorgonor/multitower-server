package org.multitower.user.infrastructure.jpa.adapter;

import org.multitower.user.domain.entity.User;
import org.multitower.user.domain.identity.UserId;
import org.multitower.user.domain.valueobject.AuthenticationType;
import org.multitower.user.infrastructure.jpa.entity.UserEntity;
import org.springframework.stereotype.Component;

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
}
