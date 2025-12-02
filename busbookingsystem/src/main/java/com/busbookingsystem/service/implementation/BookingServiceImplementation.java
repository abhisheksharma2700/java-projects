package com.busbookingsystem.service.implementation;

import com.busbookingsystem.dto.BookingRequestDto;
import com.busbookingsystem.dto.BookingResponseDto;
import com.busbookingsystem.entity.BookingEntity;
import com.busbookingsystem.entity.BusEntity;
import com.busbookingsystem.entity.UserEntity;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.mapper.BookingMapper;
import com.busbookingsystem.repository.BookingRepository;
import com.busbookingsystem.repository.BusRepository;
import com.busbookingsystem.repository.UserEntityRepository;
import com.busbookingsystem.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImplementation implements BookingService {

    private final  BookingRepository bookingRepository;
    private final BusRepository busRepository;
    private final UserEntityRepository userEntityRepository;

    public BookingServiceImplementation(BookingRepository bookingRepository, BusRepository busRepository, UserEntityRepository userEntityRepository) {
        this.bookingRepository = bookingRepository;
        this.busRepository = busRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto){
        UserEntity user= userEntityRepository.findById(bookingRequestDto.getUserId()).orElseThrow(()->new NotFoundException("cannot find user assosiated with this id"));
        BusEntity bus= busRepository.findById(bookingRequestDto.getBusId()).orElseThrow(()->new NotFoundException("cannot find bus assosiated with this id"));

        if (bus.getAvailableSeats()<=0) {
            throw new NotFoundException("No seat available in this bus");
        }
        if(bookingRepository.findByUserIdAndBusId(bookingRequestDto.getUserId(),bookingRequestDto.getBusId()).isPresent()){
            throw new NotFoundException("user already booked seat");
        }

        BookingEntity booking= new BookingEntity();
        booking.setBus(bus);
        booking.setUser(user);
        booking.setLocalDateTime(LocalDateTime.now());
        booking.setSeatNumber( bus.getTotalSeats()-bus.getAvailableSeats()+1);
        booking.setTicketNumber(bus.getAvailableSeats());
        bus.setAvailableSeats(bus.getAvailableSeats()-1);
        busRepository.save(bus);
        booking=bookingRepository.save(booking);
        return BookingMapper.toDto(booking);


    }
    @Override
    public List< BookingResponseDto> getAllBooking(){
        return bookingRepository.findAll().stream().map(bookingEntity1 -> BookingMapper.toDto(bookingEntity1)).collect(Collectors.toList());

    }
    @Override
    public BookingResponseDto getBookingById(Long id){
        BookingEntity bookingEntity= bookingRepository.findById(id).orElseThrow(()->new NotFoundException("booking not found"));
        return BookingMapper.toDto(bookingEntity);
    }

    @Override
    public String deleteBookingById(Long id,String name){
        BookingEntity bookingEntity=bookingRepository.findById(id).orElseThrow(()->new NotFoundException("booking not found"));

        BusEntity bus= busRepository.findByName(name).orElseThrow(()-> new NotFoundException("cannot find bus by this name"));
        bookingRepository.delete(bookingEntity);
        bus.setAvailableSeats(bus.getAvailableSeats()+1);
        busRepository.save(bus);
        return ("booking deleted with id:"+id);

    }

}
