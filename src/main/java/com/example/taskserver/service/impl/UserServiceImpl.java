package com.example.taskserver.service.impl;

import com.example.taskserver.api.dto.UserDto;
import com.example.taskserver.config.mapstruct.UserMapper;
import com.example.taskserver.entity.UserEntity;
import com.example.taskserver.repository.UserRepository;
import com.example.taskserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @SneakyThrows
    @Override
    public UserDto login(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null){
            throw new CredentialNotFoundException();
        }
        if (!password.equals(userEntity.getPassword())){
            throw new RuntimeException();
        }

        return userMapper.toDto(userRepository.findByEmail(email));
    }

    @Override
    public UserDto registration(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
