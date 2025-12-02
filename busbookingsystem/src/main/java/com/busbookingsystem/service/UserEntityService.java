package com.busbookingsystem.service;

import com.busbookingsystem.dto.UserRequestDto;
import com.busbookingsystem.dto.UserResponseDto;

import java.util.List;


public interface UserEntityService {
    public UserResponseDto addUser(UserRequestDto userRequestDto);
    public List<UserResponseDto> getAllUsers();
    public UserResponseDto getUserById(Long id);
    public UserResponseDto updateUserById(Long id, UserRequestDto userRequestDto);
    public String deleteUserById(Long id);
}
