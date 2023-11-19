package net.sinasoheili.heal.drug;

import net.sinasoheili.heal.user.Gender;
import net.sinasoheili.heal.user.UserEntity;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DrugRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    DrugRepository drugRepository;

    @BeforeEach
    void initDrugRepository() {
        drugRepository = new DrugRepository(testEntityManager.getEntityManager());
    }

    @Test
    void When_PersistDrug_Expect_PersistDrugOnDBAndAssignNewIdToDrug() {
        UserEntity userEntity = createDummyUserEntity("firstName", "lastName", 23, 100, Gender.MALE, "09121111111");
        testEntityManager.persist(userEntity);

        DrugEntity drugEntity = createDummyDrug("drugName", userEntity);

        drugRepository.persist(drugEntity);

        assertNotNull(drugEntity.getId());
    }

    @Test
    void When_RegisterDrugWithoutUser_Expect_ThrowException() {
        DrugEntity drugEntity = createDummyDrug("drugName", null);

        assertThrows(ConstraintViolationException.class, () -> {
            drugRepository.persist(drugEntity);
            testEntityManager.flush();
        });
    }

    private UserEntity createDummyUserEntity(String firstName, String lastName, int age, int height, Gender gender, String phoneNumber) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setAge(age);
        userEntity.setHeight(height);
        userEntity.setGender(gender);
        userEntity.setPhoneNumber(phoneNumber);
        return userEntity;
    }

    private DrugEntity createDummyDrug(String drugName, UserEntity userEntity) {
        DrugEntity drugEntity = new DrugEntity();
        drugEntity.setName(drugName);
        drugEntity.setUser(userEntity);
        return drugEntity;
    }
}
