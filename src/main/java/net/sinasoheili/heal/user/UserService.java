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
        registerUserInternal(userEntity);
        return userEntityToUserDto(userEntity);
    }

    @Transactional
    public void registerUserInternal(UserEntity userEntity) {
        log.info("try to register user with {} info", userEntity.toString());
        userRepository.persistUser(userEntity);
    }

    @Transactional(readOnly = true)
    public UserDto loadUserById(String userId) {
        return userEntityToUserDto(loadUserByIdInternal(userId));
    }

    @Transactional(readOnly = true)
    public UserEntity loadUserByIdInternal(String userId) {
        return userRepository.loadUserById(userId).orElseThrow(
                () -> {throw new UserNotFoundException(userId);}
        );
    }

    @Transactional
    public int deleteAll() {
        return userRepository.deleteAll();
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
