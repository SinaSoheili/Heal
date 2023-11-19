package net.sinasoheili.heal.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    UserRepository userRepository;

    @BeforeEach
    void initUserRepository() {
        userRepository = new UserRepository(testEntityManager.getEntityManager());
    }

    @Test
    void When_persistUser_Expect_persistUserOnDBAndSetNewIdToUser() {
        UserEntity userEntity = createDummyUserEntity("firstName", "lastName", 23, 180, Gender.MALE, "09121111111");

        userRepository.persistUser(userEntity);
        testEntityManager.flush();

        assertNotNull(userEntity.getId());
        UserEntity persistedUserEntity = testEntityManager.find(UserEntity.class, userEntity.getId());
        assertEquals(userEntity, persistedUserEntity);
    }

    @Test
    void When_LoadUserByIdAndUserIsNotAvailable_Expect_ReturnAnOptionalWithNullContent() {
        Optional<UserEntity> userEntityOptional = userRepository.loadUserById("someString");
        assertFalse(userEntityOptional.isPresent());
    }

    @Test
    void When_LoadUserByIdAndUserIsAvailable_Expect_ReturnUserInfo() {
        UserEntity userEntity = createDummyUserEntity("firstName", "lastName", 23, 100, Gender.MALE, "09121111111");
        testEntityManager.persist(userEntity);

        Optional<UserEntity> userEntityOptional = userRepository.loadUserById(userEntity.getId());

        assertTrue(userEntityOptional.isPresent());
        assertEquals(userEntity, userEntityOptional.get());
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

}
