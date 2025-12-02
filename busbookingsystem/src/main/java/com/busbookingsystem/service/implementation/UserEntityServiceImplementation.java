package com.busbookingsystem.service.implementation;

import com.busbookingsystem.dto.UserRequestDto;
import com.busbookingsystem.dto.UserResponseDto;
import com.busbookingsystem.entity.UserEntity;
import com.busbookingsystem.enums.Gender;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.mapper.UserMapper;
import com.busbookingsystem.repository.UserEntityRepository;
import com.busbookingsystem.service.UserEntityService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityServiceImplementation implements UserEntityService {
    private final UserEntityRepository userEntityRepository;

    public UserEntityServiceImplementation(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    @Transactional
    public UserResponseDto addUser( @Valid UserRequestDto userRequestDto) {
        if(userRequestDto.getName()==null||
                userRequestDto.getEmail()==null||
                userRequestDto.getMobileNumber()==null){
            throw new NotFoundException("a user must be have properties Name, email and phone Number");
        }
        if(userRequestDto.getAge()<=0|| userRequestDto.getAge()>=101){
            throw new NotFoundException("age must be between 1-100");
        }
        if(userEntityRepository.existsByEmail(userRequestDto.getEmail())){
            throw new NotFoundException("already added user with this particular email ");
        }
        if(userEntityRepository.existsByMobileNumber(userRequestDto.getMobileNumber())){
            throw new NotFoundException("already added user with this mobileNumber");
        }
        if (!Gender.isValidGender(String.valueOf(userRequestDto.getGender()))) {
            throw new NotFoundException("Invalid gender: " + userRequestDto.getGender());
        }


        UserEntity userEntity = UserMapper.toEntity(userRequestDto);

        userEntity = userEntityRepository.save(userEntity);
        return UserMapper.toDto(userEntity);


    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userEntityRepository.findAll().stream().map(userEntity -> UserMapper.toDto(userEntity)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("user with this id:" + id + "not found"));
        return UserMapper.toDto(userEntity);
    }

    @Override
    public UserResponseDto updateUserById(@Valid Long id, UserRequestDto userRequestDto) {
        UserEntity userEntity = userEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("user with this id:" + id + "not found"));
        if(userRequestDto.getName()==null||
                userRequestDto.getEmail()==null||
                userRequestDto.getMobileNumber()==null){
            throw new NotFoundException("a user must be have properties Name, email and phone Number");
        }
        if(userRequestDto.getAge()<=0|| userRequestDto.getAge()>=101){
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

        userEntity.setName(userRequestDto.getName());
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setMobileNumber(userRequestDto.getMobileNumber());
        userEntity.setGender(userRequestDto.getGender());
        userEntity.setAge(userRequestDto.getAge());
        userEntityRepository.save(userEntity);
        return UserMapper.toDto(userEntity);


    }

    @Override
    public java.lang.String deleteUserById(Long id) {
        UserEntity existingUserEntity = userEntityRepository.findById(id).orElseThrow(() -> new NotFoundException("can't delete user beacause id:" + id + " don't match to any  existing user"));
        userEntityRepository.delete(existingUserEntity);
        return "User deleted from database ";
    }
}
