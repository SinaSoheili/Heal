package net.sinasoheili.heal.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto registerUser(UserDto userDto) {
        UserEntity userEntity = userDtoToUserEntity(userDto);
        registerUser(userEntity);
        return userEntityToUserDto(userEntity);
    }

    @Transactional
    public void registerUser(UserEntity userEntity) {
        log.info("try to register user with {} info", userEntity.toString());
        userRepository.persistUser(userEntity);
    }

    private UserEntity userDtoToUserEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        return userEntity;
    }

    private UserDto userEntityToUserDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }
}
