package net.sinasoheili.heal.utils;

import lombok.RequiredArgsConstructor;
import net.sinasoheili.heal.user.Gender;
import net.sinasoheili.heal.user.UserDto;
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

    @Transactional
    public UserDto persistUserDto(UserDto userDto) {
        return userService.registerUser(userDto);
    }
}
