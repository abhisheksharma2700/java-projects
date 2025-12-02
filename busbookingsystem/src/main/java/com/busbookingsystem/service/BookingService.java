package com.busbookingsystem.service;

import com.busbookingsystem.dto.BookingRequestDto;
import com.busbookingsystem.dto.BookingResponseDto;

import java.util.List;


public interface BookingService {
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto);
    public List<BookingResponseDto> getAllBooking();
    public BookingResponseDto getBookingById(Long id);
    public String deleteBookingById(Long id,String name);


}
