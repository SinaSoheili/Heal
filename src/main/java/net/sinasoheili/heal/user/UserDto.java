package net.sinasoheili.heal.user;

import jakarta.validation.constraints.*;
import lombok.Data;
import net.sinasoheili.heal.utils.EntityState;

@Data
public class UserDto {

    @Null(groups = EntityState.Create.class)
    @NotBlank(groups = EntityState.Update.class)
    private String id;

    @NotBlank
    private String username;

    @NotBlank(groups = EntityState.Create.class)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Size(min = 11, max = 11)
    private String phoneNumber;

    @NotNull
    private Gender gender;

    @Min(1)
    @Max(100)
    private int age;

    @Min(50)
    @Max(300)
    private int height;
}
