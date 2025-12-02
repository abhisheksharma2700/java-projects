package com.busbookingsystem.service;

import com.busbookingsystem.dto.PassengerRequestDto;
import com.busbookingsystem.dto.PassengerResponseDto;

import java.util.List;


public interface PassengerEntityService {
    public PassengerResponseDto addUser(PassengerRequestDto passengerRequestDto);
    public List<PassengerResponseDto> getAllUsers();
    public PassengerResponseDto getUserById(Long id);
    public PassengerResponseDto updateUserById(Long id, PassengerRequestDto passengerRequestDto);
    public String deleteUserById(Long id);
}
