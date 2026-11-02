package org.multitower.user.infrastructure.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "USR_DELETED_USER")
@Data
public class DeletedUserEntity {
    @Id
    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;

    @Column(name = "NICKNAME", nullable = false, length = 63)
    private String nickname;

    @Column(name = "AUTH_TYPE", nullable = false, length = 32)
    private String authType;

    @Column(name = "CREATED_AT", nullable = false)
    private Instant createdAt;

    @Column(name = "LAST_ACCESS", nullable = true)
    private Instant lastAccess;

    @Column(name = "REMOVED_AT", nullable = false)
    private Instant removedAt;
}
