package com.example.taskserver.config.mapstruct;

import com.example.taskserver.api.dto.UserDto;
import com.example.taskserver.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDto userDto);

    UserDto toDto(UserEntity userEntity);

}
