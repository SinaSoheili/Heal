package net.sinasoheili.heal.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void When_RegisterUserByDTO_Expect_ConvertDtoToEntityAndRegisterEntityByCallRegisterUserInternal() {
        String expectUserId = "1";
        UserDto userDto = new UserDto();
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPhoneNumber("09121111111");
        userDto.setHeight(123);
        userDto.setGender(Gender.MALE);
        userDto.setAge(20);

        Mockito.doAnswer(invocation -> {
            UserEntity answerUserEntity = invocation.getArgument(0, UserEntity.class);
            answerUserEntity.setId(expectUserId);
            return answerUserEntity;
        }).when(userRepository).persistUser(Mockito.any());

        UserDto resultUserDto = userService.registerUser(userDto);
        assertEquals(expectUserId, resultUserDto.getId());
    }

    @Test
    void When_RegisterUserInternal_Expect_PersistUserInRepository() {
        String expectUserId = "1";
        UserEntity userEntity = createDummyUserEntity();

        Mockito.doAnswer(invocation -> {
            UserEntity userEntityResponse = invocation.getArgument(0, UserEntity.class);
            userEntityResponse.setId(expectUserId);
            return userEntityResponse;
        }).when(userRepository).persistUser(userEntity);

        userService.registerUserInternal(userEntity);

        Mockito.verify(userRepository).persistUser(userEntity);
        assertEquals(expectUserId, userEntity.getId());
    }

    @Test
    void When_LoadUserById_Expect_LoadUserEntityByIdAndConvertToUserDto() {
        String userId = "1";
        UserEntity userEntity = createDummyUserEntity();
        userEntity.setId(userId);

        Mockito.doReturn(Optional.of(userEntity)).when(userRepository).loadUserById(userId);

        UserDto userDto = userService.loadUserById(userId);

        assertEquals(userEntity.getFirstName(), userDto.getFirstName());
        assertEquals(userEntity.getLastName(), userDto.getLastName());
        assertEquals(userEntity.getId(), userDto.getId());
    }

    @Test
    void When_LoadUserByIdInternal_Expect_LoadUserFromRepository() {
        String userId = "1";
        UserEntity userEntity = createDummyUserEntity();
        userEntity.setId(userId);

        Mockito.doReturn(Optional.of(userEntity)).when(userRepository).loadUserById(userId);

        UserEntity userEntityResponse = userService.loadUserByIdInternal(userId);
        assertEquals(userEntity, userEntityResponse);
    }

    @Test
    void When_LoadUserByIdInternalAndUserNotExist_Expect_ThrowUserNotFoundException() {
        String userId = "1";
        assertThrows(UserNotFoundException.class, () -> userService.loadUserByIdInternal(userId));
    }

    private UserEntity createDummyUserEntity() {
        UserEntity userEntity= new UserEntity();
        userEntity.setFirstName("firstName");
        userEntity.setLastName("lastName");
        userEntity.setPhoneNumber("09121111111");
        userEntity.setHeight(123);
        userEntity.setGender(Gender.MALE);
        userEntity.setAge(20);
        return userEntity;
    }
}
