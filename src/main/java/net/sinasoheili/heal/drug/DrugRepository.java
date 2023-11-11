package net.sinasoheili.heal.drug;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DrugRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public void persist(DrugEntity drugEntity) {
        log.info("try to persist drug with {} info", drugEntity);
        entityManager.persist(drugEntity);
    }
}
