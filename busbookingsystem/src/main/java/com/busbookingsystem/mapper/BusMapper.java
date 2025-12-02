package com.busbookingsystem.mapper;

import com.busbookingsystem.dto.BusRequestDto;
import com.busbookingsystem.dto.BusResponseDto;
import com.busbookingsystem.entity.BusEntity;

public class BusMapper {
    public static BusEntity toEntity(BusRequestDto dto){
        BusEntity bus= new BusEntity();
        bus.setName(dto.getName());
        bus.setBusNumber(dto.getBusNumber());
        bus.setSource(dto.getSource());
        bus.setDestination(dto.getDestination());
        bus.setDateOfDeparture(dto.getDateOfDeparture());
        bus.setTimeOfDeparture(dto.getTimeOfDeparture());
        bus.setTotalSeats(dto.getTotalSeats());
        bus.setAvailableSeats(dto.getTotalSeats());
        return bus;
    }
    public static BusResponseDto toDto(BusEntity entity){
        BusResponseDto busResponseDto= new BusResponseDto();
        busResponseDto.setId(entity.getId());
        busResponseDto.setName(entity.getName());
        busResponseDto.setBusNumber(entity.getBusNumber());
        busResponseDto.setSource(entity.getSource());
        busResponseDto.setDestination(entity.getDestination());
        busResponseDto.setDateOfDeparture(entity.getDateOfDeparture());
        busResponseDto.setTimeOfDeparture(entity.getTimeOfDeparture());
        busResponseDto.setTotalSeats(entity.getTotalSeats());
        busResponseDto.setAvailableSeats(entity.getAvailableSeats());
        return busResponseDto;

    }
}
