package net.sinasoheili.heal.user;

import net.sinasoheili.heal.utils.UserTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserIntegrationTest {

    private final String USER_SERVICE_BASE_URL = "/api/user";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserTestUtils userTestUtils;

    @BeforeEach
    void clearDataBase() {
        userTestUtils.clearUserTable();
    }

    @Test
    void When_RegisterNewUser_Expect_AssignNewIdToUser() {
        UserDto newUserDto = userTestUtils.createDummyUserDto();

        ResponseEntity<UserDto> responseEntity = testRestTemplate.postForEntity(USER_SERVICE_BASE_URL, newUserDto, UserDto.class);

        UserDto responseUserDto = responseEntity.getBody();
        assertNotNull(responseUserDto.getId());
    }

    @Test
    void When_LoadUserByIdAndUserExistInDB_Expect_LoadUserInfo() {
        UserDto newUserDto = userTestUtils.createDummyUserDto();
        newUserDto = userTestUtils.persistUserDto(newUserDto);

        ResponseEntity<UserDto> responseEntity = testRestTemplate.getForEntity(USER_SERVICE_BASE_URL + "/" + newUserDto.getId(), UserDto.class);

        UserDto responseUserDto = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newUserDto.getFirstName(), responseUserDto.getFirstName());
    }
}
