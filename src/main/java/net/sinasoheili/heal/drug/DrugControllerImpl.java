package net.sinasoheili.heal.drug;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DrugControllerImpl implements DrugController {

    private final DrugService drugService;

    @Override
    public DrugDto registerDrug(DrugDto drugDto) {
        log.info("received an http request to register drug with {} info", drugDto);
        return drugService.registerNewDrugInternal(drugDto);
    }
}
