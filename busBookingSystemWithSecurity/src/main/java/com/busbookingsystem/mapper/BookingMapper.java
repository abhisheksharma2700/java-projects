package com.busbookingsystem.mapper;

import com.busbookingsystem.dto.BookingResponseDto;
import com.busbookingsystem.entity.BookingEntity;

import java.util.stream.Collectors;

public class BookingMapper {

    public static BookingResponseDto toDto(BookingEntity entity){
        BookingResponseDto bookingResponseDto= new BookingResponseDto();
        bookingResponseDto.setId(entity.getId());
        bookingResponseDto.setUserName(entity.getUser().getUsername());
        bookingResponseDto.setBusName(entity.getBus().getName());
        bookingResponseDto.setTicketNumber(entity.getTicketNumber());
        bookingResponseDto.setBookingTime(entity.getBookingAt());
        bookingResponseDto.setPassengers(entity.getPassenger().stream().map(PassengerMapper::toDto).collect(Collectors.toList()));
        return bookingResponseDto;
    }
}
