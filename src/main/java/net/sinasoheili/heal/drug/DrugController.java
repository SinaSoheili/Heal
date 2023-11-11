package net.sinasoheili.heal.drug;

import jakarta.validation.Valid;
import net.sinasoheili.heal.utils.EntityState;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/drug")
@Validated
public interface DrugController {

    /**
     * Register new drug for specified user
     *
     * @param drugDto new drug information
     * @return Information of registered drug
     */
    @PostMapping
    @Validated(EntityState.Create.class)
    DrugDto registerDrug(@Valid @RequestBody DrugDto drugDto);
}
