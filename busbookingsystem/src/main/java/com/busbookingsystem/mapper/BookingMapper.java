package com.busbookingsystem.mapper;

import com.busbookingsystem.dto.BookingResponseDto;
import com.busbookingsystem.entity.BookingEntity;

public class BookingMapper {

    public static BookingResponseDto toDto(BookingEntity entity){
        BookingResponseDto bookingResponseDto= new BookingResponseDto();
        bookingResponseDto.setId(entity.getId());
        bookingResponseDto.setUserName(entity.getUser().getName());
        bookingResponseDto.setBusName(entity.getBus().getName());
        bookingResponseDto.setSeatNumber(entity.getSeatNumber());
        bookingResponseDto.setTicketNumber(entity.getTicketNumber());
        bookingResponseDto.setBookingTime(entity.getLocalDateTime());
        return bookingResponseDto;
    }
}
