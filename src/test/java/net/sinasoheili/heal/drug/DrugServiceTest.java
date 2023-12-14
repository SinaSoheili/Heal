package net.sinasoheili.heal.drug;

import net.sinasoheili.heal.user.UserEntity;
import net.sinasoheili.heal.user.UserService;
import net.sinasoheili.heal.utils.UserTestUtils;
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

    @Mock
    UserService userService;

    @Test
    void when_RegisterNewDrugWithDrugDto_Expect_RegisterDrugByDrugRepository() {
        UserEntity userEntity = UserTestUtils.createDummyUserEntity();
        userEntity.setId("userId");

        String drugId = "drugId";
        String drugName = "drugName";
        DrugDto drugDto = new DrugDto(null, drugName, userEntity.getId());
        DrugEntity drugEntity = new DrugEntity(null, drugName, userEntity);

        Mockito.doAnswer(invocation -> {
            DrugEntity drugEntityResponse = invocation.getArgument(0, DrugEntity.class);
            drugEntityResponse.setId(drugId);
            return drugEntityResponse;
        }).when(drugRepository).persist(drugEntity);

        Mockito.doReturn(userEntity).when(userService).loadUserByIdInternal(userEntity.getId());

        DrugDto drugDtoResult = drugService.registerNewDrugInternal(drugDto);

        assertEquals(drugDtoResult.getId(), drugId);
    }
}
