package com.busbookingsystem.service.implementation;

import com.busbookingsystem.dto.BookingRequestDto;
import com.busbookingsystem.dto.BookingResponseDto;
import com.busbookingsystem.dto.PassengerRequestDto;
import com.busbookingsystem.dto.PassengerResponseDto;
import com.busbookingsystem.entity.BookingEntity;
import com.busbookingsystem.entity.BusEntity;
import com.busbookingsystem.entity.PassengerEntity;
import com.busbookingsystem.entity.Users;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.mapper.BookingMapper;
import com.busbookingsystem.repository.BookingRepository;
import com.busbookingsystem.repository.BusRepository;
import com.busbookingsystem.repository.PassengerEntityRepository;
import com.busbookingsystem.repository.UsersDetailsRepository;
import com.busbookingsystem.service.BookingService;
import com.busbookingsystem.service.PassengerEntityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImplementation implements BookingService {

    private final  BookingRepository bookingRepository;
    private final BusRepository busRepository;
    private final PassengerEntityRepository passengerEntityRepository;
    private final UsersDetailsRepository usersDetailsRepository;
    private final PassengerEntityService passengerEntityService;

    public BookingServiceImplementation(BookingRepository bookingRepository, BusRepository busRepository, PassengerEntityRepository passengerEntityRepository, UsersDetailsRepository usersDetailsRepository, PassengerEntityService passengerEntityService) {
        this.bookingRepository = bookingRepository;
        this.busRepository = busRepository;
        this.passengerEntityRepository = passengerEntityRepository;
        this.usersDetailsRepository = usersDetailsRepository;
        this.passengerEntityService = passengerEntityService;
    }

    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto){
       //PassengerEntity passenger= passengerEntityRepository.findById(bookingRequestDto.getUserId()).orElseThrow(()->new NotFoundException("cannot find passenger assosiated with this id"));
      // PassengerEntity passenger= passengerEntityService.addUser(bookingRequestDto.getPassenger());
        Users user= usersDetailsRepository.findById(bookingRequestDto.getUserId()).orElseThrow(()-> new NotFoundException("user not found assosited with this id:"+bookingRequestDto.getUserId()));

        BusEntity bus= busRepository.findById(bookingRequestDto.getBusId()).orElseThrow(()->new NotFoundException("cannot find bus assosiated with this id"));

        if (bus.getAvailableSeats()<=0) {
            throw new NotFoundException("No seat available in this bus");
        }


        BookingEntity booking= new BookingEntity();
        booking.setBus(bus);
        booking.setUser(user);
        booking.setBookingAt(LocalDateTime.now());
        booking.setTicketNumber(bus.getAvailableSeats());
        booking=bookingRepository.save(booking);
        List<PassengerEntity> passengerEntities=new ArrayList<>();
        int seatNumber=bus.getTotalSeats()-bus.getAvailableSeats()+1;
        for(PassengerRequestDto dto:bookingRequestDto.getPassenger()){
            PassengerEntity passenger=new PassengerEntity();
            passenger.setName(dto.getName());
            passenger.setEmail(dto.getEmail());
            passenger.setAge(dto.getAge());
            passenger.setGender(dto.getGender());
            passenger.setMobileNumber(dto.getMobileNumber());
            passenger.setBooking(booking);
            passenger.setSeatNumber(seatNumber);
            passenger.setUser(user);

            passengerEntities.add(passenger);
            seatNumber++;

        }
        booking.setPassenger(passengerEntities);

        bus.setAvailableSeats(bus.getAvailableSeats()-passengerEntities.size());
        busRepository.save(bus);
        passengerEntityRepository.saveAll(passengerEntities);

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
    @Transactional
    public String deleteBookingById(Long id,String name){
        BookingEntity bookingEntity=bookingRepository.findById(id).orElseThrow(()->new NotFoundException("booking not found"));

        BusEntity bus= busRepository.findByName(name).orElseThrow(()-> new NotFoundException("cannot find bus by this name"));
        bookingRepository.delete(bookingEntity);
        bus.setAvailableSeats(bus.getAvailableSeats()+bus.getBookings().size());
        busRepository.save(bus);
        return ("booking deleted with id:"+id);

    }


}
