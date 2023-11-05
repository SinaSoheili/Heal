package net.sinasoheili.heal.user;

import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import net.sinasoheili.heal.utils.EntityState;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

@Path("/user")
@Validated
public interface UserController {

    @POST
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Validated(EntityState.Create.class)
    UserDto registerUser(@Valid UserDto userDto);

}
