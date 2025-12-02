package com.busbookingsystem.controller;

import com.busbookingsystem.dto.BookingRequestDto;
import com.busbookingsystem.dto.BookingResponseDto;
import com.busbookingsystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }
    @PostMapping("/addBooking")
    public BookingResponseDto addBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto){
         return bookingService.createBooking(bookingRequestDto);
    }
    @GetMapping("/getAllBookings")
    public List<BookingResponseDto> getAllBookings(){
        return bookingService.getAllBooking();
    }
    @GetMapping("/getBooking/{id}")
    public BookingResponseDto getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id);
    }

    @DeleteMapping("/cancel/{id}/{name}")
    public String deleteBookingById(@PathVariable Long id,@PathVariable String name){
        return bookingService.deleteBookingById(id,name);
    }

}
