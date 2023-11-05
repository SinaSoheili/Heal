package net.sinasoheili.heal.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public UserDto registerUser(UserDto userDto) {
        log.info("receive an http request to register user with {} details", userDto);
        return userService.registerUser(userDto);
    }
}
