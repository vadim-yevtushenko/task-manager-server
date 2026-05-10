package com.example.taskserver.service;

import com.example.taskserver.api.dto.UserDto;

public interface UserService {

    UserDto login(String email, String password);

    UserDto registration(UserDto userDto);

    void delete(String id);
}
