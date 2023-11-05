package net.sinasoheili.heal.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class UserRepository {

    @PersistenceContext()
    private final EntityManager entityManager;

    public void persistUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        log.info("persist new user with {} info", userEntity);
    }
}
