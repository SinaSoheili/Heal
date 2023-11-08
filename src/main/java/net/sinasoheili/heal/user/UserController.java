package net.sinasoheili.heal.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import net.sinasoheili.heal.utils.EntityState;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("${heal.api.prefix}/user")
@Validated
public interface UserController {

    @PostMapping
    @Validated(EntityState.Create.class)
    UserDto registerUser(@Valid @RequestBody UserDto userDto);

    /**
     * Load user with specified id
     * @param userId unique user identifier
     * @return {@link UserDto} user information with specified userId.
     * @throws UserNotFoundException Throw exception if did not find any user with specified userId and translate to
     * 404 (NotFound) Http Status code.
     */
    @GetMapping("/{userId}")
    UserDto loadUserById(@NotNull @PathVariable String userId);

}
