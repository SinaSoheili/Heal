package net.sinasoheili.heal.user;

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
    private UserService userService;

    @BeforeEach
    void clearDataBase() {
        userService.deleteAll();
    }

    @Test
    void When_RegisterNewUser_Expect_AssignNewIdToUser() {
        UserDto newUserDto = createDummyUserDto();

        ResponseEntity<UserDto> responseEntity = testRestTemplate.postForEntity(USER_SERVICE_BASE_URL, newUserDto, UserDto.class);

        UserDto responseUserDto = responseEntity.getBody();
        assertNotNull(responseUserDto.getId());
    }

    @Test
    void When_LoadUserByIdAndUserExistInDB_Expect_LoadUserInfo() {
        UserDto newUserDto = createDummyUserDto();
        newUserDto = userService.registerUser(newUserDto);

        ResponseEntity<UserDto> responseEntity = testRestTemplate.getForEntity(USER_SERVICE_BASE_URL + "/" + newUserDto.getId(), UserDto.class);

        UserDto responseUserDto = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newUserDto.getFirstName(), responseUserDto.getFirstName());
    }

    private UserDto createDummyUserDto() {
        UserDto newUserDto = new UserDto();
        newUserDto.setFirstName("firstName");
        newUserDto.setLastName("lastName");
        newUserDto.setPhoneNumber("09123335555");
        newUserDto.setGender(Gender.MALE);
        newUserDto.setAge(23);
        newUserDto.setHeight(185);
        return newUserDto;
    }
}
