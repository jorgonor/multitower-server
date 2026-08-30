package org.multitower.user.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "USR_USER")
@Data
public class UserEntity {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "NICKNAME", nullable = false, unique = true, length = 63)
    private String nickname;

    @Column(name = "PASSWORD", nullable = true)
    private String password;

    @Column(name = "AUTH_TYPE", nullable = false, length = 31)
    private String authType;

    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAt;

    @Column(name = "LAST_ACCESS", nullable = true)
    private Instant lastAccess;

}
