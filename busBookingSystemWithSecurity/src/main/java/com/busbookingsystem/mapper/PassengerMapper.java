package com.busbookingsystem.mapper;

import com.busbookingsystem.dto.PassengerRequestDto;
import com.busbookingsystem.dto.PassengerResponseDto;
import com.busbookingsystem.entity.PassengerEntity;

public class PassengerMapper {
    public static PassengerEntity toEntity(PassengerRequestDto dto){
        PassengerEntity user= new PassengerEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setMobileNumber(dto.getMobileNumber());
        user.setAge(dto.getAge());
        user.setGender(dto.getGender());

        return user;
    }
    public static PassengerResponseDto toDto(PassengerEntity entity){
        PassengerResponseDto passengerResponseDto = new PassengerResponseDto();
        passengerResponseDto.setId(entity.getId());
        passengerResponseDto.setName(entity.getName());
        passengerResponseDto.setEmail(entity.getEmail());
        passengerResponseDto.setMobileNumber(entity.getMobileNumber());
        passengerResponseDto.setGender(entity.getGender());
        passengerResponseDto.setAge(entity.getAge());
        passengerResponseDto.setSeatNumber(entity.getSeatNumber());
        return passengerResponseDto;
    }
}
