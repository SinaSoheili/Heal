package net.sinasoheili.heal.drug;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DrugControllerImplTest {

    @InjectMocks
    DrugControllerImpl drugController;

    @Mock
    DrugService drugService;

    @Test
    void When_registerDrug_Expect_CallRegisterDrugInServiceLayerWithPassedDrugDto() {
        DrugDto drugDto = new DrugDto();
        drugDto.setName("drug name");
        drugDto.setUserId("user Id");

        drugController.registerDrug(drugDto);

        Mockito.verify(drugService).registerNewDrugInternal(drugDto);
    }
}
