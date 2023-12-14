package net.sinasoheili.heal.utils;

import lombok.RequiredArgsConstructor;
import net.sinasoheili.heal.user.Gender;
import net.sinasoheili.heal.user.UserDto;
import net.sinasoheili.heal.user.UserEntity;
import net.sinasoheili.heal.user.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserTestUtils {

    private final UserService userService;

    public UserDto createDummyUserDto() {
        UserDto newUserDto = new UserDto();
        newUserDto.setFirstName("firstName");
        newUserDto.setLastName("lastName");
        newUserDto.setPhoneNumber("09123335555");
        newUserDto.setGender(Gender.MALE);
        newUserDto.setAge(23);
        newUserDto.setHeight(185);
        return newUserDto;
    }

    public static UserEntity createDummyUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("userFirstName");
        userEntity.setLastName("userLastName");
        userEntity.setPhoneNumber("09121111111");
        return userEntity;
    }

    @Transactional
    public UserDto persistUserDto(UserDto userDto) {
        return userService.registerUser(userDto);
    }

    @Transactional
    public int clearUserTable() {
        return userService.deleteAll();
    }
}
