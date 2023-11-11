package net.sinasoheili.heal.drug;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DrugServiceTest {

    @InjectMocks
    DrugService drugService;

    @Mock
    DrugRepository drugRepository;

    @Test
    void when_RegisterNewDrugWithDrugDto_Expect_RegisterDrugByDrugRepository() {
        String drugId = "drugId";
        String drugName = "drugName";
        String userId = "userId";
        DrugDto drugDto = new DrugDto(null, drugName, userId);
        DrugEntity drugEntity = new DrugEntity(null, drugName, null);

        Mockito.doAnswer(invocation -> {
            DrugEntity drugEntityResponse = invocation.getArgument(0, DrugEntity.class);
            drugEntityResponse.setId(drugId);
            return drugEntityResponse;
        }).when(drugRepository).persist(drugEntity);

        DrugDto drugDtoResult = drugService.registerNewDrugInternal(drugDto);
        assertEquals(drugDtoResult.getId(), drugId);
    }
}
