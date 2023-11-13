package net.sinasoheili.heal.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

    @InjectMocks
    UserControllerImpl userControllerImpl;

    @Mock
    UserService userService;

    @Test
    void When_RegisterNewUser_Expect_CallRegisterUserFunctionInServiceLayer() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("user name");
        userDto.setLastName("user last name");
        userDto.setAge(23);
        userDto.setGender(Gender.MALE);
        userDto.setHeight(185);
        userDto.setPhoneNumber("09121111111");

        userControllerImpl.registerUser(userDto);

        Mockito.verify(userService).registerUser(userDto);
    }

    @Test
    void When_LoadUserById_Expect_CallLoadUserByIdFunctionInServiceLayer() {
        String userId = "1234";
        userControllerImpl.loadUserById(userId);

        Mockito.verify(userService).loadUserById(userId);
    }

}
