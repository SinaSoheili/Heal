package net.sinasoheili.heal.user;

import jakarta.validation.Valid;
import net.sinasoheili.heal.utils.EntityState;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${heal.api.prefix}/user")
@Validated
public interface UserController {

    @PostMapping
    @Validated(EntityState.Create.class)
    UserDto registerUser(@Valid @RequestBody UserDto userDto);

}
