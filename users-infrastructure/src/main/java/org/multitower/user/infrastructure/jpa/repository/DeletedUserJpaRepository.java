package org.multitower.user.infrastructure.jpa.repository;

import org.multitower.user.infrastructure.jpa.entity.DeletedUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeletedUserJpaRepository extends JpaRepository<DeletedUserEntity, Long> {
    Optional<DeletedUserEntity> findByEmail(String email);
}
