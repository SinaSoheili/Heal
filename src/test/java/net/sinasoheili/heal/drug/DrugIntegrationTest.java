package net.sinasoheili.heal.drug;

import net.sinasoheili.heal.user.UserDto;
import net.sinasoheili.heal.utils.UserTestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DrugIntegrationTest {

    private final String DRUG_BASE_URL = "/api/drug";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserTestUtils userTestUtils;

    @Test
    void When_RegisterNewDrug_Expect_AssignIdToRegisteredDrug() {
        UserDto userDto = userTestUtils.createDummyUserDto();
        userDto = userTestUtils.persistUserDto(userDto);

        DrugDto drugDto = new DrugDto();
        drugDto.setName("drugOne");
        drugDto.setUserId(userDto.getId());

        ResponseEntity<DrugDto> drugDtoResponse = testRestTemplate.postForEntity(DRUG_BASE_URL, drugDto, DrugDto.class);

        assert  drugDtoResponse.getBody() != null;
        assertEquals(HttpStatus.OK, drugDtoResponse.getStatusCode());
        assertNotNull(drugDtoResponse.getBody().getId());
    }
}
