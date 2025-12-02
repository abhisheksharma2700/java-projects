package com.busbookingsystem.mapper;

import com.busbookingsystem.dto.UserRequestDto;
import com.busbookingsystem.dto.UserResponseDto;
import com.busbookingsystem.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(UserRequestDto dto){
        UserEntity user= new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobileNumber(dto.getMobileNumber());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());
        return user;
    }
    public static UserResponseDto toDto(UserEntity entity){
        UserResponseDto userResponseDto= new UserResponseDto();
        userResponseDto.setId(entity.getId());
        userResponseDto.setName(entity.getName());
        userResponseDto.setEmail(entity.getEmail());
        userResponseDto.setMobileNumber(entity.getMobileNumber());
        userResponseDto.setGender(entity.getGender());
        userResponseDto.setAge(entity.getAge());
        return userResponseDto;
    }
}
