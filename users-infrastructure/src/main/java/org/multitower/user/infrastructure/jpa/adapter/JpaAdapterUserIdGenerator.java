package org.multitower.user.infrastructure.jpa.adapter;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.multitower.user.domain.application.service.UserIdGenerator;
import org.multitower.user.domain.identity.UserId;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAdapterUserIdGenerator implements UserIdGenerator {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserId generateUserId() {
        String sql = "SELECT nextval('SEQ_USER')";
        Long nextVal = (Long) entityManager.createNativeQuery(sql, Long.class).getSingleResult();

        return UserId.of(nextVal);
    }

}
