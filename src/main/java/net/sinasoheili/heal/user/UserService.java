package net.sinasoheili.heal.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto registerUser(UserDto userDto) {
        UserEntity userEntity = userDtoToUserEntity(userDto);
        registerUserInternal(userEntity);
        return userEntityToUserDto(userEntity);
    }

    @Transactional
    public void registerUserInternal(UserEntity userEntity) {
        log.info("try to register user with {} info", userEntity.toString());
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
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

    @Override
    public void createUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateUser(UserDetails user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        throw new UnsupportedOperationException();
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByIdInternal(username);
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
