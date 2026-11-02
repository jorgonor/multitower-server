package org.multitower.auth.server.config;

import org.multitower.user.domain.application.service.UserRepository;
import org.multitower.user.infrastructure.jpa.adapter.JpaAdapterUserRepository;
import org.multitower.user.infrastructure.jpa.adapter.UserEntityJpaMapper;
import org.multitower.user.infrastructure.jpa.repository.DeletedUserJpaRepository;
import org.multitower.user.infrastructure.jpa.repository.UserJpaRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "org.multitower.user.infrastructure.jpa")
@EntityScan(basePackages = "org.multitower.user.infrastructure.jpa.entity")
public class JpaAuthServerConfiguration {

    @Bean
    UserRepository userRepository(UserJpaRepository userJpaRepository, DeletedUserJpaRepository deletedUserJpaRepository, UserEntityJpaMapper userEntityJpaMapper) {
        return new JpaAdapterUserRepository(userJpaRepository, deletedUserJpaRepository, userEntityJpaMapper);
    }
}
