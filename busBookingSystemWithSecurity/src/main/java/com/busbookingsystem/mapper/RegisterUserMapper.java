package com.busbookingsystem.mapper;

import com.busbookingsystem.dto.RegisterUserRequest;
import com.busbookingsystem.dto.RegisterUserResponse;
import com.busbookingsystem.entity.Users;

public class RegisterUserMapper {
    public static Users toEntity(RegisterUserRequest registerUserRequest){
        Users users= new Users();
        users.setUsername(registerUserRequest.getUsername());
        users.setEmail(registerUserRequest.getEmail());
        users.setPassword(registerUserRequest.getPassword());
        return users;

    }
    public static RegisterUserResponse toDto(Users users){
        RegisterUserResponse dto= new RegisterUserResponse();
        dto.setId(users.getId());
        dto.setUsername(users.getUsername());
        dto.setEmail(users.getEmail());
        dto.setPassword(users.getPassword());
        dto.setRole(users.getRole());
        return dto;


    }
}
