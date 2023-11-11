package net.sinasoheili.heal.drug;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sinasoheili.heal.utils.EntityState;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrugDto {

    @Null(groups = EntityState.Create.class)
    @NotNull(groups = EntityState.Update.class)
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String userId;
}
