package com.busbookingsystem.service.implementation;

import com.busbookingsystem.dto.PassengerRequestDto;
import com.busbookingsystem.dto.PassengerResponseDto;
import com.busbookingsystem.entity.PassengerEntity;
import com.busbookingsystem.enums.Gender;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.mapper.PassengerMapper;
import com.busbookingsystem.repository.BookingRepository;
import com.busbookingsystem.repository.PassengerEntityRepository;
import com.busbookingsystem.service.PassengerEntityService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerEntityServiceImplementation implements PassengerEntityService {
    private final PassengerEntityRepository passengerEntityRepository;
    private final BookingRepository bookingRepository;

    public PassengerEntityServiceImplementation(PassengerEntityRepository passengerEntityRepository, BookingRepository bookingRepository) {
        this.passengerEntityRepository = passengerEntityRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public PassengerResponseDto addUser(@Valid PassengerRequestDto passengerRequestDto) {
        if(passengerRequestDto.getName()==null||
                passengerRequestDto.getEmail()==null||
                passengerRequestDto.getMobileNumber()==null){
            throw new NotFoundException("a user must be have properties Name, email and phone Number");
        }
        if(passengerRequestDto.getAge()<=0|| passengerRequestDto.getAge()>=101){
            throw new NotFoundException("age must be between 1-100");
        }
        if(passengerEntityRepository.existsByEmail(passengerRequestDto.getEmail())){
            throw new NotFoundException("already added user with this particular email ");
        }
        if(passengerEntityRepository.existsByMobileNumber(passengerRequestDto.getMobileNumber())){
            throw new NotFoundException("already added user with this mobileNumber");
        }
        if (!Gender.isValidGender(String.valueOf(passengerRequestDto.getGender()))) {
            throw new NotFoundException("Invalid gender: " + passengerRequestDto.getGender());
        }


        PassengerEntity passengerEntity = PassengerMapper.toEntity(passengerRequestDto);

        passengerEntity = passengerEntityRepository.save(passengerEntity);
        return PassengerMapper.toDto(passengerEntity);


    }

    @Override
    public List<PassengerResponseDto> getAllUsers() {
        return passengerEntityRepository.findAll().stream().map(userEntity -> PassengerMapper.toDto(userEntity)).collect(Collectors.toList());
    }

    @Override
    public PassengerResponseDto getUserById(Long id) {
        PassengerEntity passengerEntity = passengerEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("user with this is id not found"));
        return PassengerMapper.toDto(passengerEntity);
    }

    @Override
    public PassengerResponseDto updateUserById(@Valid Long id, PassengerRequestDto passengerRequestDto) {
        PassengerEntity passengerEntity = passengerEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("user with this id:" + id + "not found"));
        if(passengerRequestDto.getName()==null||
                passengerRequestDto.getEmail()==null||
                passengerRequestDto.getMobileNumber()==null){
            throw new NotFoundException("a user must be have properties Name, email and phone Number");
        }
        if(passengerRequestDto.getAge()<=0|| passengerRequestDto.getAge()>=101){
            throw new NotFoundException("age must be between 1-100");
        }

//        Gender male=MALE;
//        Gender female=FEMALE;
//        if(userRequestDto.getGender()!=male&& userRequestDto.getGender()!=female){
//            throw new NotFoundException("please provide a valid gender (MALE,FEMALE)");
//        }
//        if (!Gender.isValidGender(String.valueOf(userRequestDto.getGender()))) {
//            throw new NotFoundException("Invalid gender: " + userRequestDto.getGender());
//        }

        passengerEntity.setName(passengerRequestDto.getName());
        passengerEntity.setEmail(passengerRequestDto.getEmail());
        passengerEntity.setMobileNumber(passengerRequestDto.getMobileNumber());
        passengerEntity.setGender(passengerRequestDto.getGender());
        passengerEntity.setAge(passengerRequestDto.getAge());
        passengerEntityRepository.save(passengerEntity);
        return PassengerMapper.toDto(passengerEntity);


    }

    @Override
    public java.lang.String deleteUserById(Long id) {
        PassengerEntity existingPassengerEntity = passengerEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("can't delete user beacause id:" + id + " don't match to any  existing user"));
        long bookingCount = bookingRepository.countByPassenger(existingPassengerEntity);
        if(bookingCount > 0) {
            throw new NotFoundException("Cannot delete user with existing bookings: " + bookingCount);
        }

        passengerEntityRepository.delete(existingPassengerEntity);
        return "User deleted from database ";
    }
}
